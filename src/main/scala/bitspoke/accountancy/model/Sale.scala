package bitspoke.accountancy.model


class Sale(val invoice:Invoice) {
  require(!invoice.draft, "Issued invoice required")

  var _shareholder:Option[Shareholder] = None

  def assignTo(s:Shareholder):Unit = _shareholder match  {
    case Some(_) => println("throw error")
    case _ => {
      _shareholder = Some(s)
      _shareholder.get.add(this)
    }
  }

}


object Sale {
  def apply(invoice:Invoice) = new Sale(invoice)
}
