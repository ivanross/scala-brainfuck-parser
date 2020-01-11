package com.ivanross.brainfuck

object BrainfuckRunner {
  var buffer = new Array[Byte](30000)
  var ptr = 0
  var output = List[Char]()

  def apply(statements: List[BrainfuckToken]): String = {
    run(statements)
    output.mkString
  }

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

  def incrementCell: Unit = buffer(ptr) = (buffer(ptr) + 1).toByte
  def decrementCell: Unit = buffer(ptr) = (buffer(ptr) - 1).toByte

  def printChar: Unit = output = output.:+(buffer(ptr).toChar)
  def readByte: Unit = buffer(ptr) = scala.io.StdIn.readChar.toByte

  def skip: Unit = return

  def loop(statements: List[BrainfuckToken]) =
    while (buffer(ptr) != 0) run(statements)
}
