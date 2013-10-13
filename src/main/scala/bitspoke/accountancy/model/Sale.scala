package bitspoke.accountancy.model


class Sale(val invoice:Invoice) {

  def closed = invoice.issued

  def close() {
    invoice.issue()
  }
}
