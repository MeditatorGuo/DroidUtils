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
 * Created by GuoWee on 2018/1/14.
 */

public class StaggeredAdapter extends DelegateAdapter.Adapter {
    private Context context;
    private LayoutHelper helper;
    private LayoutInflater inflater;
    private String name;

    public StaggeredAdapter(Context context, LayoutHelper helper, String name) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.helper = helper;
        this.name = name;
    }

    public LayoutHelper onCreateLayoutHelper() {
        return helper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewholder(inflater.inflate(R.layout.layout_item, parent, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(0xaa3A5FCD);
        } else {
            holder.itemView.setBackgroundColor(0xcc90EE90);
        }
        ViewGroup.LayoutParams layoutParams = ((MyViewholder) holder).text.getLayoutParams();
        layoutParams.height = 260 + position % 3 * 20;
        ((MyViewholder) holder).text.setLayoutParams(layoutParams);
        ((MyViewholder) holder).text.setText(position + 1 + "");
        MyViewholder myViewholder = (MyViewholder) holder;
        myViewholder.text.setText(name);

    }

    public int getItemCount() {
        return 20;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        private TextView text;

        public MyViewholder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.item_name);
        }
    }

}
