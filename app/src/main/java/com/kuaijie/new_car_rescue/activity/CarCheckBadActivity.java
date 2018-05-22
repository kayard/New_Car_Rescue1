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
 * Created by kathe on 2018/5/7.
 * desc:车辆质检不合格的流程
 */

public class CarCheckBadActivity extends BaseActivity{

    @Bind(R.id.btn_photo)
    public Button btn_photo;
    @Bind(R.id.btn_next_step)
    public Button btn_next_step;
    @Bind(R.id.btn_name)
    public Button btn_name;
    @Bind(R.id.img_photo)
    public ImageView img_photo;


    private Bitmap bitmap_show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_check_bad);
        ButterKnife.bind(this);
        settitle();
        setonclick();
    }

    private void settitle() {
        final TopTitle topTitle = new TopTitle(getWindow().getDecorView(),this);
        topTitle.tv_title.setText("车辆质检");
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

        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera_1();
                btn_next_step.setEnabled(true);
            }
        });

        btn_name.setOnClickListener(new View.OnClickListener() {         //点击签名
            @Override
            public void onClick(View view) {
                WritePadDialog mWritePadDialog = new WritePadDialog(
                        CarCheckBadActivity.this, new WriteDialogListener() {
                    @Override
                    public void onPaintDone(Object object) {
                        bitmap_show = (Bitmap) object;
                        createSignFile();
                        //img_photo.setImageBitmap(bitmap_show);
                    }
                });
                mWritePadDialog.show();
                btn_next_step.setEnabled(true);
            }
        });

        btn_next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    // 拍照并显示图片
    private void openCamera_1() {
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
                        .into(img_photo);
            }
        }
    }


    //创建签名文件
    private void createSignFile() {
        ByteArrayOutputStream baos = null;
        FileOutputStream fos = null;
        String path = null;
        File file = null;
        try {
            path = Environment.getExternalStorageDirectory() + File.separator + System.currentTimeMillis() + ".jpg";
            file = new File(path);
            fos = new FileOutputStream(file);
            baos = new ByteArrayOutputStream();
            //如果设置成Bitmap.compress(CompressFormat.JPEG, 100, fos) 图片的背景都是黑色的
            bitmap_show.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            if (b != null) {
                fos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
