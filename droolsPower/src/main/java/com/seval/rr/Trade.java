package com.seval.rr;

public class Trade {
    private String tradeId;
    private String tradeDate;
    private String symbol;
    private String price;
    private String volume;
    private String totalPrice;
    private String buyerId;
    private String sellerId;

    public Trade(String tradeId, String tradeDate, String symbol, String price,
            String volume, String totalPrice, String buyerId, String sellerId) {
        super();
        this.tradeId = tradeId;
        this.tradeDate = tradeDate;
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.totalPrice = totalPrice;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "Trade [tradeId=" + tradeId + ", tradeDate=" + tradeDate
                + ", symbol=" + symbol + ", price=" + price + ", volume="
                + volume + ", totalPrice=" + totalPrice + ", buyerId="
                + buyerId + ", sellerId=" + sellerId + "]";
    }

}