#!/usr/bin/bash
clear
gradle build
kotlin -classpath build/libs/basic.jar main.MainKt
