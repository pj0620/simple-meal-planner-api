#!/bin/sh

curl -X PUT http://localhost:8080/ingredients/100 \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{"name":"ayo"}'
