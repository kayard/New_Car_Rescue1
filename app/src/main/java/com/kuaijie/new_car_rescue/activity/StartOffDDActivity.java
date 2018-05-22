package com.kuaijie.new_car_rescue.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

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
 * Created by kathe on 2018/5/10.
 * desc:搭电的接单页面
 */

public class StartOffDDActivity extends BaseActivity {

    @Bind(R.id.btn_guolu)
    public Button btn_guolu;
    @Bind(R.id.btn_zhengche)
    public Button btn_zhengche;
    @Bind(R.id.btn_name)
    public Button btn_name;
    @Bind(R.id.btn_photo)
    public Button btn_photo;
    @Bind(R.id.btn_feed)
    public Button btn_feed;
    @Bind(R.id.btn_elec)
    public Button btn_elec;
    @Bind(R.id.btn_next_step)
    public Button btn_next_step;

    private Bitmap bitmap_show;
    private boolean t1=false,t2=false,
            t3=false,t4=false,t5=false;    //判断能否进行下一步

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_off_dd);
        ButterKnife.bind(this);
        settitle();
        setonclick();
    }

    private void settitle() {
        final TopTitle topTitle =new TopTitle(getWindow().getDecorView(),this);
        topTitle.tv_title.setText("搭电");
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

        btn_guolu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartOffDDActivity.this,RoadTollActivity.class);
                startActivityForResult(intent,1);
            }
        });

        btn_zhengche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartOffDDActivity.this,TakePhotoOneActivity.class);
                intent.putExtra("type","zhengche");
                startActivityForResult(intent,2);
            }
        });

        btn_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WritePadDialog mWritePadDialog = new WritePadDialog(
                        StartOffDDActivity.this, new WriteDialogListener() {
                    @Override
                    public void onPaintDone(Object object) {
                        bitmap_show = (Bitmap) object;
                        createSignFile();
                        //img_photo.setImageBitmap(bitmap_show);
                    }
                });
                mWritePadDialog.show();
                t3=true;
            }
        });

        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartOffDDActivity.this,TakePhotoOneActivity.class);
                intent.putExtra("type","dadian_photo");
                startActivityForResult(intent,5);
            }
        });

        btn_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartOffDDActivity.this,CompletionFeedbackActivity.class);
                startActivityForResult(intent,4);
            }
        });

        btn_elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowToast("电子工单暂定！");
            }
        });

        btn_next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(CompleteOrderActivity.class,null);
            }
        });


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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 1){
            t1=true;
        }else if(requestCode == 2 && resultCode == 2){
            t2=true;
        }else if(requestCode == 3 && resultCode == 3){
            t3=true;
        }else if(requestCode == 4 && resultCode == 4){
            t4=true;
        }else if(requestCode == 5 && resultCode == 5){
            t5=true;
        }

        if (t1&&t2&&t3&&t4&&t5){
            btn_next_step.setEnabled(true);
        }

    }

}
