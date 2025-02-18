#!/bin/sh
sfdp -Tsvg \
	-Gsize=40,32 -Gfontname=Sans-Serif -Gfontsize=24 -Gfontcolor=red -Glabel="urn:un:unece:uncefact:data:standard" -Glabelloc=t -Goverlap=false -Goutputorder=edgesfirst \
	-Earrowhead=halfopen -Ecolor=lightgrey \
	-Nfontname=Sans-Serif \
	uncefact-xml-schemas-graph.dot >uncefact-xml-schemas-graph.svg
