package com.uowee.droid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.uowee.droid.layout.RootActivity;
import com.uowee.droid.util.R;

/**
 * Created by GuoWee on 2018/1/18.
 */

public class StartActivity extends AppCompatActivity implements View.OnClickListener {


    private Button tangramBtn;
    private Button utautaBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        tangramBtn = findViewById(R.id.btn_layout);
        utautaBtn = findViewById(R.id.btn_util);
        tangramBtn.setOnClickListener(this);
        utautaBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_layout:
                startActivity(new Intent(this, RootActivity.class));
                break;
            case R.id.btn_util:
                startActivity(new Intent(this, MainActivity.class));
                break;
            default:
                break;
        }
    }
}
