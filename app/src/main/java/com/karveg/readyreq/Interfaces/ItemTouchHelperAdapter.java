/*
   Autor: Arturo Balleros Albillo
 */
package com.karveg.readyreq.Interfaces;

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);

    void onDrop(int fromPosition, int toPosition);
}