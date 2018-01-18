package com.example.root.mytest;

import java.util.ArrayList;

/**
 * Created by root on 18/1/18.
 */

public class Name {

    private String p_name;
    private ArrayList<Model> modelArrayList=new ArrayList<>();

    public Name(String p_name, ArrayList<Model> modelArrayList) {
        this.p_name = p_name;
        this.modelArrayList = modelArrayList;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public ArrayList<Model> getModelArrayList() {
        return modelArrayList;
    }

    public void setModelArrayList(ArrayList<Model> modelArrayList) {
        this.modelArrayList = modelArrayList;
    }
}
