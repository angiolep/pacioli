package bitspoke.pacioli

import org.joda.time.DateTime


class PayStatement(
        val employee:Employee,
        val payDate:DateTime,
        val amount:Money,
        val fiscalYear:FiscalYear
) {

  require(employee!=null, "employee is required")
  require(payDate!=null, "payDate is required")
  require(amount!=null, "amount is required")
  require(fiscalYear!=null, "fiscalYear is required")


  override def equals(other:Any) = other match {
    case that:PayStatement => this.employee == that.employee && this.payDate == that.payDate
    case _ => false
  }

  override def hashCode : Int =
      41 * (
        41 * (
          41 + employee.hashCode
        ) + payDate.hashCode
      )
}


object PayStatement {
  def apply(employee:Employee, payDate:DateTime, amount:Money, fiscalYear:FiscalYear) = new PayStatement(employee, payDate, amount, fiscalYear)
}
