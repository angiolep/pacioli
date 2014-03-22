package bitspoke.accountancy.model


import org.joda.time.DateTime

class Dividend(val shareholder:Shareholder, val payDate:DateTime, val amount:BigDecimal) {
  require(shareholder!=null)


}


object Dividend {
  def apply(shareholder:Shareholder, payDate:DateTime, amount:BigDecimal) = new Dividend(shareholder, payDate, amount)

}
