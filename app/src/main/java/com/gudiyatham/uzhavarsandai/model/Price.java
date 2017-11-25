package com.gudiyatham.uzhavarsandai.model;

/**
 * Created by sarath on 11/26/2017.
 */

public class Price {
int pPrice;
String pName;

    public Price(int pPrice, String pName) {
        this.pPrice = pPrice;
        this.pName = pName;
    }

    public int getpPrice() {
        return pPrice;
    }

    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }
}
