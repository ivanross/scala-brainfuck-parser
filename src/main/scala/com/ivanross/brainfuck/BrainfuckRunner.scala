package com.ivanross.brainfuck

object BrainfuckRunner {
  def apply(
      statements: List[BrainfuckToken],
      memorySize: Int = 30000
  ): String = {
    val parser = new BrainfuckRunner(memorySize)
    parser run statements
    parser.output.mkString
  }
}

private class BrainfuckRunner(memorySize: Int) extends RunnerMemory {

  var memory = new Array(memorySize)
  var output = List[Char]()

  def run(statements: List[BrainfuckToken]): Unit = statements foreach {
    case IncrementCell       => incrementCell
    case DecrementCell       => decrementCell
    case IncrementPtr        => incrementPointer
    case DecrementPtr        => decrementPointer
    case Print               => printChar
    case Read                => readByte
    case Loop(tokens)        => loop(tokens)
    case Comment(char: Char) => ()
  }

  def printChar: Unit = output = output :+ readMemory.toChar
  def readByte: Unit = writeMemory(scala.io.StdIn.readChar.toByte)

  def loop(statements: List[BrainfuckToken]) =
    while (readMemory != 0) run(statements)
}
