#!/bin/sh

curl -X PUT http://localhost:8080/ingredients/4 \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{"name":"ketchup"}'
