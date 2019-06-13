# REA Toy Robot Challenge

This submission to the REA Toy Robot Technical Challenge is distributed as an Eclipse Workspace.

It is written in Scala and written using functional programming.

Source code is in the RobotChallenge/src directory

Example data files are in the the RobotChallenge/data directory.

## Unit Testing

Most of the testing was done using JUnit tests located within the src directory. All 21 unit tests pass.

## Code Structure

+ RobotApp - The main program entry point.
+ Simulation - Responsible for managing the simulation.
+ Robot - Represents an instance of the robot at a given point in time.
+ Board - Defines the board
+ Facing - Case class that defines a direction the robot can face.
+ RobotTest - Robot unit tests
+ SimulationTest - Simulation unit tests

## Test Run

Programs are submitting by executing the RobotApp.scala module and providing the filename of the program as the first
command line argument.

This is the result of running the roundTheWorld program that places the robot at the center, moves it
to all four map corners and returns to the center.

~~~~
===== REA Robot =====
--- Running 'data\roundTheWorld.txt' ---
PLACE 2,2,NORTH
REPORT
MOVE
MOVE
REPORT
RIGHT
MOVE
MOVE
MOVE
MOVE
MOVE
REPORT
RIGHT
MOVE
MOVE
MOVE
MOVE
MOVE
REPORT
RIGHT
MOVE
MOVE
MOVE
MOVE
MOVE
REPORT
RIGHT
MOVE
MOVE
MOVE
MOVE
MOVE
REPORT
RIGHT
MOVE
MOVE
RIGHT
MOVE
MOVE
LEFT
LEFT
REPORT
--- Output ---
2,2,NORTH
2,4,NORTH
4,4,EAST
4,0,SOUTH
0,0,WEST
0,4,NORTH
2,2,NORTH
--- Complete ---
~~~~

