package com.uowee.droid.layout.adapter;

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

public class StickyLayoutAdapter extends DelegateAdapter.Adapter {

    public Context context;
    private LayoutHelper helper;
    private LayoutInflater inflater;

    public StickyLayoutAdapter(Context context,LayoutHelper helper){
        this.inflater = LayoutInflater.from(context);
        this.helper = helper;
        this.context=context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return this.helper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.layout_sticy,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.item_name);
        }
    }
}
