/*
   Autor: Arturo Balleros Albillo
 */
package com.karveg.readyreq.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class ObjEstandar extends ObjBase implements Serializable {
    private String Description;
    private int Prior;
    private int Urge;
    private int Esta;
    private boolean State;
    private List<Generic> Autors = new ArrayList<>();
    private List<Generic> Sources = new ArrayList<>();
    private List<Generic> Objetives = new ArrayList<>();

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPrior() {
        return Prior;
    }

    public void setPrior(int prior) {
        Prior = prior;
    }

    public int getUrge() {
        return Urge;
    }

    public void setUrge(int urge) {
        Urge = urge;
    }

    public int getEsta() {
        return Esta;
    }

    public void setEsta(int esta) {
        Esta = esta;
    }

    public boolean isState() {
        return State;
    }

    public void setState(boolean state) {
        State = state;
    }

    public List<Generic> getAutors() {
        return Autors;
    }

    public void setAutors(List<Generic> autors) {
        Autors = autors;
    }

    public List<Generic> getSources() {
        return Sources;
    }

    public void setSources(List<Generic> sources) {
        Sources = sources;
    }

    public List<Generic> getObjetives() {
        return Objetives;
    }

    public void setObjetives(List<Generic> objetives) {
        Objetives = objetives;
    }

}
