package com.example.universitybazaarsystem;

public class Product {
    private String sID,proName,proPrice,proDescription;
    private long productId;
    private byte[] proImage;

    public Product(String sID, String proName, String proPrice, String proDescription, long productId, byte[] proImage) {
        this.sID = sID;
        this.proName = proName;
        this.proPrice = proPrice;
        this.proDescription = proDescription;
        this.productId = productId;
        this.proImage = proImage;
    }



    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProPrice() {
        return proPrice;
    }

    public void setProPrice(String proPrice) {
        this.proPrice = proPrice;
    }

    public String getProDescription() {
        return proDescription;
    }

    public void setProDescription(String proDescription) {
        this.proDescription = proDescription;
    }

    public byte[] getProImage() {
        return proImage;
    }

    public void setProImage(byte[] proImage) {
        this.proImage = proImage;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
