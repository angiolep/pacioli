package bitspoke.accountancy.model

import org.scalatest.FunSpec
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith


@RunWith(classOf[JUnitRunner])
class SaleSpec extends FunSpec {

  describe("A newly created Sale") {

    it("should be linked to a seller company") {

      val seller = Company("seller")
      val buyer = Company("buyer")

      val sale1 = Sale(seller, "001", buyer, List(SoldItem("item1", 5, BigDecimal("102.78"),  Option(BigDecimal("0.20")))))
      assert (sale1.seller.sales!=null && sale1.seller.sales.size==1)

      val sale2 = Sale(seller, "002", buyer, List(SoldItem("item2", 2, BigDecimal("23.34"))))
      assert (sale2.seller.sales.size==2)
    }
  }


}
