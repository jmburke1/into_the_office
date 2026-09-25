#!/usr/bin/env bash

set -e

./gradlew clean --no-daemon

./gradlew shadowJar --no-daemon

JAR_NAME=$(find "build/libs" -name '*.jar')

exec java -jar "$JAR_NAME" "$@"
