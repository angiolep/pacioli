package bitspoke.accountancy.model

import org.scalatest.{Matchers, FlatSpec}


class LineItemSpec extends FlatSpec with Matchers {


  "A LineItem" should "require a non empty description" in  {
    for (illegalDescription <- Array(null, "", " ")) {
      an [IllegalArgumentException] should be thrownBy { LineItem(illegalDescription, 5, GBP("100.00"), RATE("0.22")) }
    }
    val item = LineItem("aDescription", 5, GBP("100.00"), RATE("0.22"))
    item.description should be ("aDescription")
  }

  it should "require a positive quantity" in  {
    an [IllegalArgumentException] should be thrownBy { LineItem("descr", 0, GBP("100.00"), RATE("0.22")) }
    an [IllegalArgumentException] should be thrownBy { LineItem("descr", -1, GBP("100.00"), RATE("0.22")) }

    val item1 = LineItem("descr", 13, GBP("0.00"), RATE("0.22"))
    item1.quantity should be (13)
  }


  it should "require a non negative unit price" in  {
    an [IllegalArgumentException] should be thrownBy { LineItem("descr", 5, GBP("-100.00"), RATE("0.22")) }

    val item1 = LineItem("descr", 5, GBP("0.00"), RATE("0.22"))
    item1.unitPrice should be (GBP("0.00"))

    val item2 = LineItem("descr", 5, GBP("150.00"), RATE("0.22"))
    item2.unitPrice should be (GBP("150.00"))
  }



  it should "require a non negative vatRate" in  {
    a [IllegalArgumentException] should be thrownBy { LineItem("descr", 5, GBP("100.00"), GBP("-0.18")) }

    val item1 = LineItem("descr", 5, GBP("100.00"), GBP("0.00"))
    item1.vatRate should be (GBP("0.00"))

    val item2 = LineItem("descr", 5, GBP("100.00"), GBP("0.15"))
    item2.vatRate should be (GBP("0.15"))
  }




  it should "have net, vat and gross calculated properly" in {
    val item1 = LineItem("descr", 5, GBP("100.00"), GBP("0.20"))

    item1.net should be (GBP("500.00"))
    item1.vat should be (GBP("100.00"))
    item1.gross should be (GBP("600.00"))
  }
}
