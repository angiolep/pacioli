package bitspoke.accountancy.model

import scala.BigDecimal
import org.joda.time.DateTime


class DataFixture {

  val bitSpoke = Company("BitSpoke Ltd")
  val astburyMarsden = Company("Astbury Marsden & Partners Limited")
  val crunch = Company("Crunch")

  val StandardVatRate = BigDecimal("0.2")

  val Descr1 = "Software development daily services provided to Barclays Capital during the week"
  val Descr2 = "  year 2013 which ends on "
  val invoice1 = bitSpoke.createInvoice(DateTime.parse("2013-07-04"), astburyMarsden)
  val unitPrice1 = BigDecimal("487.50")
  invoice1.addItem(Descr1 + "#24" + Descr2 + "16-Jun", 5, unitPrice1, StandardVatRate)
  invoice1.addItem(Descr1 + "#25" + Descr2 + "23-Jun", 5, unitPrice1, StandardVatRate)
  invoice1.addItem(Descr1 + "#26" + Descr2 + "30-Jun", 5, unitPrice1, StandardVatRate)
  invoice1.addItem(Descr1 + "#27" + Descr2 + "04-Jun", 5, unitPrice1, StandardVatRate)
  bitSpoke.issueInvoice(invoice1)

  // bitSpoke.addExpense(crunch, "receipt", List(expense1Item1, expense1Item2))
}
