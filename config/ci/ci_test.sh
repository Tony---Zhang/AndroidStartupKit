#!/usr/bin/env bash

basedir=`dirname $0`
cd $basedir/../../

./gradlew clean  && \
./gradlew check
