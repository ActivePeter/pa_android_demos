package com.hanbao.opencv_android_demo;


import android.app.Activity;
//import android.content.Context;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.Size;

import androidx.camera.core.CameraX;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageAnalysisConfig;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;
import androidx.camera.core.UseCase;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedList;


public class CameraHandler extends Activity {
    public static CameraX.LensFacing CAMERA_ID = CameraX.LensFacing.BACK;


    public LinkedList<IPreviewInCallback> previewInCallbacks=new LinkedList<>();

    private Activity activity;

    public CameraHandler(Activity activity1) {
        activity=activity1;


    }


    public void startCamera(LifecycleOwner lifecycleOwner) {
        CameraX.unbindAll();
        // 1. preview
//        PreviewConfig previewConfig = new PreviewConfig.Builder()
//                .setLensFacing(CAMERA_ID)
////                .setTargetAspectRatio(Rational.NEGATIVE_INFINITY)  // 宽高比
//                .setTargetResolution(new Size(480, 640))  // 分辨率
//                .build();
//
//        Preview preview = new Preview(previewConfig);
//        preview.setOnPreviewOutputUpdateListener(new Preview.OnPreviewOutputUpdateListener() {
//            @Override
//            public void onUpdated(Preview.PreviewOutput output) {
////                output.getSurfaceTexture().
////                ViewGroup parent = (ViewGroup) viewFinder.getParent();
////                parent.removeView(viewFinder);
////                parent.addView(viewFinder, 0);
////
////                viewFinder.setSurfaceTexture(output.getSurfaceTexture());
////                updateTransform();
//            }
//        });


        DetailedAnalyzer detectAnalyzer = new DetailedAnalyzer();
         CameraX.bindToLifecycle(lifecycleOwner, packAnalyzer(detectAnalyzer));

    }
    private UseCase packAnalyzer(DetailedAnalyzer detailedAnalyzer) {
        ImageAnalysisConfig.Builder analysisConfigBuilder = new ImageAnalysisConfig.Builder();
        ImageAnalysisConfig config=analysisConfigBuilder
                .setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
                .setTargetResolution(new Size(480, 640))  // 输出预览图像尺寸
                .build();
        ImageAnalysis analysis = new ImageAnalysis(config);

        analysis.setAnalyzer(detailedAnalyzer);

        return analysis;
    }

    private class DetailedAnalyzer implements ImageAnalysis.Analyzer {

        @Override
        public void analyze(ImageProxy image, final int rotationDegrees) {
//            detectOnModel(image, rotationDegrees);
//            这里处理数据，比如识别，传输等
            Bitmap bitmap = SomeUtil.imageToBitmap(image);

            for (IPreviewInCallback callback:previewInCallbacks){
                callback.onBitmap(bitmap);
            }
        }

    }



}
