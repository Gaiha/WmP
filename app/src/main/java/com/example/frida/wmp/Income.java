package com.example.frida.wmp;

/**
 * Created by Frida on 2014-09-24.
 */
import android.graphics.Bitmap;

/**
 * The class represent a income.
 * @author Frida
 *
 */
public class Income {
    private String title;
    private int price;
    private String category;
    private int 	id;

    public Income(String title, int price, String category) {
        this.title = title;
        this.price = price;
        this.category = category;
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
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}

