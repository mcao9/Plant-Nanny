#!/bin/bash
#This is a shell script that just runs the java exe. Using the windows clock, it will be executed every hour on the hour.

javac connectDatabase.java

while true; do
	java connectDatabase
	sleep 10m
done


