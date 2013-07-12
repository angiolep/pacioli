package bitspoke.accountancy.model

import scala.BigDecimal
import org.joda.time.DateTime


class DataFixture {

  val bitSpoke = Company("BitSpoke Ltd")
  val astburyMarsden = Company("Astbury Marsden")
  val crunch = Company("Crunch")

  val invoice1 = bitSpoke.sell(DateTime.now, astburyMarsden)
  invoice1.addItem("item1", 1, BigDecimal("800.67"), BigDecimal("0.10"))
  invoice1.addItem("item2", 3, BigDecimal("100.56"), BigDecimal("0.10"))
  invoice1.issue()

  // bitSpoke.addExpense(crunch, "receipt", List(expense1Item1, expense1Item2))
}
