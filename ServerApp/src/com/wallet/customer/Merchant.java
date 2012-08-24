package com.wallet.customer;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Merchant implements Serializable {

    private static final long serialVersionUID = -7330921567188590841L;
    private String email;
    private String name;
    private String dateOfRegistration;
    private String country;
    private double balance;
    private Integer ID;

    /**
     * default constructor
     */
    public Merchant() {
    }

    /**
     * 
     * @param id 
     * @param name
     * @param email
     * @param country
     * 
     * constructor with parameters
     */
    public Merchant(Integer id, String name, String email, String country) {
        this.ID = id;
        this.email = email;
        this.name = name;
        this.country = country;
        this.dateOfRegistration = Merchant.getDateTime();
    }

    public Integer getID() {
        return ID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setID(Integer iD) {
        this.ID = iD;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfRefistration() {
        return dateOfRegistration;
    }

    public void setDateOfRefistration(String dateOfRefistration) {
        this.dateOfRegistration = Merchant.getDateTime();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * 
     * @return the current date at the moment of creating at object 
     * 
     */
    public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
