package com.example.kareemramadan.sebha;



public class ModelAzakar {
    String text;
    int count ;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ModelAzakar(String text, int count) {
        this.text = text;
        this.count = count;
    }
}
