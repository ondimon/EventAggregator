#!/usr/bin/env bash

if [[ "$SERVICE_NAME" = "" ]]; then
  echo "Необходимо указать имя сервиса"
  exit 1
fi

if [[ -v $"WAITFORIT_HOST"  && -v $"WAITFORIT_PORT" ]]; then
 exec ./wait-for-it.sh -- mvn spring-boot:run -pl "$SERVICE_NAME"
else
 exec mvn spring-boot:run -pl "$SERVICE_NAME"
fi
