package bitspoke.pacioli

import collection.mutable.ListBuffer


class Employee(name:String) extends Person(name) {

  private val _salaries = ListBuffer.empty[PayStatement]

  def salaries = _salaries.toSet

  def addSalary(s:PayStatement) { _salaries += s }
}


object Employee {
  def apply(name:String) = new Employee(name)
}
