#!/bin/sh
set -eu
cd "$(dirname "$0")"
mkdir -p out data
find src test -name "*.java" > out/test-sources.txt
javac -d out -cp lib/junit-platform-console-standalone-1.9.0.jar @out/test-sources.txt
java -jar lib/junit-platform-console-standalone-1.9.0.jar --details=summary --class-path out --scan-classpath
