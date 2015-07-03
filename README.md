# Connect Four

A two-player game in which the players first choose a color and then take turns dropping colored discs from the top into a seven-column, six-row vertically suspended grid. The pieces fall straight down, occupying the next available space within the column. The object of the game is to connect four of one's own discs of the same color next to each other vertically, horizontally, or diagonally before your opponent.

The game is console based, and is implemented in both Java and Scala.

### Instructions

#### Java
1. Just compile the source code using `javac` command.
2. Run the byte code with the `java` command.

#### Scala
Use sbt for for project building.

__scalac__ for directly compiling the file might not work. The codebase uses scala 2.11.x which supports scala.read.StdIn I/O API.

1. To start __sbt__ console run `sbt` in terminal. This opens an SBT console
2. To run the code, use command `run`
3. Choose between the two java and scala run options


`[1] game.java.ConnectFour`

`[2] game.scala.Game`

 Use these other commands also

  - `compile`
  - `test`
