package bitspoke.accountancy.model

import org.scalatest.{Matchers, FlatSpec}
import org.joda.time.DateTime


class ShareholderSpec extends FlatSpec with Matchers {

  val shareholder = Shareholder("shareholder")

  val invoice1 = Invoice(Company("seller"), DateTime.now, Company("buyer"))
  invoice1.addLineItem(new LineItem("description", 5, GBP("100.00"), RATE("0.20")))
  invoice1.addLineItem(new LineItem("description", 1, GBP("50.00"), RATE("0.10")))
  invoice1.issue()

  val sale1 = Sale(invoice1)
  sale1.assignTo(shareholder)


  "A Shareholder" should " have turnover resulting from assigned sales" in {
    shareholder.turnover should be (GBP("550.00"))
  }


  it should " have corportion tax resulting from assigned sales" in {
    shareholder.corporationTax should be (GBP("110.00"))
  }
}
