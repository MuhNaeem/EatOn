package com.example.android.eaton;

/**
 * Created by Hunter on 1/4/2018.
 */

public class Order {
    private String mID;
    private String mName;
    private String mPrice;
    public Order(){

    }


    public Order(String id, String name, String price)
    {
        mID = id;
        mName = name;
        mPrice = price;
    }



    public String getmID() {
        return mID;
    }

    public String getmName() {
        return mName;
    }

    public String getmPrice() {
        return mPrice;
    }




}
