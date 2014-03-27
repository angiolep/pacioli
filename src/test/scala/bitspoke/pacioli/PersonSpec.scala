package bitspoke.pacioli

import org.specs2.mutable._

class PersonSpec extends Specification {

  "Person" should {
    "provide equality by name" in {
      Person("paolo") must be equalTo(Person("paolo")) and not be equalTo(Person("dario"))
    }
  }
}
