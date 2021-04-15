package com.example.universitybazaarsystem;

import android.graphics.Bitmap;

public class Orders {

    String sellerName;
    String sellerId;
    String buyerName;
    String buyerId;
    String productId;
    String productName;
    byte[] productImage;
    String productPrice;
    String buyerAddress;

    public Orders(String sellerName, String sellerId, String buyerName, String buyerId, String productId, String productName, byte[] productImage, String productPrice, String buyerAddress) {
        this.sellerName = sellerName;
        this.sellerId = sellerId;
        this.buyerName = buyerName;
        this.buyerId = buyerId;
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.buyerAddress = buyerAddress;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "sellerName='" + sellerName + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", buyerId='" + buyerId + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productImage=" + productImage +
                ", productPrice='" + productPrice + '\'' +
                ", buyerAddress='" + buyerAddress + '\'' +
                '}';
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }
}
