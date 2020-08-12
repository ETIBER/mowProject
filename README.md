# Mow project

Bootsrap with : 

    https://github.com/swkBerlin/kata-bootstraps

## Setup

Clone the project and use gradle to build it

## Running Tests

To execute the tests either run `./gradlew test`, `mvn test` or run the tests from the IDE you are using

### Some integration test may failed

As the simulation is multithreaded, the speed of the mow is not the same at each run. 
The result of each simulation, particularly for large simulation can be different.
As two run are sometimes different, sometimes integration test may fail.

## Usage of the simulator

The mowers are programmed using an input file constructed in the following manner:
The first line corresponds to the upper right corner of the lawn. The bottom left corner is
implicitly (0, 0).
The rest of the file describes the multiple mowers that are on the lawn. Each mower is described on two lines:
The first line contains the mower's starting position and orientation in the format "X Y O". X and Y are the coordinates and O is the orientation.
The second line contains the instructions for the mower to navigate the lawn. The instructions are not separated by spaces.
At the end of the simulation, the final position and orientation of each mower is output in the order that the mower appeared in the input.

You can find example simulation file in src/main/resources/

Filename is send as argument to the main function.
