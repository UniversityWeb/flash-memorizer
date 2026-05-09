#!/bin/bash
set -e

WATCH_DIRS="src/main"
POLL_INTERVAL_SECONDS="2"

echo "Starting polling watcher to compile on source/template changes: $WATCH_DIRS"

# background watcher: compile on changes (works on Windows bind mounts too)
(
  last_signature=""

  while true; do
    current_signature=$(find $WATCH_DIRS -type f \( -name "*.java" -o -name "*.html" -o -name "*.css" -o -name "*.js" -o -name "*.yml" -o -name "*.yaml" -o -name "*.properties" \) \
      -printf "%T@ %p\n" | sort | sha256sum | awk '{print $1}')

    if [ "$current_signature" != "$last_signature" ]; then
      if [ -n "$last_signature" ]; then
        echo "Change detected, running mvn compile"
        mvn -DskipTests=true compile
      fi
      last_signature="$current_signature"
    fi

    sleep "$POLL_INTERVAL_SECONDS"
  done
) &

echo "Starting Spring Boot"
exec mvn spring-boot:run -Dspring-boot.run.fork=false
