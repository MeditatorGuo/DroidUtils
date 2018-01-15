package com.uowee.droid.util;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.uowee.droid.util.adapter.DelegateRecyclerAdapter;
import com.uowee.tangram.VirtualLayoutManager;
import com.uowee.tangram.adapter.DelegateAdapter;
import com.uowee.tangram.helper.GridLayoutHelper;

/**
 * Created by GuoWee on 2018/1/14.
 */

public class GridLayoutHelperActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DelegateAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        mRecyclerView = findViewById(R.id.recyclerview);

        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new DelegateAdapter(manager, true);

        mAdapter.addAdapter(init(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    public static DelegateRecyclerAdapter init(Context context) {
        GridLayoutHelper helper = new GridLayoutHelper(4);
        helper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position > 5) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        helper.setAutoExpand(false);
        DelegateRecyclerAdapter delegateAdapter = new DelegateRecyclerAdapter(context, helper, "GridLayoutHelper");
        return delegateAdapter;
    }

}
