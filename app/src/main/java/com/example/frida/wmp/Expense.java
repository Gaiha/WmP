package com.example.frida.wmp;

/**
 * Created by Frida on 2014-09-24.
 */
import android.graphics.Bitmap;

/**
 * The class represent a expense
 * @author Frida
 *
 */
public class Expense {
    private String title;
    private int price;
    private String image;
    private String category;
    private long 	id;

    public String toString() {
        return "title="+title+", price="+price+", category="+category + "image="+image;
    }



    /**
     * A constructr without picture. Creates a expense object with title, price and category.
     * @param title
     * @param price
     * @param category
     * @param
     */
    public Expense(String title, int price, String category, String image) { //
        this.title = title;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the picture
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the picture to set
     * @return
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

}

