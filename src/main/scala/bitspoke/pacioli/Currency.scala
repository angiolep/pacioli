package bitspoke.pacioli


case class Currency(code:String, symbol:Char, scale:Int) 


object ISO4217 {
  private val Currencies = Map(
    "GBP" -> Currency("GBP", '£', 2),
    "USD" -> Currency("USD", '$', 2),
    "EUR" -> Currency("EUR", '€', 2),
    "JPY" -> Currency("JPY", '¥', 4)
  )

  def apply(code:String) = Currencies.get(code).getOrElse(throw new UnknownCurrencyException)
}
