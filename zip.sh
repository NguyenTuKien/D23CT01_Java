#!/bin/bash
# Usage: ./zip.sh folder_name
zip $1/$1.zip $1/*
echo "Created zip file: $1/$1.zip"