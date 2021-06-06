package com.example.view_animation;


import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


/**
 * 一个有关 PopupWindow的动画
 */
public class PopupWindowViewAnimationActivity extends AppCompatActivity {

    private PopupWindow mImgPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window_view_animation);
        initToolbar();

        findViewById(R.id.btn_show_img).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                showImgPopupWindow(v);
            }
        });

    }

    private void initToolbar() {
        Toolbar toolbar =  findViewById(R.id.toolbar);
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showImgPopupWindow(View anchor) {
        if (mImgPopupWindow == null) {
            ImageView view = new ImageView(this);
            view.setImageDrawable(getDrawable(R.drawable.img_popup));

            mImgPopupWindow = new PopupWindow(view, anchor.getMeasuredWidth(), anchor.getMeasuredWidth());
            /**
             * 在这里给PopupWindow 设置了 动画的风格  而此风格又是xml 写出来的
             */
            mImgPopupWindow.setAnimationStyle(R.style.popup_anim_style);
        }
        if (mImgPopupWindow.isShowing()) {
            mImgPopupWindow.dismiss();
        } else {
            mImgPopupWindow.showAsDropDown(anchor);
        }
    }
}
