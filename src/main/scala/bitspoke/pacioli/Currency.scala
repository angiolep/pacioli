package bitspoke.pacioli


class Currency(val code:String, val symbol:Char, val scale:Int) {
  // TODO override def equalsTo(that:Any) : Boolean = ???

  override def toString = code
}


object Currency {
  private val Currencies = Map(
    "GBP" -> new Currency("GBP", '£', 2),
    "USD" -> new Currency("USD", '$', 2),
    "EUR" -> new Currency("EUR", '€', 2),
    "JPY" -> new Currency("JPY", '¥', 4)
  )

  def apply(code:String) = Currencies.getOrElse(code, throw new UnknownCurrencyException)
}
