#!/bin/sh
java -classpath ~/lib/hsqldb-2.3.2/hsqldb/lib/hsqldb.jar org.hsqldb.Server --database.0 file:serverdb --dbname.0 monitorasv
