package com.swishsoftwaresolutions.sqlite;

/**
 * Created by DELL on 12/17/2017.
 */

public class ModuleClass {
    public int id;
    public String name1;
    public String ph_number;

    public ModuleClass(int id, String name1, String ph_number) {
        this.id = id;
        this.name1 = name1;
        this.ph_number = ph_number;
    }

    public ModuleClass(String name1, String ph_number) {
        this.name1 = name1;
        this.ph_number = ph_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getPh_number() {
        return ph_number;
    }

    public void setPh_number(String ph_number) {
        this.ph_number = ph_number;
    }

    public ModuleClass(){


    }


}
