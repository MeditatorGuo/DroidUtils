package com.uowee.droid.util.layout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.uowee.droid.util.R;
import com.uowee.droid.util.layout.adapter.DelegateRecyclerAdapter;
import com.uowee.tangram.VirtualLayoutManager;
import com.uowee.tangram.adapter.DelegateAdapter;
import com.uowee.tangram.helper.LinearLayoutHelper;

/**
 * Created by GuoWee on 2018/1/14.
 */

public class LinearLayoutHelperActivity extends AppCompatActivity {

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
        LinearLayoutHelper helper = new LinearLayoutHelper();
        helper.setDividerHeight(5);

        helper.setMarginBottom(20);
        helper.setMargin(20, 20, 20, 20);
        DelegateRecyclerAdapter mDelegateAdapter = new DelegateRecyclerAdapter(context, helper, "LinearLayoutHelper");
        return mDelegateAdapter;
    }


}
