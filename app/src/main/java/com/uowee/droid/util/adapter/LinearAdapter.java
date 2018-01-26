package com.uowee.droid.util.adapter;

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
 * Created by GuoWee on 2018/1/18.
 */

public class LinearAdapter extends DelegateAdapter.Adapter {

    public Context context;
    private LayoutHelper helper;
    private LayoutInflater inflater;
    private String title;

    public LinearAdapter(Context context, LayoutHelper helper, String title) {
        this.inflater = LayoutInflater.from(context);
        this.helper = helper;
        this.context = context;
        this.title = title;
    }


    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return this.helper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.title_layout_item, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.title.setText(title);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title);
        }
    }

}
