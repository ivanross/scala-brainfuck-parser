package com.ivanross.brainfuck

object BrainfuckMinifier {
  def apply(brainfuckAST: List[BrainfuckToken]): String =
    brainfuckAST map {
      case IncrementCell => "+"
      case DecrementCell => "-"
      case IncrementPtr  => ">"
      case DecrementPtr  => "<"
      case Print         => "."
      case Read          => ","
      case Loop(ls)      => "[" + BrainfuckMinifier(ls) + "]"
    } reduce (_ + _)
}
