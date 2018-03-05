package com.uowee.droid.layout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uowee.droid.util.R;
import com.muse.tangram.adapter.DelegateAdapter;
import com.muse.tangram.helper.LayoutHelper;

/**
 * Created by admin on 2017/5/17.
 */

public class ColumnLayoutAdapter extends DelegateAdapter.Adapter {

    private Context context;
    private LayoutHelper helper;
    private LayoutInflater inflater;
    private String name;

    public ColumnLayoutAdapter(Context context, LayoutHelper helper, String name) {
        this.context = context;
        this.helper = helper;
        this.name = name;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return helper;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewholder(inflater.inflate(R.layout.layout_text, parent, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(0xaaff6666);
        } else {
            holder.itemView.setBackgroundColor(0xcc90EE90);
        }
        MyViewholder myViewholder = (MyViewholder) holder;
        myViewholder.textView.setText(name + (position + 1) + "");
    }

    public int getItemCount() {
        return 5;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyViewholder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.fix_name);
        }
    }


}
