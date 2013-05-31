package bitspoke.accountancy.model

/**
 * <p>
 * A company is a form of business organization. It is a collection of individuals
 * and physical assets with a common focus and an aim of gaining profits.
 * </p>
 */
case class Company(name:String) {

  val sales:List[Sale] = Nil

  def addSale (s:Sale) : List[Sale] = s :: sales

}
