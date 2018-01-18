package com.uowee.droid.util.layout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uowee.droid.util.R;
import com.uowee.tangram.adapter.DelegateAdapter;
import com.uowee.tangram.helper.LayoutHelper;

/**
 * Created by GuoWee on 2018/1/14.
 */

public class OnePlusNLayoutAdapter extends DelegateAdapter.Adapter {
    public Context context;
    private LayoutHelper helper;
    private LayoutInflater inflater;
    private String name;

    public OnePlusNLayoutAdapter(Context context, LayoutHelper helper, String name) {
        this.inflater = LayoutInflater.from(context);
        this.helper = helper;
        this.context = context;
        this.name = name;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return this.helper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(0xaa3A5FCD);
        } else {
            holder.itemView.setBackgroundColor(0xcc90EE90);
        }
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.name.setText(name + (position + 1));
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
        }
    }

}
