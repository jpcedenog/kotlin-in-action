#!/bin/bash
clear &&
gradle build &&
kotlin -classpath build/libs/basic.jar chapters.chapter$1.S$1$2Kt
