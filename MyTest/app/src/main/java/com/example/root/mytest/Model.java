package com.example.root.mytest;

/**
 * Created by root on 17/1/18.
 */

public class Model {

    private String number;
    private String descrip;

    public Model(String number, String descrip, String address) {
        this.number = number;
        this.descrip = descrip;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;



    public Model() {
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
