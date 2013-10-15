package bitspoke.accountancy.model

import org.scalatest.{Matchers, FlatSpec}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.joda.time.DateTime


@RunWith(classOf[JUnitRunner])
class InvoiceSpec extends FlatSpec with Matchers {

  val invoice = Invoice(Company("seller"), DateTime.now, Company("buyer"))

   "An Invoice" should "have net, vat and gross calculated properly" in {

     invoice.addLineItem(new LineItem("description", 5, GBP("100.00"), RATE("0.20")))
     invoice.addLineItem(new LineItem("description", 1, GBP("50.00"), RATE("0.10")))

     invoice.net should be (GBP("550.00"))
     invoice.vat should be (GBP("105.00"))
     invoice.gross should be (GBP("655.00"))
   }


   it should "have a number only when issued" in {
     invoice.number should be (None)
     invoice.issue()
     invoice.number should be (Some(1))
   }
 }
