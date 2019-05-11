package com.karveg.readyreq.Adapters;

import android.app.AlertDialog;
import android.content.Context;
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

public class ActoAdapter extends RecyclerView.Adapter<ActoAdapter.ViewHolder> {

    //Atributos
    private static List<Generic> objects;
    private int layout;
    private static Context ctx;
    private static int mode;
    private static AlertDialog progressDialog;
    private static int id;

    public ActoAdapter(List<Generic> objects, int id, int layout, Context context, int mode, AlertDialog progressDialog) {
        this.objects = objects;
        this.id = id;
        this.layout = layout;
        this.ctx = context;
        this.mode = mode;
        this.progressDialog = progressDialog;
    }

    //patron de diseño (primero de los metodos en ser llamado)
    @Override
    public ActoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ActoAdapter.ViewHolder vh = new ActoAdapter.ViewHolder(v);
        return vh;
    }

    //solo se ejecuta al crear
    @Override
    public void onBindViewHolder(ActoAdapter.ViewHolder holder, int position) {
        holder.bind(objects.get(position));
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

        public ViewHolder(View v) {
            super(v);
            imageViewIcon = v.findViewById(R.id.imageViewIcon);
            textViewDesc = v.findViewById(R.id.textViewDesc);
            v.setOnCreateContextMenuListener(this); //Registro el ContextMenu
        }

        public void bind(final Generic object) {
            //Procesamos
            textViewDesc.setText(object.getName());
            imageViewIcon.setImageResource(object.getImage());
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
                        Utils.showCtxDelete(objects, getAdapterPosition(), id, ctx, mode, MyApplication.ACTO, progressDialog);
                        break;
                }
                return true;
            }
        };
    }
}