package bitspoke.accountancy.model

import org.joda.time.DateTime


class Salary(val employee:Employee, val monthNo:Integer, val fiscalYear:String, val payDate:DateTime, val amount:BigDecimal) {
  require(employee!=null)
  // TODO employee.addSalary(this)
}


object Salary {
  def apply(employee:Employee, monthNo:Integer, fiscalYear:String, payDate:DateTime, amount:BigDecimal) = new Salary(employee, monthNo, fiscalYear, payDate, amount)
}
