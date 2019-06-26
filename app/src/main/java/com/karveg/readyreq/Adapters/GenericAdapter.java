/*
   Autor: Arturo Balleros Albillo
 */
package com.karveg.readyreq.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Models.Generic;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

import java.util.List;

public class GenericAdapter extends RecyclerView.Adapter<GenericAdapter.ViewHolder> {

    //Atributos
    private static List<Generic> objects;
    private int layout;
    private static Context ctx;
    private OnItemClickListener itemClickListener;
    private static int mode;
    private static AlertDialog progressDialog;
    private static boolean flagPopUp = true;

    public GenericAdapter(List<Generic> objects, int layout, Context context, int mode, AlertDialog progressDialog, boolean flagPopUp, OnItemClickListener itemClickListener) {
        this.objects = objects;
        this.layout = layout;
        this.ctx = context;
        this.itemClickListener = itemClickListener;
        this.mode = mode;
        this.progressDialog = progressDialog;
        this.flagPopUp = flagPopUp;
    }

    //patron de diseño (primero de los metodos en ser llamado)
    @Override
    public GenericAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //solo se ejecuta al crear
    @Override
    public void onBindViewHolder(GenericAdapter.ViewHolder holder, int position) {
        //cada vez que renderiza nuevos les pone el fondo
        holder.view.setBackgroundColor(objects.get(position).isSelected() ? Color.CYAN : Color.parseColor("#E2E2E2"));
        holder.bind(objects.get(position), itemClickListener);
    }

    //numero
    @Override
    public int getItemCount() {
        try {
            return this.objects.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        //Elementos
        public ImageView imageViewIcon;
        public TextView textViewDesc;
        private View view;

        public ViewHolder(View v) {
            super(v);
            view = itemView;
            imageViewIcon = v.findViewById(R.id.imageViewIcon);
            textViewDesc = v.findViewById(R.id.textViewDesc);
            if (flagPopUp) v.setOnCreateContextMenuListener(this); //Registro el ContextMenu
        }

        public void bind(final Generic object, final OnItemClickListener listener) {
            //Procesamos
            textViewDesc.setText(object.getName());
            int image = object.getImage();
            if (image != -1) imageViewIcon.setImageResource(image);
            else textViewDesc.setTextSize(14);

            //Eventos del Card
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(object, v);
                }
            });
        }

        //Creo el popup
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            PopupMenu popup = new PopupMenu(v.getContext(), v);
            popup.setOnMenuItemClickListener(optionMenu);
            popup.getMenuInflater().inflate(R.menu.ctx_menu_item_del, popup.getMenu());
            popup.show();
        }

        //Eventos del popUp
        private final PopupMenu.OnMenuItemClickListener optionMenu = new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delItem:
                        Utils.showCtxDelete(objects, getAdapterPosition(), objects.get(getAdapterPosition()).getId(), ctx, mode, MyApplication.NOTHING, progressDialog);
                        break;
                }
                return true;
            }
        };
    }

    public interface OnItemClickListener {
        void onItemClick(Generic object, View v);
    }
}