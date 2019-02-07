package com.example.mm.advancedrecyclerview1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemArrayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;

    private ArrayList<Item> itemList;

    // Constructor of the class
    public ItemArrayAdapter(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    // specify the row layout file and click for each row
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_ONE) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_type1, parent, false);
            return new ViewHolderOne(view);

        } else if (viewType == TYPE_TWO) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_type2, parent, false);
            return new ViewHolderTwo(view);

        } else {
            throw new RuntimeException("The type has to be ONE or TWO");
        }
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case TYPE_ONE:
                initLayoutOne((ViewHolderOne)holder, position);
                break;
            case TYPE_TWO:
                initLayoutTwo((ViewHolderTwo) holder, position);
                break;
            default:
                break;
        }
    }

    // get the size of the list
    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    // determine which layout to use for the row
    @Override
    public int getItemViewType(int position) {
        Item item = itemList.get(position);
        if (item.getType() == Item.ItemType.ONE_ITEM) {
            return TYPE_ONE;
        } else if (item.getType() == Item.ItemType.TWO_ITEM) {
            return TYPE_TWO;
        } else {
            return -1;
        }
    }

    private void initLayoutOne(ViewHolderOne holder, int pos) {
        holder.item.setText(itemList.get(pos).getName());
    }

    private void initLayoutTwo(ViewHolderTwo holder, int pos) {
        holder.tvLeft.setText(itemList.get(pos).getName());
        holder.tvRight.setText(itemList.get(pos).getName());
    }

    // Static inner class to initialize the views of rows
    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView item;
        public ViewHolderOne(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.row_item);
        }
    }

    static class ViewHolderTwo extends RecyclerView.ViewHolder {
        public TextView tvLeft, tvRight;
        public ViewHolderTwo(View itemView) {
            super(itemView);
            tvLeft = itemView.findViewById(R.id.row_item_left);
            tvRight = itemView.findViewById(R.id.row_item_right);
        }
    }
}
