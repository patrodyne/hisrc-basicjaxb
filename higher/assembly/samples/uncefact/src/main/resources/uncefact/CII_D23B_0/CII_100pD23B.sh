#!/bin/sh
sfdp -Tsvg \
	-Gsize=20,16 -Gfontname=Sans-Serif -Gfontsize=24 -Gfontcolor=red -Glabel="urn:un:unece:uncefact" -Glabelloc=t -Goverlap=false -Goutputorder=edgesfirst \
	-Earrowhead=halfopen -Ecolor=lightgrey \
	-Nfontname=Sans-Serif \
	CII_100pD23B.dot >CII_100pD23B.svg
