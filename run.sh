#!/bin/sh
usage()
{
    echo "It runs the calculator."
    echo "Provide as input the file to read the list of expression from"
    echo "Example: ./run.sh input-file.txt"
}

main()
{
    java -cp build/libs/Calculator-1.0-SNAPSHOT.jar com.ki.calculator.inbound.Main "$1"
}

main "$@"