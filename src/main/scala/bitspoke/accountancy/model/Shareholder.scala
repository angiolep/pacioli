package bitspoke.accountancy.model



/**
 * A shareholder or stockholder is an individual or institution (including a corporation) that
 * legally owns a share of stock in a public or private corporation.
 *
 * @param person
 */
class Shareholder(val person:Person) {

  private var _sales  = Set.empty[Sale]

  def sales = _sales

  private var _dividends = Set.empty[Dividend]

  def dividends = _dividends


  /**
   * Add a sale
   *
   * @param s the sale being added
   */
  def add(s:Sale) {
    _sales = _sales + s
  }


  /**
   * Add a dividend
   *
   * @param d the dividend being added
   * @return all dividends
   */
  def add(d:Dividend) {
    _dividends = _dividends + d
  }


  /**
   * The sum of its closed sales invoices net
   *
   * @return the turnover
   */
  def turnover:BigDecimal =
    if (_sales.isEmpty) BigDecimal("0.00")
    else sales.map(_.invoice.net).reduce(_ + _)



  def corporationTax:BigDecimal = turnover * BigDecimal("0.2")
}



object Shareholder {
  def apply(person:Person) = new Shareholder(person)
}

