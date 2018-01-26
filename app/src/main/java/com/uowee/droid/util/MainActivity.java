package com.uowee.droid.util;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.uowee.droid.util.adapter.GridViewAdapter;
import com.uowee.droid.util.adapter.LinearAdapter;
import com.uowee.tangram.VirtualLayoutManager;
import com.uowee.tangram.adapter.DelegateAdapter;
import com.uowee.tangram.helper.GridLayoutHelper;
import com.uowee.tangram.helper.LinearLayoutHelper;

import java.util.ArrayList;
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

        adapters.add(initLinearLayout(this));
        adapters.add(initGridLayout(this));


        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        delegateAdapter = new DelegateAdapter(manager);

        delegateAdapter.setAdapters(adapters);
        mRecyclerView.setAdapter(delegateAdapter);

    }

    public static LinearAdapter initLinearLayout(Context context) {
        LinearLayoutHelper helper = new LinearLayoutHelper();
        LinearAdapter adapter = new LinearAdapter(context, helper, "System");
        return adapter;
    }

    public static GridViewAdapter initGridLayout(final Context context) {
        GridLayoutHelper helper = new GridLayoutHelper(3);
        helper.setAutoExpand(false);

        List<String> titles = new ArrayList<>();
        titles.add("aaaaa");
        titles.add("bbbbb");
        titles.add("ccccc");
        titles.add("ddddd");
        titles.add("eeeee");
        titles.add("wwwww");
        List<Integer> images = new ArrayList<>();
        images.add(R.mipmap.genius);
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);
        GridViewAdapter adapter = new GridViewAdapter(context, helper, images, titles);
        adapter.setOnGridViewItemListener(new GridViewAdapter.OnGridViewItemListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(context, "HHHHHHHHHHH", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        return adapter;
    }
}
