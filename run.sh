#!/bin/sh
main()
{
    input_file="input/input-file.txt"
    if [ -n "$1" ]
    then
      input_file="$1"
    fi

    echo "Running the Calculator with gradle..."
    ./gradlew build installDist run --args="$input_file"
    echo "Application terminated"
}

main "$@"
