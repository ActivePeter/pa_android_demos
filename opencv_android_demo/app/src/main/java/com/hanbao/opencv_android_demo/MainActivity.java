package com.hanbao.opencv_android_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    //>>permission
    void permissionFirst(){
        //camera permission needed
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.CAMERA},1);
        }else{
            afterPermission();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted 授予权限
                    //处理授权之后逻辑
                    afterPermission();
                } else {
                    // Permission Denied 权限被拒绝

//                    ToastUtils.showShort(getActivity(),"权限被禁用");
                }

                break;
            default:
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
    //<<permission

    class ViewCollection{
        public SurfaceView camView;
        void initViews(){
             camView = (SurfaceView)findViewById(R.id.cam_view);
        }
    }
    CameraViewHandler cameraViewHandler;
    ViewCollection viewCollection=new ViewCollection();
    CameraHandler cameraHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
//<<<<<<< HEAD

        permissionFirst();
//=======
//>>>>>>> parent of 9c0d0d6 (是)
    }

    void afterPermission(){
        cameraHandler=new CameraHandler(this);
        cameraViewHandler=new CameraViewHandler(this);

        viewCollection.initViews();
//        viewCollection.camView.getHolder().;
        cameraViewHandler.setup(viewCollection.camView);

        cameraHandler.previewInCallbacks.add(cameraViewHandler);
        cameraHandler.startCamera((LifecycleOwner)this);
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
