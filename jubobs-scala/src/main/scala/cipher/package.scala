import scala.util.{Failure, Try}

package object cipher {

  sealed trait Action

  case object Encode extends Action

  case object Decode extends Action

  val encode: (String, String) => Try[String] = translate(Encode)

  val decode: (String, String) => Try[String] = translate(Decode)

  private def translate(action: Action)(keyword: String, message: String) =
    for {
      validKeyword <- validate(keyword)
      validMessage <- validate(message)
    } yield unsafeTranslate(action)(validKeyword, validMessage)

  private def validate(str: String) =
    Try(str)
        .filter { _.matches("[a-z]*") }
        .recoverWith { case _ => Failure(new Exception(s"invalid input: '$str'")) }

  private def unsafeTranslate(action: Action)(keyword: String, message: String) = {
    def toCharPairs(keyword: String, message: String) =
      Stream.continually(keyword).flatten
          .zip(message.toStream)

    toCharPairs(keyword, message)
        .map { (unsafeTranslate1(action) _).tupled }
        .mkString
  }


  private def unsafeTranslate1(action: Action)(key: Char, char: Char) = {
    val binop: Action => (Int, Int) => Int = {
      case Encode => { _ + _ }
      case Decode => { _ - _ }
    }

    asciiLowerFromInt(binop(action)(intFromChar(char), intFromChar(key)))
  }

  private def asciiLowerFromInt(i: Int) = {
    // Note: The result of Scala's modulo has the same sign as the dividend.
    def mod(a: Int, n: Int) = {
      val result = a % n
      if (result < 0) result + n else result
    }
    val alphabetLength = 26

    (mod(i, alphabetLength) + offset).asInstanceOf[Char]
  }

  private def intFromChar(c: Char) = c - offset

  private val offset = 'a'

}
