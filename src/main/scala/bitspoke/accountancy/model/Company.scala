package bitspoke.accountancy.model

import org.joda.time.DateTime

/**
 * <p>
 * A company is a form of business organization. It is a collection of individuals
 * and physical assets with a common focus and an aim of gaining profits.
 * </p>
 *
 * @param name the unique name of the company
 */
class Company private (val name:String) extends Ordered[Company] {
  require(name!=null && !name.trim.isEmpty, "Company.name required")

  private var _invoices:List[Invoice] = Nil

  def invoices = _invoices

  /**
   * Create a new invoice having a draft invoice
   *
   * @param date
   * @param buyer
   * @return a new invoice
   */
  def openDraftInvoice(date:DateTime, buyer:Company):Invoice = {
    _invoices = new Invoice(this, date, buyer) :: _invoices
    _invoices.head
  }


  // TODO private var _expenses:List[Receipt] = Nil

  // TODO def expenses = _expenses

  // TODO def buy(date:DateTime, seller:Company):Invoice =



  /**
   * List the draft invoices
   *
   * @return
   */
  def draftInvoices:List[Invoice] = _invoices.filter(_.draft)


  /**
   * List the issued invoices
   *
   * @return
   */
  def issuedInvoices:List[Invoice] = _invoices.filter(! _.draft)


  /**
   * The next invoice number
   *
   * @return
   */
  def nextInvoiceNo:Int = _invoices.length - issuedInvoices.length - draftInvoices.length + 1




  /**
   * Find a sale by its invoice number
   *
   * @param number the invoice number to lookup
   * @return some invoice or none
   */
  def findInvoiceByNo(number:Int):Option[Invoice] = _invoices.find(_.number.get == number)


  /**
   * The net income this company receives from its normal business activities,
   * usually from the sale of goods and services to customers.
   *
   * @return revenue
   */
  def revenue():BigDecimal = issuedInvoices.map(_.net).reduce(_ + _)



  override def compare(that: Company): Int = this.name.compare(that.name)


  def canEqual(that:Any): Boolean = that.isInstanceOf[Company]

  override def equals(other:Any):Boolean = other match {
    case that:Company => (this canEqual that) && (this.name == that.name)
    case _ => false
  }

  override def hashCode:Int = 41 * (41 + name.hashCode)


  override def toString:String = name
}




object Company {
  def apply(name:String) = new Company(name)
}
