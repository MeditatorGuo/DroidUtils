package com.uowee.droid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uowee.droid.util.R;
import com.uowee.droid.model.GridItem;
import com.muse.tangram.adapter.DelegateAdapter;
import com.muse.tangram.helper.LayoutHelper;

import java.util.List;

/**
 * Created by GuoWee on 2018/1/26.
 */

public class GridViewAdapter extends DelegateAdapter.Adapter {

    private Context mContext;
    private List<GridItem> list;
    private LayoutInflater inflater;
    private LayoutHelper helper;


    public GridViewAdapter(Context context, LayoutHelper helper, List<GridItem> items) {
        this.mContext = context;
        this.list = items;
        this.helper = helper;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return this.helper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grid_layout_item, parent, false);
        return new MyHoldView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder == null) {
            return;
        }

        final MyHoldView viewHolder = (MyHoldView) holder;
        if (onGridViewItemListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = viewHolder.getLayoutPosition();
                    onGridViewItemListener.onItemClick(view, position);
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = viewHolder.getLayoutPosition();
                    onGridViewItemListener.onItemLongClick(view, position);
                    return true;
                }
            });
        }
        viewHolder.textView.setText(list.get(position).getTitle());
        viewHolder.imageView.setImageResource(list.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHoldView extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyHoldView(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.recycler_item_textview);
            imageView = (ImageView) itemView.findViewById(R.id.recycler_item_imageview);
        }
    }

    private OnGridViewItemListener onGridViewItemListener;

    public void setOnGridViewItemListener(OnGridViewItemListener listener) {
        this.onGridViewItemListener = listener;
    }

    public interface OnGridViewItemListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }


}
