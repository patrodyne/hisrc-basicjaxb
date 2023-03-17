package org.jvnet.basicjaxb.xmlschema;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

import javax.xml.namespace.QName;

/**
 * Built-in <b>XML Schema Datatypes</b> are those which are declared using this URI,
 * <code>"http://www.w3.org/2001/XMLSchema"</code>. 
 * 
 * <p>
 * A <code>QName</code> represents a <strong>qualified name</strong> as defined in the XML specifications:
 * </p>
 * 
 * <ul>
 *   <li><a href="http://www.w3.org/TR/xmlschema-2/#QName">XML Schema Part2: Datatypes specification</a>,</li>
 *   <li><a href="http://www.w3.org/TR/REC-xml-names/#ns-qualnames">Namespaces in XML</a>.</li>
 * </ul>
 * 
 * <p>
 * The value of a <code>QName</code> contains a <strong>Namespace
 * URI</strong>, <strong>local part</strong> and <strong>prefix</strong>.
 * </p>
 * 
 * <p>
 * The prefix is included in <code>QName</code> to retain lexical
 * information <strong><em>when present</em></strong> in an {@link
 * javax.xml.transform.Source XML input source}. The prefix is
 * <strong><em>NOT</em></strong> used in {@link #equals(Object)
 * QName.equals(Object)} or to compute the {@link #hashCode()
 * QName.hashCode()}.  Equality and the hash code are defined using
 * <strong><em>only</em></strong> the Namespace URI and local part.
 * </p>
 */
public class XmlSchemaConstants
{
	public static final String NAMESPACE_URI = W3C_XML_SCHEMA_NS_URI;
	
	public static final QName ANYTYPE = xsd("anyType");
	public static final QName ANYSIMPLETYPE = xsd("anySimpleType");
	public static final QName STRING = xsd("string");
	public static final QName NORMALIZEDSTRING = xsd("normalizedString");
	public static final QName TOKEN = xsd("token");
	public static final QName LANGUAGE = xsd("language");
	public static final QName NAME = xsd("Name");
	public static final QName NCNAME = xsd("NCName");
	public static final QName ID = xsd("ID");
	public static final QName IDREF = xsd("IDREF");
	public static final QName IDREFS = xsd("IDREFS");
	public static final QName ENTITY = xsd("ENTITY");
	public static final QName ENTITIES = xsd("ENTITIES");
	public static final QName NMTOKEN = xsd("NMTOKEN");
	public static final QName NMTOKENS = xsd("NMTOKENS");
	public static final QName BOOLEAN = xsd("boolean");
	public static final QName BASE64BINARY = xsd("base64Binary");
	public static final QName HEXBINARY = xsd("hexBinary");
	public static final QName FLOAT = xsd("float");
	public static final QName DECIMAL = xsd("decimal");
	public static final QName INTEGER = xsd("integer");
	public static final QName NONPOSITIVEINTEGER = xsd("nonPositiveInteger");
	public static final QName NEGATIVEINTEGER = xsd("negativeInteger");
	public static final QName LONG = xsd("long");
	public static final QName INT = xsd("int");
	public static final QName SHORT = xsd("short");
	public static final QName BYTE = xsd("byte");
	public static final QName NONNEGATIVEINTEGER = xsd("nonNegativeInteger");
	public static final QName UNSIGNEDLONG = xsd("unsignedLong");
	public static final QName UNSIGNEDINT = xsd("unsignedInt");
	public static final QName UNSIGNEDSHORT = xsd("unsignedShort");
	public static final QName UNSIGNEDBYTE = xsd("unsignedByte");
	public static final QName POSITIVEINTEGER = xsd("positiveInteger");
	public static final QName DOUBLE = xsd("double");
	public static final QName ANYURI = xsd("anyURI");
	public static final QName QNAME = xsd("QName");
	public static final QName NOTATION = xsd("NOTATION");
	public static final QName DURATION = xsd("duration");
	public static final QName DATETIME = xsd("dateTime");
	public static final QName TIME = xsd("time");
	public static final QName DATE = xsd("date");
	public static final QName GYEARMONTH = xsd("gYearMonth");
	public static final QName GYEAR = xsd("gYear");
	public static final QName GMONTHDAY = xsd("gMonthDay");
	public static final QName GDAY = xsd("gDay");
	public static final QName GMONTH = xsd("gMonth");
	public static final QName CALENDAR = xsd("\u0000");

	public static final QName[] TYPE_NAMES = new QName[]
	{
		 ANYTYPE, ANYSIMPLETYPE, STRING, NORMALIZEDSTRING, TOKEN, LANGUAGE,
		 NAME, NCNAME, ID, IDREF, IDREFS, ENTITY, ENTITIES, NMTOKEN,NMTOKENS,
		 BOOLEAN, BASE64BINARY, HEXBINARY, FLOAT, DECIMAL, INTEGER,
		 NONPOSITIVEINTEGER, NEGATIVEINTEGER, LONG, INT, SHORT, BYTE,
		 NONNEGATIVEINTEGER, UNSIGNEDLONG, UNSIGNEDINT, UNSIGNEDSHORT,
		 UNSIGNEDBYTE, POSITIVEINTEGER, DOUBLE, ANYURI, QNAME, NOTATION,
		 DURATION, DATETIME, TIME, DATE, GYEARMONTH, GYEAR, GMONTHDAY, GDAY,
		 GMONTH, CALENDAR
	};

	public static QName xsd(String localPart)
	{
		return new QName(NAMESPACE_URI, localPart, "xsd");
	}
}
