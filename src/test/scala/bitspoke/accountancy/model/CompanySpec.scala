package bitspoke.accountancy.model


import org.scalatest.{FunSpec, Matchers}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.joda.time.DateTime
import scala._


@RunWith(classOf[JUnitRunner])
class CompanySpec extends FunSpec with Matchers {

  describe ("A Company") {

    it("should require a name") {
      intercept[IllegalArgumentException] { Company(null) }
      intercept[IllegalArgumentException] { Company("") }
      intercept[IllegalArgumentException] { Company(" ") }
    }

    describe("when incorporated") {
      it("should have no sales") {
        val seller = Company("seller")
        seller.sales.size should be (0)
      }
      it ("should have no expenses") {
        // TODO seller.expenses.size should be (0)
      }
    }

    describe("when sells") {
      val seller = Company("seller")
      val date = DateTime.now
      val buyer = Company("buyer")
      val invoice = seller.sell(date, buyer)

      it ("should open a new empty draft invoice") {
        invoice.items should have size (0)
        invoice should be ('draft)
        invoice.number should be ("1") // it's the first invoice
        invoice.buyer should be (buyer)
        invoice.date should be (date)
      }

      it("should add line items to a draft invoice") {
        val item1 = invoice.addItem("description", 5, BigDecimal("100.00"), BigDecimal("0.20"))
        item1.description should be ("description")
        item1.quantity should be (5)
        item1.unitPrice should be (BigDecimal("100.00"))
        item1.vatRate should be (BigDecimal("0.20"))
        item1.net should be (BigDecimal("500.00"))
        item1.vat should be (BigDecimal("100.00"))
        item1.gross should be (BigDecimal("600.00"))

        invoice.addItem("description", 1, BigDecimal("50.00"), BigDecimal("0.10"))
        invoice.items should have size(2)

        invoice.net should be (BigDecimal("550.00"))
        invoice.vat should be (BigDecimal("105.00"))
        invoice.gross should be (BigDecimal("655.00"))
      }

      // TODO "should

      it("should issue a draft invoice") {
        invoice.issue()
        invoice should not be ('draft)
      }

      it("should reject updates on an issued invoice") {
        intercept[RuntimeException] {
          invoice.addItem("description", 1, BigDecimal("50.00"), BigDecimal("0.10"))
        }
      }
    }
  }
}
