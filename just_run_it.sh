#!/usr/bin/env bash

JAR_NAME=$(find "build/libs" -name '*.jar')

exec java -jar "$JAR_NAME" "$@"
