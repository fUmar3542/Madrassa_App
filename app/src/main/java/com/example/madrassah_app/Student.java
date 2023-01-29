package com.example.madrassah_app;

public class Student {
    private String name;
    private String rollNo;
    private String sabaq;
    private String sabqi;
    private String manzil;

    public Student(String name,String rollNo, String sabaq, String sabqi, String manzil) {
        this.name = name;
        this.rollNo = rollNo;
        this.sabaq = sabaq;
        this.sabqi = sabqi;
        this.manzil = manzil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return this.rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getSabaq() {
        return sabaq;
    }

    public void setSabaq(String sabaq) {
        this.sabaq = sabaq;
    }

    public String getSabqi() {
        return this.sabqi;
    }

    public void setSabqi(String sabqi) {
        this.sabqi = sabqi;
    }

    public String getManzil() {
        return this.manzil;
    }

    public void setManzil(String manzil) {
        this.manzil = manzil;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", rollNo=" + rollNo + ", sabaq=" + sabaq + ", sabqi=" + sabqi + ", manzil=" + manzil + "]";
    }
}
