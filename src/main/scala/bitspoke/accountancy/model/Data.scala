package bitspoke.accountancy.model

import org.joda.time.DateTime

class Data {

  val paoloAngioletti = Person("Paolo Angioletti")
  val employee1 = Employee(paoloAngioletti)
  val f2013_2014 = "2013/2014"

  // --------- { 2013/2014 } ---------

  val salary1 = Salary(employee1, /*30-Apr*/1,  f2013_2014, DateTime.parse("2013-07-02"), BigDecimal("641.33"))
  val salary2 = Salary(employee1, /*31-May*/2,  f2013_2014, DateTime.parse("2013-07-02"), BigDecimal("641.33"))
  val salary3 = Salary(employee1, /*30-Jun*/3,  f2013_2014, DateTime.parse("2013-07-02"), BigDecimal("641.33"))
  val salary4 = Salary(employee1, /*31-Jul*/4,  f2013_2014, DateTime.parse("2013-08-01"), BigDecimal("641.33"))
  val salary5 = Salary(employee1, /*31-Aug*/5,  f2013_2014, DateTime.parse("2013-08-01"), BigDecimal("641.33"))
  val salary6 = Salary(employee1, /*30-Sep*/6,  f2013_2014, DateTime.parse("2013-08-01"), BigDecimal("641.33"))
  val salary7 = Salary(employee1, /*31-Oct*/7,  f2013_2014, DateTime.parse("2013-09-26"), BigDecimal("641.33"))
  val salary8 = Salary(employee1, /*30-Nov*/8,  f2013_2014, DateTime.parse("2013-09-26"), BigDecimal("641.33"))
  // val salary9 = Salary(employee1, /*Dec*/9,  f2013_2014, DateTime.parse("2013-09-26"), BigDecimal("641.33"))

  // val salary10 = Salary(employee1, /*Jan*/10,  f2013_2014, DateTime.parse("2013-mm-dd"), BigDecimal("641.33"))
  // val salary11 = Salary(employee1, /*Feb*/11,  f2013_2014, DateTime.parse("2013-mm-dd"), BigDecimal("641.33"))
  // val salary12 = Salary(employee1, /*Mar*/12,  f2013_2014, DateTime.parse("2013-mm-dd"), BigDecimal("641.33"))

  val shareholder1 = Shareholder(paoloAngioletti)

  val dividend1 = Dividend(shareholder1, DateTime.parse("2013-09-29"), BigDecimal("8002.50"))
  val dividend2 = Dividend(shareholder1, DateTime.parse("2013-09-30"), BigDecimal("8002.50"))
  val dividend3 = Dividend(shareholder1, DateTime.parse("2013-12-02"), BigDecimal("8002.50"))
  val dividend4 = Dividend(shareholder1, DateTime.parse("2013-12-02"), BigDecimal("8002.50"))


  // --------------

  val bitSpoke = Company("BitSpoke Ltd")
  val astburyMarsden = Company("Astbury Marsden & Partners Limited")
  val crunch = Company("Crunch")

  val standardVatRate = BigDecimal("0.2")
  val descr1 = "Software development daily services provided to Barclays Capital during the week"
  val descr2 = "  year 2013 which ends on "

  val invoice1 = bitSpoke.openDraftInvoice(DateTime.parse("2013-07-04"), astburyMarsden)
  val unitPrice1 = BigDecimal("487.50")
  invoice1.add(LineItem(descr1 + "#24" + descr2 + "16-Jun", 5, unitPrice1, standardVatRate))
  invoice1.add(LineItem(descr1 + "#25" + descr2 + "23-Jun", 5, unitPrice1, standardVatRate))
  invoice1.add(LineItem(descr1 + "#26" + descr2 + "30-Jun", 5, unitPrice1, standardVatRate))
  invoice1.add(LineItem(descr1 + "#27" + descr2 + "04-Jun", 5, unitPrice1, standardVatRate))

}

