package com.geniusnine.android.geniusninehealth.AzureTable;

/**
 * Created by AndriodDev8 on 15-02-2017.
 */

public class PatientBasicDetails {

    @com.google.gson.annotations.SerializedName("id")
    private String id;
    @com.google.gson.annotations.SerializedName("fname")
    private String fname;
    @com.google.gson.annotations.SerializedName("lname")
    private String lname;
    @com.google.gson.annotations.SerializedName("birthdate")
    private String birthdate;
    @com.google.gson.annotations.SerializedName("gender")
    private String gender;
    @com.google.gson.annotations.SerializedName("height")
    private String height;
    @com.google.gson.annotations.SerializedName("weight")
    private String weight;
    @com.google.gson.annotations.SerializedName("bloodgroup")
    private String bloodgroup;
    @com.google.gson.annotations.SerializedName("uidssn")
    private String uidssn;


    public PatientBasicDetails() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getUidssn() {
        return uidssn;
    }

    public void setUidssn(String uidssn) {
        this.uidssn = uidssn;
    }
}
