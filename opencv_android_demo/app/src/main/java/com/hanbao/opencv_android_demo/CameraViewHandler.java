package com.hanbao.opencv_android_demo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceView;

public class CameraViewHandler extends Activity implements IPreviewInCallback {
    private static final String TAG = "CameraViewHandler";
    SurfaceView camView;

    Activity activity;
    public CameraViewHandler(Activity activity1){
        activity=activity1;
    }

    public void setup(SurfaceView camView1){
        camView=camView1;
    }

    @Override
    public void onImage() {
        Log.d(TAG, "onImage");
    }

    @Override
    public void onBitmap(Bitmap bitmap) {

        Log.d(TAG, "onBitmap");
        Paint paint = new Paint();//画笔
        paint.setAntiAlias(true);//设置是否抗锯齿
        paint.setStyle(Paint.Style.STROKE);
        Canvas canvas = camView.getHolder().lockCanvas();
        canvas.drawBitmap(bitmap,0,0,paint);
        camView.getHolder().unlockCanvasAndPost(canvas);
        

    }
}
