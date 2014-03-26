package bitspoke.pacioli

import org.specs2.mutable._


class MoneySpec extends Specification {

  val GBP = ISO4217("GBP")
  val USD = ISO4217("USD")

  val m1 = Money("100.1267", GBP)
  val m2 = Money("100.1267", GBP)
  val m3 = Money("100.1267", USD)

  "Money" should {

    "round amount towards the nearest neighbor" in {
      m1.round.toString must beEqualTo("£100.13")
    }

    "add augend with same currency" in {      
      (m1 + m2).toString must beEqualTo("£200.2534")
    }

    "throw exception when adds augend in different currency" in {            
      (m1 + m3).toString must throwA[CurrencyMismatchException]
    }

    "subtract subtrahend with same currency" in {      
      (m1 - m2).toString must beEqualTo("£0.0000")
    }

    "throw exception when subtracts subtrahend in different currency" in {            
      (m1 - m3).toString must throwA[CurrencyMismatchException]
    }
  }
}
