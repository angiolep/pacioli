package bitspoke.accountancy.model

import org.scalatest.{Matchers, FlatSpec}
import org.joda.time.DateTime


class SaleSpec extends FlatSpec with Matchers {

  def fixture =
    {
      new {
        val seller: Company = Company("seller")
        val buyer: Company = Company("buyer")

        val invoice0 = Invoice(seller, DateTime.now, buyer)

        val invoice1 = Invoice(seller, DateTime.now, buyer)
        invoice1.add(LineItem("description", 5, GBP("100.00"), RATE("0.20")))
        invoice1.issue()

        val shareholder1 = Shareholder(Person("person1"))

      }
    }

  "A Sale" should "require a not empty draft invoice" in {
    val f = fixture; import f._
    val thrown1 = the [IllegalArgumentException] thrownBy Sale(null)
    thrown1.getMessage should endWith ("invoice required")

    val thrown2 = the [IllegalArgumentException] thrownBy Sale(invoice0)
    thrown2.getMessage should endWith ("not empty invoice required")
  }

}
