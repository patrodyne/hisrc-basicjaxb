#!/bin/sh
sfdp -Tsvg -Gfontcolor=red -Glabel="urn:un:unece:uncefact:data:standard" -Goverlap=false -Goutputorder=edgesfirst -Earrowhead=halfopen -Ecolor=lightgrey uncefact-xml-schemas-graph.dot >uncefact-xml-schemas-graph.svg
