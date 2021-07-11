package jp.co.teruuu.ec_modeling.app.domain.order.model;

import java.io.Serializable;

public class Bank extends PayDetail implements Serializable {
  public static final long serialVersionUID = 13;
  String bankAccount;

  public Bank(String bankAccount) {
    this.bankAccount = bankAccount;
  }
  public String getBankAccount() {
    return bankAccount;
  }
  public void setBankAccount(String bankAccount) {
    this.bankAccount = bankAccount;
  }
}
