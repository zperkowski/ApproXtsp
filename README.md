# ApproXtsp

**ApproXtsp** is a program that compares results of different algorithms of the Travelling Salesman Problem.

### Algorithms
- Nearest Neighbour
- Approx Tsp Tour
- "Multi" Approx Tsp Tour - runs Approx Tsp Tour from every vertex and gives the best result 

### Features
- Data generator
- Showing the path and its length
- Loading data from a file
- Saving results to a file

### Data
Input file has to have in the first line a information how many lines are in the file.

At the end of the file should be not "EOF".

Files should have _.txt_ extension.

Right format of _ulysses16_:
```
0 16
1 38.24 20.42
2 39.57 26.15
3 40.56 25.32
4 36.26 23.12
5 33.48 10.54
6 37.56 12.19
7 38.42 13.11
8 37.52 20.44
9 41.23 9.10
10 41.17 13.05
11 36.08 -5.21
12 38.47 15.13
13 38.15 15.35
14 37.51 15.17
15 35.49 14.32
16 39.36 19.56
```
  
More data and best known solutions can be found here: [Universität Heidelberg](http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/).   

![ApproXtsp](https://github.com/zperkowski/ApproXtsp/blob/master/img.png "ApproXtsp")

## Getting Started

### Prerequisites

What you need to run:

[Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) & [JavaFX](http://www.oracle.com/technetwork/java/javafx/install-javafx-sdk-1-2-139156.html)