#!/bin/bash
export DISPLAY=":0"
/usr/bin/xset s off -dpms
cd $HOME
/usr/bin/java -jar baccaratDisplay.jar
