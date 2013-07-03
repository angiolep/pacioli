package bitspoke.accountancy.model


import org.scalatest.{Matchers, FunSpec}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith



@RunWith(classOf[JUnitRunner])
class CompanySpec extends FunSpec with Matchers {

  describe("A newly incorporated company") {

    it("should require a name") {
      intercept[IllegalArgumentException] { Company(null) }
      intercept[IllegalArgumentException] { Company("") }
      intercept[IllegalArgumentException] { Company(" ") }
    }

    it("should have no sales yet") {
      Company("name").sales.size should be (0)
    }
  }

}
