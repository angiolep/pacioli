package bitspoke.accountancy.model

import org.joda.time.DateTime

/**
 * TODO ...
 *
 * @param seller
 * @param date
 * @param buyer
 */
class Invoice (val seller:Company, val date:DateTime, val buyer:Company) {

  require(seller != null, "Invoice.seller required")
  require(date != null, "Invoice.date required")
  // TODO require(date.isBeforeNow, "Invoice.date before now required")
  require(buyer != null, "Invoice.buyer required")


  private var _number:Option[Int] = None

  def number = _number

  def draft = _number == None

  private var _items:List[LineItem] = Nil

  def items = _items


  /**
   * Add a new line item
   *
   * @param item
   */
  def add(item:LineItem) {
    require(draft, "Draft invoice required")
    _items = item :: _items
  }


  def issue() {
    require(!_items.isEmpty, "Invoice with items required")
    if (draft) {
      _number = Some(seller.nextInvoiceNo)
    }
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


object Invoice {
  private[model] def apply(seller:Company, date:DateTime, buyer:Company) = new Invoice(seller, date, buyer)
}



