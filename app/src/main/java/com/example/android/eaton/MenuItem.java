package com.example.android.eaton;

/**
 * Created by Hunter on 12/24/2017.
 */

public class MenuItem {
    private String mID;
    private String mName;
    private String mPrice;
    private String mImageResourceId;
    public MenuItem(){

    }

    public MenuItem(String id, String name, String price, String uri)
    {
        mID = id;
        mName = name;
        mPrice = price;
        mImageResourceId = uri;
    }
    public MenuItem(String id, String name, String price)
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

    public String getmImageResourceId() {
        return mImageResourceId;
    }





}

