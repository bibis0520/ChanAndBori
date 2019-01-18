#!/bin/bash

#####################################
#  github auto commit shell script  #
#  author : Chan Kim                #
#  Date   : 2019.01.18              #
#  Use...                           #
#  날짜를 변수에 담아 commit        #
#  message에 담아줘 마지막에 push   #
#####################################

nowDate="$(date +%y%m%d%H%m)"

git pull

git add --all

git commit -am "$nowDate updated"

git push
