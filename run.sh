#!/bin/sh
usage()
{
    echo "It runs the calculator"
    echo "It runs the calculator"
    echo "Example: ./run.sh input-file.txt"
}

main() {
  while [ "$1" != "" ]; do
    local PARAM="echo $1 | awk -F= '{print $1}'"
    local VALUE="echo $1 | awk -F= '{print $2}'"
    case $PARAM in
        -h | --help)
            usage
            exit
            ;;
        *)
            echo "ERROR: unknown parameter \"$PARAM\""
            usage
            exit 1
            ;;
          esac
          shift
    done

    java -jar build/libs/Calculator-1.0-SNAPSHOT.jar $1
}

main $