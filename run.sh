#!/bin/bash
clear &&
kotlinc chapters/chapter$1/S$1$2.kt -include-runtime -d output/S$1$2.jar &&
java -jar output/S$1$2.jar
