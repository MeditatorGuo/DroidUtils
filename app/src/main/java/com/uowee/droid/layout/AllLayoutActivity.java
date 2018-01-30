package com.uowee.droid.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.uowee.droid.util.R;
import com.uowee.tangram.VirtualLayoutManager;
import com.uowee.tangram.adapter.DelegateAdapter;

import java.util.LinkedList;
import java.util.List;

public class AllLayoutActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private DelegateAdapter delegateAdapter;
    final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        mRecyclerView = findViewById(R.id.recyclerview);
        initView();

    }

    public void initView() {
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        adapters.add(SingleLayoutHelperActivity.init(this));
        adapters.add(LinearLayoutHelperActivity.init(this));
        adapters.add(ColumnLayoutHelperActivity.initColumnLayout(this));
        adapters.add(FloatLayoutHelperActivity.initFloatLayoutHelper(this));
        adapters.add(GridLayoutHelperActivity.init(this));
        adapters.add(FixLayoutHelperActivity.initFixLayoutHelper(this));
        adapters.add(ScrollFixLayoutHelperActivity.initScrollFixLayout(this));
        adapters.add(StickyLayoutHelperActivity.initStickyLayoutHelper(this));
        adapters.add(StaggeredGridLayoutHelperActivity.init(this));
        adapters.add(OnePlusNLayoutHelperActivity.initOnePlusNLayout(this));

        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        delegateAdapter = new DelegateAdapter(manager);

        delegateAdapter.setAdapters(adapters);
        mRecyclerView.setAdapter(delegateAdapter);

    }


}
