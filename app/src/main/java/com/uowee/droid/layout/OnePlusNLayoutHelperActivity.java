package com.uowee.droid.layout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.uowee.droid.util.R;
import com.uowee.droid.layout.adapter.OnePlusNLayoutAdapter;
import com.uowee.tangram.VirtualLayoutManager;
import com.uowee.tangram.adapter.DelegateAdapter;
import com.uowee.tangram.helper.OnePlusNLayoutHelper;

/**
 * Created by GuoWee on 2018/1/14.
 */

public class OnePlusNLayoutHelperActivity extends AppCompatActivity {
    private RecyclerView recyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        recyclerview = findViewById(R.id.recyclerview);
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        DelegateAdapter adapter = new DelegateAdapter(manager, true);

        initOnePlusNLayout(this);
        adapter.addAdapter(initOnePlusNLayout(this));
        recyclerview.setAdapter(adapter);
    }

    public static OnePlusNLayoutAdapter initOnePlusNLayout(Context context) {
        OnePlusNLayoutHelper onePlusNLayoutHelper = new OnePlusNLayoutHelper();
        //设置布局底部与下个布局的间隔
        onePlusNLayoutHelper.setMarginBottom(20);
        onePlusNLayoutHelper.setColWeights(new float[]{30, 70});
        OnePlusNLayoutAdapter onePlusNLayoutAdapter = new OnePlusNLayoutAdapter(context, onePlusNLayoutHelper, "OnePlusNLayoutHelper");
        return onePlusNLayoutAdapter;
    }


}
