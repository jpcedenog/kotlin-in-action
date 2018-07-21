#!/usr/bin/bash
clear
gradle build
kotlin -classpath build/libs/basic.jar chapters.chapter5.S55Kt
