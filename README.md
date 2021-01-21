
# Calculator

<img src="resources/calculator.png" alt="Logo" width="241.2" height="254.6" />

# Table of content
 - [Description](#description-page_with_curl)
    * [Example 1](#example-1)
    * [Example 2](#example-2)
- [Getting Started](#getting-started)
    * [Prerequisites](#prerequisites)
    * [Build a jar and run it](#build-a-jar-and-run-it)
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

## Getting started
### Prerequisites
You need to have Java 11 or a later version installed.

### Build a jar and run it
Navigate to the root of the project and type:
```bash
 ./run.sh input/example-1.txt 
```
You can provide a different input file, if you want: 
```bash
echo "multiply 9" >> input/my-example.txt
echo "apply 5" >> input/my-example.txt
./run.sh input/my-example.txt 
```
If you're using Windows, you can directly use gradle:
```bash
./gradlew build installDist run --args="my-example.txt"
```
