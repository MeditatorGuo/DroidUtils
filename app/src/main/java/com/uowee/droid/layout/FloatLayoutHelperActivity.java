package com.uowee.droid.layout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.uowee.droid.util.R;
import com.uowee.droid.layout.adapter.DelegateRecyclerAdapter;
import com.uowee.droid.layout.adapter.FixLayoutAdapter;
import com.uowee.tangram.VirtualLayoutManager;
import com.uowee.tangram.adapter.DelegateAdapter;
import com.uowee.tangram.helper.FloatLayoutHelper;
import com.uowee.tangram.helper.LinearLayoutHelper;

/**
 * Created by GuoWee on 2018/1/14.
 */

public class FloatLayoutHelperActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        recyclerView = findViewById(R.id.recyclerview);
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        DelegateAdapter adapter = new DelegateAdapter(manager, true);

        adapter.addAdapter(initLinearLayout(this));
        adapter.addAdapter(initFloatLayoutHelper(this));

        recyclerView.setAdapter(adapter);
    }

    public static DelegateRecyclerAdapter initLinearLayout(Context context) {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        DelegateRecyclerAdapter delegateRecyclerAdapter = new DelegateRecyclerAdapter(context, linearLayoutHelper, "FloatLayoutHelper");
        return delegateRecyclerAdapter;
    }

    public static FixLayoutAdapter initFloatLayoutHelper(Context context) {
        FloatLayoutHelper floatLayoutHelper = new FloatLayoutHelper();
        floatLayoutHelper.setDefaultLocation(20, 250);
        FixLayoutAdapter fixLayoutAdapter = new FixLayoutAdapter(context, floatLayoutHelper, "FloatLayoutHelper");
        return fixLayoutAdapter;
    }

}
