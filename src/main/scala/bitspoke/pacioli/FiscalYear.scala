package bitspoke.pacioli

/**
 * The tax year is called the financial year or fiscal year and runs from 1 April to 31 March
 *
 * @param code four digit code code (e.g. "1415" means 2014/2015)
 */
case class FiscalYear(code:String) {
  require(code.length==4 && code.toCharArray.forall(_.isDigit), "A four digit code is required")
}
