package bitspoke.accountancy.model

import org.scalatest.{Matchers, FlatSpec}
import org.joda.time.DateTime


class InvoiceSpec extends FlatSpec with Matchers {


  def fixture() =
    new {
      val seller: Company = Company("seller")
      val buyer: Company = Company("buyer")
      val invoice0 = Invoice(seller, DateTime.now, buyer)
      val invoice1 = Invoice(seller, DateTime.now, buyer)
      invoice1 add LineItem("description", 5, GBP("100.00"), RATE("0.20"))
      invoice1 add LineItem("description", 1, GBP("50.00"), RATE("0.10"))
    }


  "An empty Invoice" should "not be issued" in {
    val f = fixture(); import f._
    the [IllegalArgumentException] thrownBy invoice0.issue() should have message ("requirement failed: Invoice with items required")
  }


  "An Invoice with items" should "have net, vat and gross calculated properly" in {
    val f = fixture(); import f._
    invoice1.net should be(GBP("550.00"))
    invoice1.vat should be(GBP("105.00"))
    invoice1.gross should be(GBP("655.00"))
  }


  it should "have a number only when issued" in {
    val f = fixture(); import f._
    invoice1.number should be(None)
    invoice1.issue()
    invoice1.number should be(Some(1))
  }
}
