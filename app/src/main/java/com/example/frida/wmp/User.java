package com.example.frida.wmp;

/**
 * Created by Frida on 2014-09-24.
 */
public class User {
    private String firstname;
    private String lastname;
    private int id;

    public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;

    }

    /**
     * @return the firstname
     */
    public String getFirstName() {
        return firstname;
    }

    /**
     * @param firstname
     *            the firstname to set
     */
    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastName() {
        return lastname;
    }

    /**
     * @param last
     *            name the lastname to set
     */
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

}