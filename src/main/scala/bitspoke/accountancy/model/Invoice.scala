package bitspoke.accountancy.model

import org.joda.time.DateTime

/**
 * TODO ...
 *
 * @param seller
 * @param number
 * @param date
 * @param buyer
 */
class Invoice private[model] (
      val seller:Company,
      val number:String,
      val date:DateTime,
      val buyer:Company
)
{
  require(seller != null, "Invoice.seller required")
  require(number != null && !number.trim.isEmpty, "Invoice.number required")
  require(date != null, "Invoice.date required")
  require(date.isBeforeNow, "Invoice.date before now required")
  require(buyer != null, "Invoice.buyer required")

  private var _draft = true

  def draft = _draft

  private var _items:List[LineItem] = Nil

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


  def issue() {
    if (!_items.isEmpty)
      _draft = false
    else
      sys.error("Cannot issue this empty invoice")
  }

  def net = _items.map(_.net).reduce(_ + _)

  def vat = _items.map(_.vat).reduce(_ + _)

  def gross = _items.map(_.gross).reduce(_ + _)


  override def equals(other:Any):Boolean = other match {
    case that:Invoice => (this.seller == that.seller) && (this.number == that.number)
    case _ => false
  }

  override def hashCode() : Int = 41 * ((41 * (41 + seller.hashCode)) + number.hashCode)
}



