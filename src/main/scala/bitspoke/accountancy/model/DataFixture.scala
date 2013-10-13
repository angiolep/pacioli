package bitspoke.accountancy.model

import scala.BigDecimal
import org.joda.time.DateTime


class DataFixture {

  val bitSpoke = Company("BitSpoke Ltd")
  val astburyMarsden = Company("Astbury Marsden & Partners Limited")
  val crunch = Company("Crunch")

  val standardVatRate = BigDecimal("0.2")
  val descr1 = "Software development daily services provided to Barclays Capital during the week"
  val descr2 = "  year 2013 which ends on "

  val sale1 = bitSpoke.openSale(DateTime.parse("2013-07-04"), astburyMarsden)
  val invoice1 = sale1.invoice
  val unitPrice1 = BigDecimal("487.50")
  invoice1.addLineItem(descr1 + "#24" + descr2 + "16-Jun", 5, unitPrice1, standardVatRate)
  invoice1.addLineItem(descr1 + "#25" + descr2 + "23-Jun", 5, unitPrice1, standardVatRate)
  invoice1.addLineItem(descr1 + "#26" + descr2 + "30-Jun", 5, unitPrice1, standardVatRate)
  invoice1.addLineItem(descr1 + "#27" + descr2 + "04-Jun", 5, unitPrice1, standardVatRate)
  bitSpoke.closeSale(sale1)

  // bitSpoke.addExpense(crunch, "receipt", List(expense1Item1, expense1Item2))
}
