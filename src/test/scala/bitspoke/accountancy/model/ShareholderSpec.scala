package bitspoke.accountancy.model

// import org.scalatest.{Matchers, FlatSpec}
import org.joda.time.DateTime
import bitspoke.pacioli.Person


class ShareholderSpec /*extends FlatSpec with Matchers*/ {

  /*def fixture =
    new {
      val shareholder0 = Shareholder(Person("person0"))
      val shareholder1 = Shareholder(Person("person1"))

      val invoice1 = Invoice(Company("seller"), DateTime.now, Company("buyer"))
      invoice1.add(LineItem("description", 5, GBP("100.00"), RATE("0.20")))
      invoice1.add(LineItem("description", 1, GBP("50.00"), RATE("0.10")))
      invoice1.issue()

      val sale1 = Sale(invoice1)
      sale1.assign(shareholder1)
  }


  "A Shareholder" should " have turnover resulting from assigned sales" in {
    val f = fixture; import f._
    shareholder0.turnover should be (GBP("0.00"))
    shareholder1.turnover should be (GBP("550.00"))
  }


  it should " have corporation tax resulting from assigned sales" in {
    val f = fixture; import f._
    shareholder1.corporationTax should be (GBP("110.00"))
  }*/
}
