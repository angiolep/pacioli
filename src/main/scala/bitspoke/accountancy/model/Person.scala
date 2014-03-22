package bitspoke.accountancy.model

/**
 * Any human
 */
class Person(val name:String) {

  override def equals(other:Any) = other match {
    case that:Person => this.name == that.name
    case _ => false
  }

  override def hashCode() = 41 * (41 + name.hashCode)
}


object Person {
  def apply(name:String) = new Person(name)
}
