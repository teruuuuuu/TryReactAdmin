package jp.co.teruuu.ec_modeling.app.domain.order.model;

public enum PaymentType {
  None(-1, "該当なし"),
  Credit(1, "クレジット"),
  Bank(2, "銀行振込");

  private int code;
  private String name;

  PaymentType(int code, String name) {
    this.code = code;
    this.name = name;
  }
  public int getCode() {
    return this.code;
  }
  public String getName() {
    return this.name;
  }

  public static PaymentType byCode(int code) {
    if (Credit.code == code) {
      return Credit;
    } else if (Bank.code == code) {
      return Bank;
    } else {
      return None;
    }
  }
}
