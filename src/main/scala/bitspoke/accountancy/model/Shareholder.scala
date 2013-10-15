package bitspoke.accountancy.model


/**
 * A shareholder or stockholder is an individual or institution (including a corporation) that
 * legally owns a share of stock in a public or private corporation.
 *
 * @param name
 */
class Shareholder(val name:String) {

  var sales:Set[Sale] = Set.empty[Sale]


  /**
   * Add a sale
   *
   * @param s the sale being added
   * @return all of its sales
   */
  def add(s:Sale) = sales += s


  /**
   * The sum of its closed sales invoices net
   *
   * @return the turnover
   */
  def turnover:BigDecimal = sales.map(_.invoice.net).reduce(_ + _)


  def corporationTax:BigDecimal = turnover * BigDecimal("0.2")
}



object Shareholder {
  def apply(name:String) = new Shareholder(name)
}

