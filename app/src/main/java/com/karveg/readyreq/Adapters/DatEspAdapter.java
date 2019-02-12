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
import com.karveg.readyreq.Interfaces.ItemTouchHelperAdapter;
import com.karveg.readyreq.Interfaces.ItemTouchHelperViewHolder;
import com.karveg.readyreq.Models.Generic;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

import java.util.Collections;
import java.util.List;

public class DatEspAdapter extends RecyclerView.Adapter<DatEspAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    //Atributos
    private static List<Generic> objects;
    private int layout;
    private  ICallback listener;

    public DatEspAdapter(List<Generic> objects, int layout, ICallback listener) {
        this.objects = objects;
        this.layout = layout;
        this.listener = listener;
    }

    //patron de diseño (primero de los metodos en ser llamado)
    @Override
    public DatEspAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        DatEspAdapter.ViewHolder vh = new DatEspAdapter.ViewHolder(v);
        return vh;
    }

    //solo se ejecuta al crear
    @Override
    public void onBindViewHolder(DatEspAdapter.ViewHolder holder, int position) {
        holder.bind(objects.get(position),listener);
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

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(objects, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onDrop(int fromPosition, int toPosition) {
        listener.onItemPositionChange(fromPosition, toPosition);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        //Elementos
        public TextView textViewDesc;

        public ViewHolder(View v) {
            super(v);
            textViewDesc = v.findViewById(R.id.textViewDesc);
        }

        public void bind(final Generic object, final ICallback listener) {
            //Procesamos
            textViewDesc.setText(object.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(object, getAdapterPosition(), v);
                }
            });

        }

        //Darle el color cuando lo tienes seleccionado
        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.parseColor("#A2A2A2"));
        }

        //Darle color cuando lo descelecionas
        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(  objects.get(getAdapterPosition()).isSelected()? Color.CYAN : Color.parseColor("#E2E2E2"));
        }
    }

    public interface ICallback {
        void onItemPositionChange(int fromPosition, int toPosition);
        void onItemClick(Generic object, int position, View v);
    }
}
