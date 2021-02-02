#!/bin/bash

echo "copying directory $1 to CS Server"
sshpass -p "Atmfox091600." scp -r ~/Desktop/CSCI261/$1 atm3232@queeg.cs.rit.edu:~/Courses/CSCI261
