package bitspoke.pacioli

import org.specs2.mutable._


class MoneySpec extends Specification {

  val m1 = Money("100.1267")
  val m2 = Money("100.1267")
  val m3 = Money("100.1267", "USD")

  "Money" should {
    "initialize GBP as default currency" in {
      m1.currency must beEqualTo("GBP")
    }
    "map currency to its scale" in {
      m1.scale must beEqualTo(2)
    }
    "map currency to its symbol" in {
      m1.symbol must beEqualTo("£")
    }
    "throw exception when unknown currency" in {
      Money("100.1267", "???") must throwA[UnknownCurrencyException]
    }
    "round amount towards the nearest neighbor" in {
      m1.toString must beEqualTo("£100.13")
    }
    "add augend with same currency" in {      
      (m1 + m2).toString must beEqualTo("£200.26")
    }
    "throw exception when adds augend in different currency" in {            
      (m1 + m3).toString must throwA[CurrencyMismatchException]
    }
    "subtract subtrahend with same currency" in {      
      (m1 - m2).toString must beEqualTo("£0.00")
    }
    "throw exception when subtracts subtrahend in different currency" in {            
      (m1 - m3).toString must throwA[CurrencyMismatchException]
    }
  }
}