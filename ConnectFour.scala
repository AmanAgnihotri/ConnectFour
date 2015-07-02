class ConnectFour {

  case class Tile(val color: Char)

  object Tile {
    final val EMPTY = Tile(' ')
    final val RED = Tile('R')
    final val YELLOW = Tile('Y')
  }

  final private val ROWS: Int = 7
  final private val COLUMNS: Int = 6

  final private val CONSECUTIVE_CONNECTION_REQUIRED: Int = 4

  private var grid: Array[Array[Tile]] = Array.fill(ROWS, COLUMNS)(Tile.EMPTY)

  private var fillerIndex: Array[Int] = Array.fill(COLUMNS)(ROWS-1)

  def startGame: Unit = {
    var (hasRedWon, hasYellowWon) = (false, false)

    def check(tile: Tile): Boolean = {
      println(gridToString)
      getAndInsertTile(tile)
      if(checkGrid(tile)) true else false
    }

    while (!hasRedWon && !hasYellowWon && !isGridFull) {
      hasRedWon = check(Tile.RED)
      if (!hasRedWon) hasYellowWon = check(Tile.YELLOW)
    }

    println(gridToString)

    (hasRedWon, hasYellowWon) match {
      case (true, false) => println("The RED Player has won")
      case (false, true) => println("The YELLOW Player has won")
      case (false, false) => println("Game is a DRAW")
      case (true, true) =>
    }
  }

  def isGridFull: Boolean = fillerIndex.filter(_ < 0).size == COLUMNS

  def checkGrid(tile: Tile): Boolean = {
    var (i, j, k, m, counter) = (0, 0, 0, 0, 0)

    // Check downward
    for (
    i <- 0 to ROWS - CONSECUTIVE_CONNECTION_REQUIRED;
    j <- 0 until COLUMNS)
    {
      if ((i until CONSECUTIVE_CONNECTION_REQUIRED + i).count(
        grid(_)(j) equals tile
      ) == CONSECUTIVE_CONNECTION_REQUIRED)
        return true
    }

    // Check across
    for (
    i <- 0 to COLUMNS - CONSECUTIVE_CONNECTION_REQUIRED;
    j <- 0 until ROWS)
    {
      if ((i until CONSECUTIVE_CONNECTION_REQUIRED + i).count(
        grid(j)(_) equals tile
      ) == CONSECUTIVE_CONNECTION_REQUIRED)
        return true
    }

    // Check left to right diagonally
    for (
    i <- 0 to ROWS - CONSECUTIVE_CONNECTION_REQUIRED;
    j <- 0 to COLUMNS - CONSECUTIVE_CONNECTION_REQUIRED)
    {
      if ((i until CONSECUTIVE_CONNECTION_REQUIRED + i)
        .zip(j until CONSECUTIVE_CONNECTION_REQUIRED + j)
        .count { case (k, m) =>
          grid(k)(m) equals tile
        } == CONSECUTIVE_CONNECTION_REQUIRED)
        return true
    }

    // Check right to left diagonally
    for (
    i <- 0 to ROWS - CONSECUTIVE_CONNECTION_REQUIRED;
    j <- (COLUMNS - 1) until (COLUMNS - CONSECUTIVE_CONNECTION_REQUIRED) by -1)
    {
      if ((i until CONSECUTIVE_CONNECTION_REQUIRED + i)
        .zip(j until j - CONSECUTIVE_CONNECTION_REQUIRED by -1)
        .count { case (k, m) =>
          grid(k)(m) equals tile
        } == CONSECUTIVE_CONNECTION_REQUIRED)
        return true
    }

    return false;
  }

  def getAndInsertTile(tile: Tile): Unit = {
    tile match {
      case Tile.RED => println("Enter position to drop the RED tile")
      case Tile.YELLOW => println("Enter position to drop the YELLOW tile")
    }

    var position = 0
    do (position = scala.io.StdIn.readInt) while (!isValid)
    def isValid: Boolean = position >= 0 && position < COLUMNS &&
      fillerIndex(position) >= 0

    grid(fillerIndex(position))(position) = tile
    fillerIndex(position) -= 1
  }

  def printGrid: String = println(grid.map(
    "|" + _.map(_.color).mkString("|") + "|"
  ).mkString("\n"))

}

object Game {

  def main(args: Array[String]): Unit = {
    (new ConnectFour()).startGame
  }

}
