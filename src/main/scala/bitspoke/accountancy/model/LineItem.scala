package bitspoke.accountancy.model

import scala.BigDecimal

/**
 * TODO ...
 *
 * @param description this item description
 * @param quantity how much of this item has been sold
 * @param unitPrice the unit price
 * @param vatRate the VAT rate option
 */
class LineItem  (val description:String, val quantity:Int, val unitPrice:BigDecimal, val vatRate:BigDecimal) {

  require(description != null && !description.trim.isEmpty, "LineItem.decription required")
  require(quantity > 0, "LineItem.quantity > 0 required")
  require(unitPrice >= 0, "LineItem.unitPrice >= 0 required")
  require(vatRate >= 0, "LineItem.vatRate option required")

  val net = unitPrice * quantity
  val vat = net * vatRate
  val gross = net + vat
}


object LineItem {
  def apply(description:String, quantity:Int, unitPrice:BigDecimal, vatRate:BigDecimal) = new LineItem(description, quantity, unitPrice, vatRate)
}




