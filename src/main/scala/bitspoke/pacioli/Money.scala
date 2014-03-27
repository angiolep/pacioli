package bitspoke.pacioli

import scala.math.BigDecimal
import scala.math.BigDecimal.RoundingMode.HALF_EVEN


/**
 * Represent money with amount and currency providing proper rounding and operators
 * 
 * @param amount for example "100.1287"
 * @param currency the ISO 4217 code
 * @see http://martinfowler.com/eaaCatalog/money.html
 */
class Money private (val amount:BigDecimal)(implicit val currency:Currency) {  

  /**
   * Round this amount according to its currency (e.g "100.1287" becomes "100.13" when currency is "GBP")
   */
  def round = new Money(amount.setScale(currency.scale, HALF_EVEN))

  /**
   * Add given augend amount if same currency
   *
   * @param augend 
   */
  def + (augend:Money) = ifSameCurrency (this, augend) { _ + _ }

  /**
   * Subtract given subtrahend amount if same currency
   *
   * @param subtrahend 
   */
  def - (subtrahend:Money) = ifSameCurrency (this, subtrahend) { _ - _ }
  
        
  private def ifSameCurrency(m1:Money, m2:Money)(f: (BigDecimal, BigDecimal) => BigDecimal) = 
    if (m1.currency == m2.currency) 
     new Money( f(m1.amount, m2.amount) )
    else
      throw new CurrencyMismatchException


  override def equals(other:Any) = other match {
    case that:Money => this.currency == that.currency && this.amount == that.amount
    case _ => false
  }

      
  override def toString() : String = s"${currency.symbol}${amount.toString}"
}



object Money {
  def apply(amount:String)(implicit currency:Currency) = {
    new Money(BigDecimal(amount))
  }
}



class UnknownCurrencyException extends RuntimeException

class CurrencyMismatchException extends RuntimeException





