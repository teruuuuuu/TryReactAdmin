package jp.co.teruuu.ec_modeling.app.domain.order.model;

import jp.co.teruuu.ec_modeling.app.domain.product.model.ProductId;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Item implements Serializable {
  public static final long serialVersionUID = 15;
  ItemId itemId;
  ProductId productId;
  int price;
  int currentPrice;
  int number;
  LocalDateTime updateDate;

  public Item(){
    this.itemId = new ItemId();
  };
  public Item(ProductId productId, int price, int currentPrice, int number, LocalDateTime updateDate) {
    this.itemId = new ItemId();
    this.productId = productId;
    this.price = price;
    this.currentPrice = currentPrice;
    this.number = number;
    this.updateDate = updateDate;
  }

  public boolean isPriceUpdate(){
    return this.currentPrice != this.price;
  }

  public int sum(){
    return currentPrice * number;
  }

  public ItemId getItemId() {
    return itemId;
  }

  public void setItemId(ItemId itemId) {
    this.itemId = itemId;
  }

  public ProductId getProductId() {
    return productId;
  }

  public void setProductId(ProductId productId) {
    this.productId = productId;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getCurrentPrice() {
    return currentPrice;
  }

  public void setCurrentPrice(int currentPrice) {
    this.currentPrice = currentPrice;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public LocalDateTime getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(LocalDateTime updateDate) {
    this.updateDate = updateDate;
  }
}
