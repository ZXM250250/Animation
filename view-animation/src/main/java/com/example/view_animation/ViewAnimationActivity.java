package com.example.view_animation;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ViewAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        initToolbar();

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
    }

    private void initToolbar() {
        Toolbar toolbar = ((Toolbar) findViewById(R.id.toolbar));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn1) {
            startActivity(new Intent(this, CommonViewAnimationActivity.class));
        } else if (i == R.id.btn2) {
            startActivity(new Intent(this, PopupWindowViewAnimationActivity.class));
        } else if (i == R.id.btn3) {
            startActivity(new Intent(this, ListViewAnimationActivity.class));
        }
        /**
         * 一个参数是第一个activity进入时的动画，另外一个参数则是第二个activity退出时的动画。
         */

       overridePendingTransition(R.anim.animation_activity_enter, R.anim.animation_activity_exit);
    }
}
