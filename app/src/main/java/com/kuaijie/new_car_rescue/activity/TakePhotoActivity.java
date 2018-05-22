package com.kuaijie.new_car_rescue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.kuaijie.new_car_rescue.R;
import com.kuaijie.new_car_rescue.utils.CameraSurfaceView;

/**
 * Created by kathe on 2018/4/25.
 * desc:拍照页面
 */

public class TakePhotoActivity extends BaseActivity {

    private CameraSurfaceView cameraSurfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_take_photo);

        cameraSurfaceView = findViewById(R.id.sv_camera);

        findViewById(R.id.ib_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.ib_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = cameraSurfaceView.takePic();
                Intent intent = new Intent();
                intent.putExtra("fileName", str);
                // 设置结果，并进行传送
                setResult(0, intent);
            }
        });
    }
}
