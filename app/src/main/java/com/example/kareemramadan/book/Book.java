package com.example.kareemramadan.book;

public class Book {
    int image;
    String name;
    int page;
    String bookSource;
    float rating;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getBookSource() {
        return bookSource;
    }

    public void setBookSource(String bookSource) {
        this.bookSource = bookSource;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Book(int image, String name, int page) {
        this.image = image;
        this.name = name;
        this.page = page;
    }
}
