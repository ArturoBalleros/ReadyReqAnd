/*
   Autor: Arturo Balleros Albillo
 */
package com.karveg.readyreq.Models;

import java.io.Serializable;

public class Generic  implements Serializable {
    private int id;
    private int image;
    private String name;
    private boolean selected;

    public Generic(int id, String name, int image){
       this.id = id;
       this.name = name;
       this.image = image;
    }

    public Generic(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Generic() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}