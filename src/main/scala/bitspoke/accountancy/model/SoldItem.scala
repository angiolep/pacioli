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
class SoldItem(val description:String,
               val quantity:Int,
               val unitPrice:BigDecimal,
               val vatRate:Option[BigDecimal]=None)
{

  require(description != null && !description.trim.isEmpty, "SoldItem.decription required")
  require(quantity>0, "SoldItem.quantity > 0 required")
  require(unitPrice >= 0, "SoldItem.unitPrice >= 0 required")
  require(vatRate != null, "SoldItem.vatRate option required")

  val amount:BigDecimal = vatRate match {
    case Some(rate) => (unitPrice * quantity) * (1 + rate)
    case None => (unitPrice * quantity)
  }

}


object SoldItem {
  def apply(description:String, quantity:Int, unitPrice:BigDecimal, vatRate:Option[BigDecimal]=None) = new SoldItem(description, quantity, unitPrice, vatRate)
}

