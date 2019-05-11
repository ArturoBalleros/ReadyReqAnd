package com.karveg.readyreq.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.karveg.readyreq.Interfaces.ItemTouchHelperAdapter;
import com.karveg.readyreq.Interfaces.ItemTouchHelperViewHolder;

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.SimpleCallback {

    public static final float ALPHA_FULL = 1.0f;

    private final ItemTouchHelperAdapter mAdapter;

    private Integer mFrom = null;
    private Integer mTo = null;

    Context context;

    public SimpleItemTouchHelperCallback(Context context, ItemTouchHelperAdapter adapter) {
        super(0, ItemTouchHelper.START);
        this.context = context;
        mAdapter = adapter;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(dragFlags, 0);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        if (source.getItemViewType() != target.getItemViewType()) return false;
        if (mFrom == null) mFrom = source.getAdapterPosition();
        mTo = target.getAdapterPosition();
        mAdapter.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder instanceof ItemTouchHelperViewHolder) {
                ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
                itemViewHolder.onItemSelected();
            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setAlpha(ALPHA_FULL);
        if (viewHolder instanceof ItemTouchHelperViewHolder) {
            ItemTouchHelperViewHolder itemViewHolder = (ItemTouchHelperViewHolder) viewHolder;
            itemViewHolder.onItemClear();
        }
        if (mFrom != null && mTo != null) mAdapter.onDrop(mFrom, mTo);
        mFrom = mTo = null;
    }
}