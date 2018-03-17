#!/bin/bash
git pull --ff-only

pushd decide
./gradlew build
docker build .
popd

docker-compose rm -f
docker-compose up --build -d