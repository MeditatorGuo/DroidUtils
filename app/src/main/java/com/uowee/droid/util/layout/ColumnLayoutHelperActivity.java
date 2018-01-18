package com.uowee.droid.util.layout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.uowee.droid.util.R;
import com.uowee.droid.util.layout.adapter.ColumnLayoutAdapter;
import com.uowee.tangram.VirtualLayoutManager;
import com.uowee.tangram.adapter.DelegateAdapter;
import com.uowee.tangram.helper.ColumnLayoutHelper;

/**
 * Created by GuoWee on 2018/1/14.
 */

public class ColumnLayoutHelperActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        mRecyclerView = findViewById(R.id.recyclerview);
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        DelegateAdapter adapter = new DelegateAdapter(manager, true);

        adapter.addAdapter(initColumnLayout(this));
        mRecyclerView.setAdapter(adapter);

    }

    public static ColumnLayoutAdapter initColumnLayout(Context context) {
        ColumnLayoutHelper helper = new ColumnLayoutHelper();
        helper.setWeights(new float[]{20,20,20,20,20}); //float数组总和为100
        helper.setMarginBottom(20);

        ColumnLayoutAdapter columnLayoutAdapter = new ColumnLayoutAdapter(context, helper, "ColumnLayoutHelper");
        return columnLayoutAdapter;
    }
}
