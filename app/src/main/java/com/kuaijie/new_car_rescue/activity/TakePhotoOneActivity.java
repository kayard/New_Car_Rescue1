package com.kuaijie.new_car_rescue.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kuaijie.new_car_rescue.R;
import com.kuaijie.new_car_rescue.utils.TopTitle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kathe on 2018/5/3.
 * desc:
 */

public class TakePhotoOneActivity extends BaseActivity {

    @Bind(R.id.btn_next_step)
    public Button btn_next_step;
    @Bind(R.id.btn_photo)
    public Button btn_photo;
    @Bind(R.id.img_photo)
    public ImageView img_photo;

    private static int REQUEST_CAMERA_2 = 2;
    private String mFilePath;
    private String type;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo_one);
        ButterKnife.bind(this);
        Intent intent =getIntent();
        type = intent.getStringExtra("type");
        settitle();
        init();
        setonclick();
    }

    private void settitle() {
        final TopTitle topTitle = new TopTitle(getWindow().getDecorView(),this);
        topTitle.tv_title.setText("拍照");
        topTitle.back();
        topTitle.imgb_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topTitle.showOptionWindow(view);
            }
        });
        topTitle.tophone();
    }

    // 初始化控件和变量
    private void init() {
        mFilePath = Environment.getExternalStorageDirectory().getPath();// 获取SD卡路径
        mFilePath = mFilePath + "/" + "temp.png";// 指定路径
    }


    private void setonclick() {
        btn_next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("zhengche")) {
                    Intent intent = new Intent();
                    setResult(2, intent);
                    finish();
                } else if (type.equals("beiche")){
                    Intent intent = new Intent();
                    setResult(4, intent);
                    finish();
                } else if (type.equals("daoda")){
                    Intent intent = new Intent();
                    setResult(2, intent);
                    finish();
                } else if (type.equals("xieche")){
                    Intent intent = new Intent();
                    setResult(3, intent);
                    finish();
                } else if (type.equals("dadian_photo")){
                    Intent intent = new Intent();
                    setResult(5, intent);
                    finish();
                }
            }
        });

        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera_2();
            }
        });
    }

    // 拍照后存储并显示图片
    private void openCamera_2() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 启动系统相机
        Uri photoUri = Uri.fromFile(new File(mFilePath)); // 传递路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);// 更改系统默认存储路径
        startActivityForResult(intent, REQUEST_CAMERA_2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回数据
            if (requestCode == REQUEST_CAMERA_2) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(mFilePath); // 根据路径获取数据
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    img_photo.setImageBitmap(bitmap);// 显示图片
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fis.close();// 关闭流
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
