package bitspoke.accountancy.model

import scala.collection.mutable.Set

/**
 * <p>
 * A company is a form of business organization. It is a collection of individuals
 * and physical assets with a common focus and an aim of gaining profits.
 * </p>
 *
 * @param name the unique name of the company
 */
class Company(val name:String) {

  require(name!=null && !name.trim.isEmpty, "Company.name required")

  // one-Company-to-zero-or-many-Sales
  val sales = Set.empty[Sale]

  // TODO reduce visibility of this method
  def addSale(sale:Sale):Unit = sales += sale

  def canEqual(that:Any): Boolean = that.isInstanceOf[Company]

  override def equals(other:Any):Boolean = other match {
    case that:Company => (this canEqual that) && (this.name == that.name)
    case _ => false
  }

  override def hashCode:Int = 41 * (41 + name.hashCode)

  override def toString:String = name
}


object Company {
  def apply(name:String) = new Company(name)
}
