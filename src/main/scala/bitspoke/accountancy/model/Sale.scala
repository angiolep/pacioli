package bitspoke.accountancy.model

import org.joda.time.DateTime

/**
 * <p>
 *  A sale is the act of selling a product or service in return for money or other compensation
 * </p>
 *
 * @param seller the provider of the goods or services
 * @param invoiceNo the invoice number
 * @param buyer
 * @param items
 * @param dateTime
 */
class Sale(val seller:Company,
           val invoiceNo:String,
           val buyer:Company,
           val items:List[SoldItem], // one-Sale-to-one-or-many-SoldItem(s)
           val dateTime:DateTime = DateTime.now
)
// TODO extends Ordered[Sale]
{

  require(seller != null, "Sale.seller required")
  require(invoiceNo != null && !invoiceNo.trim.isEmpty, "Sale.invoiceNo required")
  require(buyer != null, "Sale.buyer required")
  require(items != null && !items.isEmpty, "Sale.items required")
  require(dateTime != null, "Sale.dateTime required")

  // creates the bidirectional one-Company-to-zero-or-many-Sales link
  seller addSale this

  def canEqual(that:Any): Boolean = that.isInstanceOf[Sale]

  override def equals(other:Any):Boolean = other match {
    case that:Sale => (this canEqual that) && (this.seller == that.seller) && (this.invoiceNo == that.invoiceNo)
    case _ => false
  }

  override def hashCode() : Int = 41 * ((41 * (41 + seller.hashCode)) + invoiceNo.hashCode)

  // TODO override def compare(that:Sale):Int = this.dateTime.compareTo(that.dateTime)
}


object Sale {
  def apply(seller:Company, invoiceNo:String, buyer:Company, items:List[SoldItem], dateTime:DateTime = DateTime.now) = new Sale(seller, invoiceNo, buyer, items, dateTime)
}

