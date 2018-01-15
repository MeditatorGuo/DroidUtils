package com.uowee.droid.util;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.uowee.droid.util.adapter.DelegateRecyclerAdapter;
import com.uowee.droid.util.adapter.FixLayoutAdapter;
import com.uowee.droid.util.adapter.StaggeredAdapter;
import com.uowee.tangram.VirtualLayoutManager;
import com.uowee.tangram.adapter.DelegateAdapter;
import com.uowee.tangram.helper.LinearLayoutHelper;
import com.uowee.tangram.helper.ScrollFixLayoutHelper;
import com.uowee.tangram.helper.StaggeredGridLayoutHelper;

/**
 * Created by GuoWee on 2018/1/14.
 */

public class ScrollFixLayoutHelperActivity extends AppCompatActivity {
    private RecyclerView recyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_main);
        recyclerview = findViewById(R.id.recyclerview);
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        DelegateAdapter adapter = new DelegateAdapter(manager, true);

        adapter.addAdapter(initScrollFixLayout(this));
        adapter.addAdapter(initLinearLayout(this));
        adapter.addAdapter(initStaggeredGridLayoutHelper(this));
        recyclerview.setAdapter(adapter);
    }


    public static FixLayoutAdapter initScrollFixLayout(Context context) {
        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(15, 15);
        //show_always:总是显示
        //show_on_enter:当页面滚动到这个视图的位置的时候，才显示
        //show_on_leave:当页面滚出这个视图的位置的时候显示
        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_ENTER);
        return new FixLayoutAdapter(context, scrollFixLayoutHelper, "Scrollfixlayouthelper");
    }

    public static DelegateRecyclerAdapter initLinearLayout(Context context) {
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
}
