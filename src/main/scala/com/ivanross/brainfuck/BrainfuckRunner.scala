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

private class BrainfuckRunner(private val memorySize: Int) {
  var memory = new Array[Byte](memorySize)
  var ptr = 0
  var output = List[Char]()

  def run(statements: List[BrainfuckToken]): Unit = statements foreach {
    case IncrementCell       => incrementCell
    case DecrementCell       => decrementCell
    case IncrementPtr        => incrementPointer
    case DecrementPtr        => decrementPointer
    case Print               => printChar
    case Read                => readByte
    case Loop(tokens)        => loop(tokens)
    case Comment(char: Char) => skip
  }

  def incrementPointer: Unit = ptr += 1
  def decrementPointer: Unit = ptr -= 1

  def incrementCell: Unit = memory(ptr) = (memory(ptr) + 1).toByte
  def decrementCell: Unit = memory(ptr) = (memory(ptr) - 1).toByte

  def printChar: Unit = output = output :+ memory(ptr).toChar
  def readByte: Unit = memory(ptr) = scala.io.StdIn.readChar.toByte

  def skip: Unit = return

  def loop(statements: List[BrainfuckToken]) =
    while (memory(ptr) != 0) run(statements)
}
