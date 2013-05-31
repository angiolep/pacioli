package bitspoke.accountancy.model


import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith


@RunWith(classOf[JUnitRunner])
class CompanyTest extends FlatSpec with ShouldMatchers {

  "A new company" should "have no sales" in {
    val company = Company("BitSpoke Ltd")
    assert(company.sales.isEmpty)
  }

}
