package com.uowee.droid.util;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.uowee.droid.util.adapter.DelegateRecyclerAdapter;
import com.uowee.droid.util.adapter.FixLayoutAdapter;
import com.uowee.tangram.VirtualLayoutManager;
import com.uowee.tangram.adapter.DelegateAdapter;
import com.uowee.tangram.helper.FixLayoutHelper;
import com.uowee.tangram.helper.LinearLayoutHelper;

/**
 * Created by GuoWee on 2018/1/14.
 */

public class FixLayoutHelperActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        recyclerView = findViewById(R.id.recyclerview);
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        DelegateAdapter adapter = new DelegateAdapter(manager, true);

        adapter.addAdapter(initLinearLayoutHelper(this));
        adapter.addAdapter(initFixLayoutHelper(this));
        recyclerView.setAdapter(adapter);
    }

    public static DelegateRecyclerAdapter initLinearLayoutHelper(Context context) {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        //设置间隔高度
        linearLayoutHelper.setDividerHeight(5);
        //设置布局底部与下个布局的间隔
        linearLayoutHelper.setMarginBottom(20);
        //设置间距
        linearLayoutHelper.setMargin(20, 20, 20, 20);
        DelegateRecyclerAdapter delegateRecyclerAdapter = new DelegateRecyclerAdapter(context, linearLayoutHelper, "FixLayoutHelper");
        return delegateRecyclerAdapter;
    }

    public static FixLayoutAdapter initFixLayoutHelper(Context context) {
        FixLayoutHelper fixLayoutHelper = new FixLayoutHelper(FixLayoutHelper.BOTTOM_RIGHT, 100,100);
        FixLayoutAdapter fixLayoutAdapter = new FixLayoutAdapter(context, fixLayoutHelper, "fixlayouthelp");
        return fixLayoutAdapter;
    }

}
