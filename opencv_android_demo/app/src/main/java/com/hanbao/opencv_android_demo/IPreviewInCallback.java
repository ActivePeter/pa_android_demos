package com.hanbao.opencv_android_demo;

import android.graphics.Bitmap;

public interface IPreviewInCallback {
    void onImage();
    void onBitmap(Bitmap bitmap);

}
