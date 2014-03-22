package bitspoke.pacioli

import java.math.BigDecimal
import java.math.RoundingMode.HALF_EVEN
import bitspoke.pacioli.Money.initializeBy

/**
 * Represent money with amount and currency providing proper maths and roundings
 * 
 * @param amount for example "100.1287" will be rounded to "100.13" when currency is "GBP"
 * @param currency the ISO 4217 code
 * @see http://martinfowler.com/eaaCatalog/money.html
 */
class Money private (val amount:String, val currency:String) {

  val (scale, symbol) = initializeBy(currency)

  private val amnt = new BigDecimal(amount).setScale(scale, HALF_EVEN)

  /**
   * Add given augend amount when same currency
   *
   * @param augend 
   */
  def + (augend:Money) : Money = 
    withSameCurrency (this, augend) { _ add _ }

  /**
   * Subtract given subtrahend amount when same currency
   *
   * @param subtrahend 
   */
  def - (subtrahend:Money) : Money = 
    withSameCurrency (this, subtrahend) { _ subtract _ }

    
    

  private def withSameCurrency(m1:Money, m2:Money)(op: (BigDecimal, BigDecimal) => BigDecimal) = 
    if (m1.currency == m2.currency) 
      new Money(op(m1.amnt, m2.amnt).toString, m1.currency)
    else
      throw new CurrencyMismatchException


  // TODO override def equalsTo(that:Any) : Boolean = ???
      
  override def toString() : String = s"${symbol}${amnt.toString}"
}



object Money {

  val Currencies = Map(
    "GBP" -> (2, "£"),
    "USD" -> (2, "$"),
    "EUR" -> (2, "€"),
    "JPY" -> (6, "¥")
  )
  
  def apply(amount:String, currency:String="GBP") = 
    new Money(amount, currency)

  def initializeBy(currency:String) = 
    Currencies.get(currency).getOrElse(throw new UnknownCurrencyException)
}



class UnknownCurrencyException extends RuntimeException

class CurrencyMismatchException extends RuntimeException


