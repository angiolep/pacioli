package bitspoke.pacioli

import org.joda.time.DateTime


class Data {

  private def d(str:String) = DateTime.parse(str)

  implicit val currency = Currency("GBP")
  private def m(str:String) = Money(str)

  val emp1 = Employee("Paolo Angioletti")
  val fy1314 = FiscalYear("1314")

  // --------- { 2013/2014 } ---------

  val paystm1 = PayStatement(emp1, d("2013-07-02"), m("641.33"), fy1314)
  val paystm2 = PayStatement(emp1, d("2013-07-02"), m("641.33"), fy1314)
  val paystm3 = PayStatement(emp1, d("2013-07-02"), m("641.33"), fy1314)
  val paystm4 = PayStatement(emp1, d("2013-08-01"), m("641.33"), fy1314)
  val paystm5 = PayStatement(emp1, d("2013-08-01"), m("641.33"), fy1314)
  val paystm6 = PayStatement(emp1, d("2013-08-01"), m("641.33"), fy1314)
  val paystm7 = PayStatement(emp1, d("2013-09-26"), m("641.33"), fy1314)
  val paystm8 = PayStatement(emp1, d("2013-09-26"), m("641.33"), fy1314)
  // TODO add more ...

  //val shareholder1 = Shareholder("Paolo Angioletti")

  //val dividend1 = Dividend(shareholder1, dateTime("2013-09-29"), BigDecimal("8002.50"))
  //val dividend2 = Dividend(shareholder1, dateTime("2013-09-30"), BigDecimal("8002.50"))
  //val dividend3 = Dividend(shareholder1, dateTime("2013-12-04"), BigDecimal("8002.50"))
  //val dividend4 = Dividend(shareholder1, dateTime("2013-12-04"), BigDecimal("8002.50"))


  // --------------

  //val bitSpoke = Company("BitSpoke Ltd")
  //val astburyMarsden = Company("Astbury Marsden & Partners Limited")
  //val crunch = Company("Crunch")

  //val standardVatRate = BigDecimal("0.2")
  //val descr1 = "Software development daily services provided to Barclays Capital during the week"
  //val descr2 = "  year 2013 which ends on "

  //val invoice1 = bitSpoke.openDraftInvoice(dateTime("2013-07-04"), astburyMarsden)
  //val unitPrice1 = BigDecimal("487.50")
  //invoice1.add(LineItem(descr1 + "#24" + descr2 + "16-Jun", 5, unitPrice1, standardVatRate))
  //invoice1.add(LineItem(descr1 + "#25" + descr2 + "23-Jun", 5, unitPrice1, standardVatRate))
  //invoice1.add(LineItem(descr1 + "#26" + descr2 + "30-Jun", 5, unitPrice1, standardVatRate))
  //invoice1.add(LineItem(descr1 + "#27" + descr2 + "04-Jun", 5, unitPrice1, standardVatRate))

}

