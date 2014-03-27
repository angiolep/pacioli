package bitspoke.pacioli

import org.specs2.mutable._


class MoneySpec extends Specification {

  implicit val GBP = Currency("GBP")
  val m1 = Money("100.1267")
  val m2 = Money("100.1267")

  "Money" should {

    "provide equality by currency and amount" in {
      Money("100.1267") must be equalTo(Money("100.1267")) and not be equalTo(Money("100.13")) // TODO and not be equalTo(Money("100.1267"), Currency("USD"))
    }

    "round amount towards the nearest neighbor" in {
      m1.round must be equalTo(Money("100.13"))
    }

    "add augend with same currency" in {      
      (m1 + m2) must be equalTo(Money("200.2534"))
    }

    /*"throw exception when adds augend in different currency" in {
      implicit val USD = Currency("USD")
      val m3 = Money("100.1267")
      (m1 + m3) must throwA[CurrencyMismatchException]
    }*/

    "subtract subtrahend with same currency" in {      
      (m1 - m2) must be equalTo(Money("0.0000"))
    }

    /* TODO "throw exception when subtracts subtrahend in different currency" in {
      (m1 - m3) must throwA[CurrencyMismatchException]
    }*/
  }
}
