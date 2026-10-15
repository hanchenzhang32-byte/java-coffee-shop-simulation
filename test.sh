#!/bin/sh
set -eu
cd "$(dirname "$0")"
mkdir -p out data lib
junit=lib/junit-platform-console-standalone-1.9.0.jar
if [ ! -f "$junit" ]; then
    curl --fail --location --output "$junit" https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.0/junit-platform-console-standalone-1.9.0.jar
fi
find src test -name "*.java" > out/test-sources.txt
javac -d out -cp "$junit" @out/test-sources.txt
java -jar "$junit" --details=summary --class-path out --scan-classpath
