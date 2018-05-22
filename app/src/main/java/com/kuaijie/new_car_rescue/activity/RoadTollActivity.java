package com.kuaijie.new_car_rescue.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kuaijie.new_car_rescue.R;
import com.kuaijie.new_car_rescue.utils.TopTitle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by kathe on 2018/4/27.
 * desc:输入过路费加拍照
 */

public class RoadTollActivity extends BaseActivity {

    @Bind(R.id.et_input_cost)
    public EditText et_input_cost;
    @Bind(R.id.iv_take_photo)
    public ImageView iv_take_photo;
    @Bind(R.id.btn_next_step)
    public Button btn_next_step;

    private String type = "";
    private boolean next = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_toll);
        ButterKnife.bind(this);
        settitle();
        setonclick();
    }

    private void settitle() {
        final TopTitle topTitle =new TopTitle(getWindow().getDecorView(),this);
        topTitle.tv_title.setText("过路费");
        topTitle.back();
        topTitle.imgb_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topTitle.showOptionWindow(view);
            }
        });
        topTitle.tophone();
    }

    private void setonclick() {
        btn_next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cost = et_input_cost.getText().toString();

                if (cost.equals("")){
                    ShowToast("您还没有填写费用！");
                } else {
                    if (cost.equals("0")) {
                        type = "0";
                    } else {
                        type = "1";
                    }

                    if (type.equals("0") || next==true){
                        //startActivity(CarCheckActivity.class, null);
                        Intent intent = new Intent();
                        setResult(1,intent);
                        finish();
                    } else if (type.equals("1")){
                        //拍照
                        openCamera_1();
                    }
                }
            }
        });

        iv_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera_1();
            }
        });

    }


    // 拍照并显示图片
    private void openCamera_1() {
        next = true;
        Intent intent = new Intent();
        intent.setClass(this, TakePhotoActivity.class);
        startActivityForResult(intent, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            String fileName = data.getStringExtra("fileName");
            if (fileName != null) {
                Glide.with(this)
                        .load(Environment.getExternalStorageDirectory() + "/CarRescue/PhotoCache/" + fileName)
                        .into(iv_take_photo);
            }
        }
    }

}
