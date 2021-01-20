
# Calculator

[![Master Build](https://travis-ci.com/audhumla/Calculator.svg?branch=master)](https://travis-ci.com/github/audhumla/Calculator)

<img src="resources/calculator.png" alt="Logo" width="241.2" height="254.6" />

# Table of content
 - [Description](#description-:page_with_curl:)
    * [Example 1](#example-1)
    * [Example 2](#example-2)

## Description :page_with_curl:
Write some code to calculate a result from a set of instructions.
Instructions comprise a keyword and a number that are separated by a space per rightOperation. 
Instructions are loaded from file and results are output to the screen. 
Any number of Instructions can be specified.
Instructions should be the add, divide, multiply and subtract operators, ignoring mathematical precedence. 
The last instruction should be "apply", and a number (e.g., "apply 3"). 
The calculator is then initialised with that number and the previous instructions are applied to that number.

### Example 1
#### [Input from file]
```
add 2
multiply 3
apply 3
```
#### [Output to screen]
`15`

#### [Explanation]
`(3 + 2) * 3 = 15`

### Example 2
#### [Input from file]
```
multiply 9
apply 5
```
#### [Output to screen]
`45`

#### [Explanation]
`5 * 9 = 45`

Please include unit tests and demonstrate good architectural principles.

## How to run it
### Dependencies
You need to install a version of Java  

### Run it
Navigate to the home directory and type:
```bash
 ./run.sh input/example-1.txt 
```
   
