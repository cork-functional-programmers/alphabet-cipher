At the root of the project, run `sbt console` to start a Scala REPL.
Here are some examples:

```
scala> import cipher._
import cipher._

scala> encode("scones", "meetmebythetree")
res0: scala.util.Try[String] = Success(egsgqwtahuiljgs)

scala> encode("scones", "meetmebythetree").flatMap(decode("scones", _))
res1: scala.util.Try[String] = Success(meetmebythetree)

scala> encode("sco  nes", "meetmebythetree")
res2: scala.util.Try[String] = Failure(java.lang.Exception: invalid input: 'sco  nes')

scala> encode("scones", "meetmeb   ythetree")
res3: scala.util.Try[String] = Failure(java.lang.Exception: invalid input: 'meetmeb   ythetree')

scala> encode("sco   nes", "meetmeb   ythetree")
res4: scala.util.Try[String] = Failure(java.lang.Exception: invalid input: 'sco   nes')
```
