package bitspoke.accountancy.model

import org.scalatest.{FlatSpec}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class LineItemSpec extends FlatSpec {

  "A LineItem" should "have its calculated properties passed" in {

    val item1 = new LineItem("description", 5, BigDecimal("100.00"), BigDecimal("0.20"))

    assertResult(item1.description)("description")
    assertResult(item1.quantity)(5)
    assertResult(item1.unitPrice)(BigDecimal("100.00"))
    assertResult(item1.vatRate)(BigDecimal("0.20"))

    assertResult(BigDecimal("500.00"))(item1.net)
    assertResult(BigDecimal("100.00"))(item1.vat)
    assertResult(BigDecimal("600.00"))(item1.gross)
  }
}
