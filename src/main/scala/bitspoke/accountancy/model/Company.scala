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
class Company private (val name:String) extends Ordered[Company]
{
  require(name!=null && !name.trim.isEmpty, "Company.name required")

  private var _sales:List[Invoice] = Nil

  def sales = _sales

  /**
   * Prepend a new sale
   *
   * @param date
   * @param buyer
   * @return a new draft invoice
   */
  def createInvoice(date:DateTime, buyer:Company):Invoice = {
    val invoice = new Invoice(this, date, buyer)
    _sales = invoice :: _sales
    invoice
  }

  // TODO private var _expenses:List[Receipt] = Nil

  // TODO def expenses = _expenses

  // TODO def buy(date:DateTime, seller:Company):Invoice =

  /**
   * Issue the supplied draf invoice
   *
   * @param invoice
   */
  def issueInvoice(invoice:Invoice) {
    require(invoice.draft, "Draft invoice required")
    require(!invoice.items.isEmpty, "Not empty invoice required")
    invoice.issue(_sales.length + 1 - draftInvoices.length)
  }

  /**
   * List the draft invoices
   *
   * @return
   */
  def draftInvoices:List[Invoice] = _sales.filter(_.draft)


  /**
   * Find a sale by its invoice number
   *
   * @param number the invoice number to lookup
   * @return some invoice or none
   */
  def findInvoice(number:Int):Option[Invoice] = _sales.find(_.number == number)


  /**
   * The net income this company receives from its normal business activities,
   * usually from the sale of goods and services to customers.
   *
   * @return revenue
   */
  def revenue() = sales.map(_.net).reduce(_ + _)



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
