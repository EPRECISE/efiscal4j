#!/bin/sh

mvn versions:set -DnewVersion=$1;
git add .;
git commit -a -m "Gerada versão $1";
git push origin master;
git tag $1;
git push --tags;
