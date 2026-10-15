#!/bin/sh
set -eu
cd "$(dirname "$0")"
mkdir -p out
find src examples -name "*.java" > out/demo-sources.txt
javac -d out @out/demo-sources.txt
java -cp out CafeDemo
