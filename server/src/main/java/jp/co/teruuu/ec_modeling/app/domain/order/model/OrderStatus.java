package jp.co.teruuu.ec_modeling.app.domain.order.model;

public enum OrderStatus {
  None(-1, "該当なし"),
  Shopping(1, "ショッピング中"),
  Confirm(2, "確定"),
  Cancel(100, "キャンセル");

  private int code;
  private String name;

  OrderStatus(int code, String name) {
    this.code = code;
    this.name = name;
  }
  public int getCode() {
    return this.code;
  }
  public String getName() {
    return this.name;
  }

  public static OrderStatus byCode(int code) {
    if (Shopping.code == code) {
      return Shopping;
    } else if (Confirm.code == code) {
      return Confirm;
    } else if (Cancel.code == code){
      return Cancel;
    } else {
      return None;
    }
  }
}
