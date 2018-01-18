package com.uowee.droid.util.layout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.uowee.droid.util.R;
import com.uowee.droid.util.layout.adapter.StaggeredAdapter;
import com.uowee.tangram.VirtualLayoutManager;
import com.uowee.tangram.adapter.DelegateAdapter;
import com.uowee.tangram.helper.StaggeredGridLayoutHelper;

/**
 * Created by GuoWee on 2018/1/14.
 */

public class StaggeredGridLayoutHelperActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        mRecyclerView = findViewById(R.id.recyclerview);

        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        DelegateAdapter adapter = new DelegateAdapter(manager, true);

        adapter.addAdapter(init(this));

        mRecyclerView.setAdapter(adapter);

    }

    public static StaggeredAdapter init(Context context) {
        StaggeredGridLayoutHelper helper = new StaggeredGridLayoutHelper(3, 10);
        StaggeredAdapter mAdapter = new StaggeredAdapter(context, helper, "StaggeredGridLayoutHelper");
        return mAdapter;
    }


}
