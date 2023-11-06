#!/bin/sh

curl -X POST http://localhost:8080/ingredients \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{"name":"steak"}'
