package bitspoke.accountancy.model


import org.scalatest.{FunSpec, Matchers}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.joda.time.DateTime
import scala._


@RunWith(classOf[JUnitRunner])
class CompanySpec extends FunSpec with Matchers {

  describe ("A Company") {

    describe("incorporated just now") {
      it("should require a name") {
        intercept[IllegalArgumentException] { Company(null) }
        intercept[IllegalArgumentException] { Company("") }
        intercept[IllegalArgumentException] { Company(" ") }
      }
      it("should have no sales") {
        val seller = Company("seller")
        seller.sales.size should be (0)
      }
      it ("should have no expenses") {
        // TODO seller.expenses.size should be (0)
      }
    }

    describe("acting as a seller") {
      val seller = Company("seller")
      val date = DateTime.now
      val buyer = Company("buyer")

      var invoice1:Invoice = null
      it ("should create its first draft invoice") {
        invoice1 = seller.createInvoice(date, buyer)
        invoice1.items should have size (0)
        invoice1 should be ('draft)
        invoice1.number should be (-1) // it's a draft state
        invoice1.buyer should be (buyer)
        invoice1.date should be (date)
      }

      it("should add line items to the draft invoice") {
        val item1 = invoice1.addItem("description", 5, BigDecimal("100.00"), BigDecimal("0.20"))
        item1.description should be ("description")
        item1.quantity should be (5)
        item1.unitPrice should be (BigDecimal("100.00"))
        item1.vatRate should be (BigDecimal("0.20"))
        item1.net should be (BigDecimal("500.00"))
        item1.vat should be (BigDecimal("100.00"))
        item1.gross should be (BigDecimal("600.00"))

        invoice1.addItem("description", 1, BigDecimal("50.00"), BigDecimal("0.10"))
        invoice1.items should have size(2)

        invoice1.net should be (BigDecimal("550.00"))
        invoice1.vat should be (BigDecimal("105.00"))
        invoice1.gross should be (BigDecimal("655.00"))
      }

      var invoice2:Invoice = null
      it ("should create its second draft invoice") {
        invoice2 = seller.createInvoice(date, buyer)
        seller.draftInvoices should have size(2)
      }

      it("should issue the first draft invoice with number 1") {
        seller.issueInvoice(invoice1)
        invoice1 should not be ('draft)
        invoice1.number should be (1)
      }

      it("should reject further issue commands for the first invoice") {
        intercept[RuntimeException] {
          seller.issueInvoice(invoice1)
        }
      }

      it("should reject any issue commands for the second invoice (because still empty)") {
        intercept[RuntimeException] {
          seller.issueInvoice(invoice2)
        }
      }

      it("should reject any update commands for the first invoice (because issued)") {
        intercept[RuntimeException] {
          invoice1.addItem("description", 1, BigDecimal("50.00"), BigDecimal("0.10"))
        }
        // TODO other updates ...
      }
    }
  }
}
