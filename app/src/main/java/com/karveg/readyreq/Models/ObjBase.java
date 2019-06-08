package com.karveg.readyreq.Models;

import java.util.Date;

public abstract class ObjBase  {
    private int Id;
    private String Name;
    private double Version = 1.0;
    private Date Fech;
    private int Category;
    private String Commentary;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getVersion() {
        return Version;
    }

    public void setVersion(double version) {
        Version = version;
    }

    public Date getFech() {
        return Fech;
    }

    public void setFech(Date fech) {
        Fech = fech;
    }

    public int getCategory() {
        return Category;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public String getCommentary() {
        return Commentary;
    }

    public void setCommentary(String commentary) {
        Commentary = commentary;
    }
}
