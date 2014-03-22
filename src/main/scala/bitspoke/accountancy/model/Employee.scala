package bitspoke.accountancy.model

import collection.mutable.ListBuffer

class Employee(val person:Person) {

  val salaries = ListBuffer.empty[Salary]

  def addSalary(s:Salary) { salaries += s}
}


object Employee {
  def apply(person:Person) = new Employee(person)
}
