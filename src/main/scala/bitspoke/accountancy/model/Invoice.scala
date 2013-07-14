package bitspoke.accountancy.model

import org.joda.time.DateTime

/**
 * TODO ...
 *
 * @param seller
 * @param date
 * @param buyer
 */
class Invoice private[model] (
      val seller:Company,
      val date:DateTime,
      val buyer:Company
)
{
  require(seller != null, "Invoice.seller required")
  require(date != null, "Invoice.date required")
  require(date.isBeforeNow, "Invoice.date before now required")
  require(buyer != null, "Invoice.buyer required")

  private var _draft = true

  private var _number = -1

  private var _items:List[LineItem] = Nil

  def draft = _draft

  def number = _number

  def items = _items


  /**
   * Prepend a new line item
   *
   * @param description
   * @param quantity
   * @param unitPrice
   * @param vatRate
   * @return
   */
  def addItem(description:String, quantity:Int, unitPrice:BigDecimal, vatRate:BigDecimal=BigDecimal(0)):LineItem = {
    if (_draft) {
      val item = new LineItem(description, quantity, unitPrice, vatRate)
      _items = item :: _items
      item
    }
    else
      sys.error("Cannot add line items to issued invoices")
  }


  private[model] def issue(number:Int) {
    _draft = false
    _number = number
  }

  def net = _items.map(_.net).reduce(_ + _)

  def vat = _items.map(_.vat).reduce(_ + _)

  def gross = _items.map(_.gross).reduce(_ + _)


  override def equals(other:Any):Boolean = other match {
    case that:Invoice => (this.seller == that.seller) && (this._number == that._number)
    case _ => false
  }

  override def hashCode() : Int = 41 * ((41 * (41 + seller.hashCode)) + _number.hashCode)
}



