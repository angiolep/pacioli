package bitspoke.accountancy.model

import org.scalatest.FlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.joda.time.DateTime


@RunWith(classOf[JUnitRunner])
class InvoiceSpec extends FlatSpec {

   "An Invoice" should "have its calculated properties passed" in {

     val invoice = Invoice(Company("seller"), DateTime.now, Company("seller"))
     invoice.addLineItem("description", 5, BigDecimal("100.00"), BigDecimal("0.20"))
     invoice.addLineItem("description", 1, BigDecimal("50.00"), BigDecimal("0.10"))

     assertResult(BigDecimal("550.00"))(invoice.net)
     assertResult(BigDecimal("105.00"))(invoice.vat)
     assertResult(BigDecimal("655.00"))(invoice.gross)
   }


   "An Invoice" should "be issued with a number" in {
     val invoice = Invoice(Company("seller"), DateTime.now, Company("seller"))
     invoice.addLineItem("description", 5, BigDecimal("100.00"), BigDecimal("0.20"))
     invoice.issue()

     assertResult(1)(invoice.number)
   }
 }
