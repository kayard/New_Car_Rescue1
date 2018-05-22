package com.kuaijie.new_car_rescue.utils;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

/**
 * Created by MitsukiSaMa on 12-10.
 */

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private final String TAG = "CameraSurfaceView";

    private Activity context;
    private SurfaceHolder surfaceHolder;

    private int screenWidth;
    private int screenHeight;

    public CameraSurfaceView(Context context) {
        super(context, null);
        this.context = (Activity) context;
        initView();
        getScreenMetrix(context);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        this.context = (Activity) context;
        initView();
        getScreenMetrix(context);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = (Activity) context;
        initView();
        getScreenMetrix(context);
    }

    private void initView() {
        Log.i(TAG, "initView");
        surfaceHolder = getHolder();//获得surfaceHolder引用
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//设置类型
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCreated");
        CameraUtil.getInstance().open(surfaceHolder);
        CameraUtil.getInstance().setCameraParams(context, screenWidth, screenHeight);
//        setCameraParams(camera, screenWidth, screenHeight);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i(TAG, "surfaceChanged");
        CameraUtil.getInstance().startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder h) {
        Log.i(TAG, "surfaceDestroyed");
        CameraUtil.getInstance().release();
        surfaceHolder = null;
    }

    public String takePic() {
        Log.i(TAG, "takePicture");
        // 当调用camera.takePiture方法后，camera关闭了预览，这时需要调用startPreview()来重新开启预览
        return  CameraUtil.getInstance().takePhoto(new CameraUtil.saveFinish() {
            @Override
            public void finish() {
                context.finish();
            }
        });
    }

    // 拍照瞬间调用
    private Camera.ShutterCallback shutter = new Camera.ShutterCallback() {
        @Override
        public void onShutter() {
            Log.i(TAG, "shutter");
        }
    };

    // 获得没有压缩过的图片数据
    private PictureCallback raw = new PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Log.i(TAG, "raw");
        }
    };

    private void getScreenMetrix(Context context) {
        WindowManager WM = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        WM.getDefaultDisplay().getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;
        screenHeight = outMetrics.heightPixels;
    }

}
