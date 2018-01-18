package com.uowee.droid.util;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.uowee.droid.util.adapter.LinearAdapter;
import com.uowee.droid.util.layout.ColumnLayoutHelperActivity;
import com.uowee.droid.util.layout.FixLayoutHelperActivity;
import com.uowee.droid.util.layout.FloatLayoutHelperActivity;
import com.uowee.droid.util.layout.GridLayoutHelperActivity;
import com.uowee.droid.util.layout.LinearLayoutHelperActivity;
import com.uowee.droid.util.layout.OnePlusNLayoutHelperActivity;
import com.uowee.droid.util.layout.ScrollFixLayoutHelperActivity;
import com.uowee.droid.util.layout.SingleLayoutHelperActivity;
import com.uowee.droid.util.layout.StaggeredGridLayoutHelperActivity;
import com.uowee.droid.util.layout.StickyLayoutHelperActivity;
import com.uowee.droid.util.layout.adapter.DelegateRecyclerAdapter;
import com.uowee.tangram.VirtualLayoutManager;
import com.uowee.tangram.adapter.DelegateAdapter;
import com.uowee.tangram.helper.LinearLayoutHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by GuoWee on 2018/1/18.
 */

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DelegateAdapter delegateAdapter;
    final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.main_view);
        initView();
    }

    public void initView() {
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        adapters.add(init(this));
        adapters.add(GridLayoutHelperActivity.init(this));


        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        delegateAdapter = new DelegateAdapter(manager);

        delegateAdapter.setAdapters(adapters);
        mRecyclerView.setAdapter(delegateAdapter);

    }

    public static LinearAdapter init(Context context) {
        LinearLayoutHelper helper = new LinearLayoutHelper();
        LinearAdapter adapter = new LinearAdapter(context, helper, "System");
        return adapter;
    }
}
