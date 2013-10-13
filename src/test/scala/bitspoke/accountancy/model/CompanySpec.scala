package bitspoke.accountancy.model


import org.scalatest.{FunSpec, Matchers}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.joda.time.DateTime
import scala._


@RunWith(classOf[JUnitRunner])
class CompanySpec extends FunSpec with Matchers {

  describe ("A Company") {

    describe("being incorporated today") {
      it("should require a name") {
        intercept[IllegalArgumentException] { Company(null) }
        intercept[IllegalArgumentException] { Company("") }
        intercept[IllegalArgumentException] { Company(" ") }
      }
      it("should have no sales") {
        Company("anyName").sales.size should be (0)
      }
      it ("should have no expenses") {
        // TODO Company("anyName").expenses.size should be (0)
      }
    }

    describe("acting as a seller") {
      val seller = Company("seller")
      val now = DateTime.now
      val buyer = Company("buyer")

      var sale1:Sale = null
      it ("should open a new sale with a draft invoice") {
        sale1 = seller.openSale(now, buyer)
        sale1.invoice.items should have size (0)
        sale1.invoice should be ('draft)
        sale1.invoice.number should be (-1) // it's a draft state
        sale1.invoice.buyer should be (buyer)
        sale1.invoice.date should be (now)
      }

      it("should add some line items to the draft invoice") {
        sale1.invoice.addLineItem("description", 5, BigDecimal("100.00"), BigDecimal("0.20"))
        sale1.invoice.addLineItem("description", 1, BigDecimal("50.00"), BigDecimal("0.10"))
        sale1.invoice.items should have size(2)
      }

      var sale2:Sale = null
      var invoice2:Invoice = null
      it ("should open more empty sales") {
        sale2 = seller.openSale(now, buyer)
        invoice2 = sale2.invoice
        seller.openSale(now, buyer)
        seller.draftInvoices should have size(3)
      }

      it("should close the first (not empty) sale") {
        seller.closeSale(sale1)
        sale1.invoice should not be ('draft)
        sale1.invoice.number should be (1)
      }

      // TODO write proper specifications
      /*
      it("should reject any close request for already closed invoices") {
        intercept[RuntimeException] {
          seller.closeSale(sale1)
        }
      }

      it("should reject any close request for empty invoices") {
        intercept[RuntimeException] {
          seller.closeSale(sale2)
        }
      }

      it("should have revenue passed") {
        seller.revenue should be (BigDecimal("550.00"))
      }
      */
    }
  }
}
