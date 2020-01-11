import com.ivanross.brainfuck._
import com.ivanross.brainfuck.BrainfuckParser._

object Main extends App {
  val script =
    scala.io.Source.fromFile("src/main/resources/test.b").mkString

  println(BrainfuckParser(script))

}
