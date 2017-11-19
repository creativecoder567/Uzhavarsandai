package com.gudiyatham.uzhavarsandai.model;

/**
 * Created by sarath on 11/16/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    public Product(String name) {
        this.name = name;
    }

    public Product(){

    }
   private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("image")
    @Expose

    private String image;

//        @SerializedName("price")
//    @Expose
//    private Integer price;
//
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    public Integer getPrice() {
//        return price;
//    }
//
//    public void setPrice(Integer price) {
//        this.price = price;
//    }
//
//    public String getAvailability() {
//        return availability;
//    }
//
//    public void setAvailability(String availability) {
//        this.availability = availability;
//    }


//

//    @SerializedName("availability")
//    @Expose
//    private String availability;
}
