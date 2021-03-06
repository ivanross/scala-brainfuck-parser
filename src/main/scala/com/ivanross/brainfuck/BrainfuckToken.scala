package com.ivanross.brainfuck

sealed trait BrainfuckToken

case object IncrementCell extends BrainfuckToken
case object DecrementCell extends BrainfuckToken
case object IncrementPtr extends BrainfuckToken
case object DecrementPtr extends BrainfuckToken
case object Print extends BrainfuckToken
case object Read extends BrainfuckToken
case class Loop(token: List[BrainfuckToken]) extends BrainfuckToken
