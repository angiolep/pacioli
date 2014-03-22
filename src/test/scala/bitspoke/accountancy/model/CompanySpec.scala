package bitspoke.accountancy.model


import org.scalatest.{FunSpec, Matchers}
import org.joda.time.DateTime


class CompanySpec extends FunSpec with Matchers {

  describe ("A Company") {

    describe("being incorporated today") {
      it("should require a non empty name") {
        for (name <- Array(null, "", "  ")) {
          a [IllegalArgumentException] should be thrownBy { Company(name) }
        }
      }
      it("should have no sales") {
        Company("anyName").invoices.size should be (0)
      }
      it ("should have no expenses") {
        // TODO Company("anyName").expenses.size should be (0)
      }
    }

    describe("acting as a seller") {
      val seller = Company("seller")
      val now = DateTime.now
      val buyer = Company("buyer")

      var invoice1:Invoice = null
      it ("should open a draft invoice") {
        invoice1 = seller.openDraftInvoice(now, buyer)
        invoice1 should be ('draft)
        invoice1.number should be (None)
        invoice1.date should be (now)
        invoice1.buyer should be (buyer)
        invoice1.items should have size (0)
      }

      it("should add line items to a draft invoice") {
        invoice1 add LineItem("description", 5, GBP("100.00"), RATE("0.20"))
        invoice1 add LineItem("description", 1, GBP("50.00"), RATE("0.10"))
        invoice1.items should have size(2)
      }

      var invoice2:Invoice = null
      it ("should open more draft invoices") {
        invoice2 = seller.openDraftInvoice(now, buyer)
        seller.openDraftInvoice(now, buyer)
        seller.draftInvoices should have size(3)
      }
    }
  }
}
