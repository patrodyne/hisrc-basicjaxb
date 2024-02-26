#!/bin/sh
#
# Use PlantUML to generate class diagram.
# See https://sourceforge.net/projects/plantuml/files/plantuml.jar/download
# See https://graphviz.org/download/
#
# Configure BEGIN
export GRAPHVIZ_DOT="/usr/bin/dot"
IMGTYPE="svg"
DIAGRAM_NAME="XJCModel"
PLANTUML_HOME="/opt/java/plantuml"
PLANTUML_TEMP="target/generated-docs/${DIAGRAM_NAME}Temp.puml"
PLANTUML_FILE="target/generated-docs/${DIAGRAM_NAME}.puml"
PLANTUML_DEST="doc/${DIAGRAM_NAME}.${IMGTYPE}"
# Configure END

# Pre-process PUML
cat "${PLANTUML_TEMP}" | \
	sed 's/java.math.BigDecimal/BigDecimal/' | \
	sed 's/java.util.List/List/' | \
	sed 's/javax.xml.datatype.XMLGregorianCalendar/XMLGregCal/' \
	>"${PLANTUML_FILE}"

# Generate IMG
java -jar ${PLANTUML_HOME}/plantuml.jar -t${IMGTYPE} "${PLANTUML_FILE}"
PLANTUML_IMG="${PLANTUML_FILE%.puml}.${IMGTYPE}"
echo "PlantUML generated ${PLANTUML_IMG}"
mv "${PLANTUML_IMG}" "${PLANTUML_DEST}"
echo "Installed to ${PLANTUML_DEST}"
