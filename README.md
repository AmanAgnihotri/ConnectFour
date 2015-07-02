Connect Four

A two-player game in which the players first choose a color and then take turns dropping colored discs from the top into a seven-column, six-row vertically suspended grid. The pieces fall straight down, occupying the next available space within the column. The object of the game is to connect four of one's own discs of the same color next to each other vertically, horizontally, or diagonally before your opponent.

The game is console based, and is implemented in Scala.

###### Instruction

Use sbt for for project building.

__scalac__ for directly compiling the file might not work. The codebase uses scala 2.11.x which supports scala.read.StdIn I/O API.

1. To start __sbt__ console run `sbt` in terminal
2. Use the following commands

  - `compile`
  - `run`
  - `test`
