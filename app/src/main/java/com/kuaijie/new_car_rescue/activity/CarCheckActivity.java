package com.kuaijie.new_car_rescue.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kuaijie.new_car_rescue.R;
import com.kuaijie.new_car_rescue.utils.TopTitle;
import com.kuaijie.new_car_rescue.utils.WriteDialogListener;
import com.kuaijie.new_car_rescue.utils.WritePadDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kathe on 2018/4/26.
 * desc:车辆质检客户签名
 */

public class CarCheckActivity extends BaseActivity {

    @Bind(R.id.btn_ok)
    public Button btn_ok;
    @Bind(R.id.btn_not_ok)
    public Button btn_not_ok;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_check);
        ButterKnife.bind(this);
        settitle();
        setonclick();
    }

    private void settitle() {
        final TopTitle t = new TopTitle(getWindow().getDecorView(),this);
        t.tv_title.setText(getResources().getString(R.string.title_text_car_check));
        t.back();
        t.imgb_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.showOptionWindow(view);
            }
        });
        t.tophone();
    }

    private void setonclick(){
        //点击确定进行下一步
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(3,intent);
                finish();
            }
        });

        //不合格拍照
        btn_not_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(CarCheckBadActivity.class,null);
                finish();
            }
        });
    }
}