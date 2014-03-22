package bitspoke.accountancy.model


class Sale(val invoice:Invoice) {
  require(invoice!=null, "invoice required")
  require(!invoice.draft, "issued invoice required")

  var _shareholder:Option[Shareholder] = None

  def shareholder = _shareholder

  /**
   * Assign this sale to the supplied shareholder
   * @param s the shareholder
   */
  def assign(s:Shareholder) {
    require(_shareholder == None, "No shareholder previously assigned required")
    s.add(this)
    _shareholder = Some(s)
  }


  override def equals(other:Any):Boolean = other match {
    case that:Sale => this.invoice == that.invoice
    case _ => false
  }

  override def hashCode():Int = 41 * (41 + this.invoice.hashCode())
}



object Sale {
  def apply(invoice:Invoice) = new Sale(invoice)
}
