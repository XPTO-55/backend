package br.com.cpa.spring.modules.payment.strategies;

public interface IPayment {
  public boolean payment(Double value);
}
