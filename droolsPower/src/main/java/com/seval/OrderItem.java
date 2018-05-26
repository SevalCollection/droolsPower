package com.seval;

import java.math.BigDecimal;

public class OrderItem {

  public enum PROD_TYPE {
    BASIC, LUXURY
  }

  private PROD_TYPE type;
  private int units;
  private BigDecimal unitPrice;
  private BigDecimal effectiveDiscountRate;
  private BigDecimal discountedUnitPrice;

  public PROD_TYPE getType() {
    return type;
  }

  public void setType(PROD_TYPE type) {
    this.type = type;
  }

  public int getUnits() {
    return units;
  }

  public void setUnits(int units) {
    this.units = units;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }

  public BigDecimal getEffectiveDiscountRate() {
    return effectiveDiscountRate;
  }

  public void setEffectiveDiscountRate(BigDecimal effectiveDiscountRate) {
    this.effectiveDiscountRate = effectiveDiscountRate;
  }

  public BigDecimal getDiscountedUnitPrice() {
    return discountedUnitPrice;
  }

  public void setDiscountedUnitPrice(BigDecimal discountedUnitPrice) {
    this.discountedUnitPrice = discountedUnitPrice;
  }

  @Override
  public String toString() {
    return "OrderItem [type=" + type + ", units=" + units + ", unitPrice=" + unitPrice + ", effectiveDiscountRate="
        + effectiveDiscountRate + ", discountedUnitPrice=" + discountedUnitPrice + "]";
  }

}