# Thing demo program

## Task
Create a thing that can be offered numbers.

At any given time it can tell 3 things:
1) the smallest number it has encountered so far
2) the largest number it has encountered so far
3) the average of all numbers it has encountered so far

Prove that it is working correctly. Make it so that a novice programmer cannot use it the wrong way, nor that an evil programmer can break it.

## Solution
Solution have been implemented as ThingService and console program.

## Build
### Prerequisites
- Java 8
- Maven 3

### Build
```
mvn clean install
```

### Generate test coverage reports
```
mvn site
```
Demo Main class was excluded from coverage reports.

### Run console program
```
mvn exec:java
```
logging configuration resides in resources/logback.xml.
Move it out of jar-file in real life usage.


## Prove that it is working correctly

Some tests were developed to prove program work is correct.

### Test coverage

| Element	| Instructions	| Cov.	| Branches	| Cov.	| Missed	| Cxty	| Missed	| Lines	| Missed	| Methods	| Missed	| Classes |
| ---       | ---                   | ---   | ---               | ---   | ---       | ---   | ---       | ---   | ---       | ---       | ---       | ---     |
| org.nameapi.thing.service.impl	| 142	| 100%	| 6	| 100%	| 0	| 12	| 0	| 37	| 0	| 9	| 0	| 1 |
| org.nameapi.thing.service.impl.algo	| 112	| 100%	| 10	| 100%	| 0	| 15	| 0	| 30	| 0	| 10	| 0	| 3 |
| org.nameapi.thing.service	| 34	| 100%		|  | n/a	| 0	| 1	| 0	| 4	| 0	| 1	| 0	| 1 |
| Total	| 0 of 288	| 100%	| 0 of 16	| 100%	| 0	| 28	| 0	| 71	| 0	| 20	| 0	| 5 |

