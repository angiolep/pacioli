package bitspoke.pacioli

/**
 * Any human person that can be identified by name
 *
 * @param name the unique name
 */
class Person(val name:String) {

  override def equals(other:Any) = other match {
    case that:Person => this.name == that.name
    case _ => false
  }

  override def hashCode() = 41 * (41 + name.hashCode)

  override def toString = name
}


object Person {
  def apply(name:String) = new Person(name)
}
