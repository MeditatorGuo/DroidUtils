package com.uowee.droid.layout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.uowee.droid.util.R;
import com.uowee.droid.layout.adapter.DelegateRecyclerAdapter;
import com.uowee.droid.layout.adapter.StaggeredAdapter;
import com.uowee.droid.layout.adapter.StickyLayoutAdapter;
import com.muse.tangram.VirtualLayoutManager;
import com.muse.tangram.adapter.DelegateAdapter;
import com.muse.tangram.helper.LinearLayoutHelper;
import com.muse.tangram.helper.StaggeredGridLayoutHelper;
import com.muse.tangram.helper.StickyLayoutHelper;

/**
 * Created by GuoWee on 2018/1/14.
 */

public class StickyLayoutHelperActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        mRecyclerView = findViewById(R.id.recyclerview);
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        DelegateAdapter adapter = new DelegateAdapter(manager, true);

        adapter.addAdapter(initStickyLayoutHelper(this));
        adapter.addAdapter(initLinearLayoutHelper(this));
        adapter.addAdapter(initStaggeredGridLayoutHelper(this));

        mRecyclerView.setAdapter(adapter);
    }

    public static DelegateRecyclerAdapter initLinearLayoutHelper(Context context) {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        //设置间隔高度
        linearLayoutHelper.setDividerHeight(5);
        DelegateRecyclerAdapter delegateRecyclerAdapter = new DelegateRecyclerAdapter(context, linearLayoutHelper, "StickyLayoutHelper");
        return delegateRecyclerAdapter;
    }


    public static StaggeredAdapter initStaggeredGridLayoutHelper(Context context) {
        StaggeredGridLayoutHelper helper = new StaggeredGridLayoutHelper(3, 10);
        StaggeredAdapter mAdapter = new StaggeredAdapter(context, helper, "StaggeredGridLayoutHelper");
        return mAdapter;
    }
    public static StickyLayoutAdapter initStickyLayoutHelper(Context context) {
        StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();
        return new StickyLayoutAdapter(context, stickyLayoutHelper);
    }
}
