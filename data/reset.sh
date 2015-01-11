#!/bin/sh
rm serverdb.lck
rm serverdb.log
rm serverdb.script
rm serverdb.script.dat
rm -r serverdb.tmp

cp serverdb.script.bck serverdb.script
