package com.ivanross.brainfuck

import scala.util.parsing.combinator._

class BrainfuckParserException(msg: String) extends Exception(msg)

object BrainfuckParser extends RegexParsers {

  override protected val whiteSpace = """[^\[\].,<>+-]+""".r

  implicit def stringToBrainfuck(input: String): List[BrainfuckToken] =
    BrainfuckParser(input)

  def statementList: Parser[List[BrainfuckToken]] = statement.*

  def statement = loop | token

  def loop: Parser[BrainfuckToken] = "[" ~> statementList <~ "]" ^^ {
    case ls => Loop(ls)
  }

  def token: Parser[BrainfuckToken] =
    ("+" | ">" | "<" | "-" | "." | ",") ^^ {
      case "+" => IncrementCell
      case "-" => DecrementCell
      case ">" => IncrementPtr
      case "<" => DecrementPtr
      case "." => Print
      case "," => Read
    }

  def apply(input: String) = parseAll(statementList, input) match {
    case Success(res, _) => res
    case failure: NoSuccess =>
      throw new BrainfuckParserException("Failure: " + failure.msg)
  }
}
