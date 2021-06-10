package edu.neu.madcourse.numad21su_shanness;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private final ArrayList<ListItems> itemList;
    private ItemClickListener listener;

    //Constructor
    public RecyclerViewAdapter(ArrayList<ListItems> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new RecyclerViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ListItems currentItem = itemList.get(position);

        holder.itemIcon.setImageResource(currentItem.getImageSource());
        holder.itemName.setText(currentItem.getItemName());
        holder.itemDesc.setText(currentItem.getItemDesc());
        holder.checkBox.setChecked(currentItem.getStatus());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
