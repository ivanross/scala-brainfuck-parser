import org.scalatest._
import io.Source
import com.ivanross.brainfuck.{
  BrainfuckParser => Parser,
  BrainfuckRunner => Runner,
  BrainfuckMinifier => Minifier
}

class BrainfuckTest extends FlatSpec with Matchers {
  val script: String =
    Source
      .fromURL(getClass.getResource("hello_world.b"))
      .mkString

  "Parser" should "print hello world" in {
    Runner(Parser(script)) should be("Hello World!\n")
  }

  "Parser" should "minify code" in {
    Minifier(Parser(">>++,[ tests <->-].")) should be(">>++,[<->-].")
  }

  it should "use implicit convertion" in {
    import com.ivanross.brainfuck.BrainfuckParser._
    Runner(script) should be("Hello World!\n")
  }

}
