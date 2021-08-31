package com.cas.veritasapp.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.util.AppUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PictureActivity extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback  {
    private final String TAG = PictureActivity.class.getSimpleName();
    private int KEY_CODE_RIGHT_BOTTOM = 249;
    private int KEY_CODE_LEFT_BOTTOM = 250;
    private int KEY_CODE_LEFT_TOP = 251;
    private int KEY_CODE_RIGHT_TOP = 252;

    private SurfaceView previewSFV;
    private ImageButton takeBT;

    private Camera mCamera;
    private SurfaceHolder holder;

    private String gatherID;
    private String pictureUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snap_picture);

        initView();
        setListener();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initView() {
        previewSFV = (SurfaceView) findViewById(R.id.picture_SurfaceView);
        holder = previewSFV.getHolder();
        holder.addCallback(PictureActivity.this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        takeBT = (ImageButton) findViewById(R.id.take_picture_bt);
    }

    private void setListener() {
        takeBT.setOnClickListener(this);
    }

    private void initData() {
        Intent data = getIntent();
        gatherID = data.getStringExtra("pictureName");
        DetectScreenOrientation detectScreenOrientation = new DetectScreenOrientation(this);
        detectScreenOrientation.enable();
    }

    private boolean focus = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take_picture_bt:
                if (!focus) {
                    takePicture();
                }

                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((KEY_CODE_LEFT_BOTTOM == keyCode || KEY_CODE_LEFT_TOP == keyCode
                || KEY_CODE_RIGHT_BOTTOM == keyCode || KEY_CODE_RIGHT_TOP == keyCode) && !focus) {
            takePicture();
        }

        return super.onKeyDown(keyCode, event);
    }

    private void takePicture() {
        mCamera.autoFocus((success, camera) -> {
            focus = success;
            if (success) {
                mCamera.cancelAutoFocus();
                mCamera.takePicture(() -> {
                }, null, null, (data, camera1) -> {
                    savePicture(data);
                    Intent intentData = new Intent();
                    intentData.putExtra("pictureUrl", pictureUrl);
                    setResult(Activity.RESULT_OK, intentData);
                    finish();
                });
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initCamera();
        setCameraParams();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }

    private void initCamera() {
        try {
            mCamera = Camera.open(0);
            mCamera.setPreviewDisplay(holder);
        } catch (Exception e) {
            Toast.makeText(this, "camera open failed!", Toast.LENGTH_SHORT).show();
            finish();
            e.printStackTrace();
        }
    }

    private void setCameraParams() {
        if (mCamera == null) {
            return;
        }
        try {
            Camera.Parameters parameters = mCamera.getParameters();

            int orientation = judgeScreenOrientation();
            if (Surface.ROTATION_0 == orientation) {
                mCamera.setDisplayOrientation(90);
                parameters.setRotation(90);
            } else if (Surface.ROTATION_90 == orientation) {
                mCamera.setDisplayOrientation(0);
                parameters.setRotation(0);
            } else if (Surface.ROTATION_180 == orientation) {
                mCamera.setDisplayOrientation(180);
                parameters.setRotation(180);
            } else if (Surface.ROTATION_270 == orientation) {
                mCamera.setDisplayOrientation(180);
                parameters.setRotation(180);
            }
            mCamera.setDisplayOrientation(270);
            parameters.setRotation(180);
            parameters.setPictureSize(240, 320);
            parameters.setPreviewSize(240, 320);

            mCamera.setParameters(parameters);
            mCamera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int judgeScreenOrientation() {
        return getWindowManager().getDefaultDisplay().getRotation();
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    private void savePicture(byte[] data) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            String pictureName = AppUtil.hashKeyForDisk(gatherID);
            String savePath = AppConstant.EXTERNAL_SD_PATH + File.separator + "picture";
            pictureUrl = savePath + File.separator + pictureName;
            File saveFolder = new File(savePath);
            if (!saveFolder.exists()) {
                if (saveFolder.mkdirs()) {
                    Log.e(TAG, "savePicture: create picture directory failed!");
                }
            }
            File pictureFile = new File(saveFolder, pictureName);
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class DetectScreenOrientation extends OrientationEventListener {
        public DetectScreenOrientation(Context context) {
            super(context);
        }

        @Override
        public void onOrientationChanged(int orientation) {
            if (260 < orientation && orientation < 290) {
                setCameraParams();
            } else if (80 < orientation && orientation < 100) {
                setCameraParams();
            }
        }
    }
}
