package com.mustafabaser.resto;

public class ExampleItem {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public ExampleItem(int imageResource, String title, String description){
        mImageResource = imageResource;
        mText1 = title;
        mText2 = description;
    }

    public int getmImageResource(){
        return mImageResource;
    }

    public String getText1(){
        return mText1;
    }

    public String getText2(){
        return mText2;
    }
}