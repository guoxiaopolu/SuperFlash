package com.palmerkuo.superflashlight;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FlashLight extends BaseActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mImageViewFlashLight.setTag(false);
		Point point = new Point();
		getWindowManager().getDefaultDisplay().getSize(point);

//	LayoutParams layoutParams = (LayoutParams) mImageViewFlashLightControler.getLayoutParams();
		FrameLayout.LayoutParams layoutParam = new FrameLayout.LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT); 
		
		float a,b=0f;
		a=layoutParam.width = point.x * 3 / 10;
//		b=layoutParam.height = point.y * 15 / 100;
		b=layoutParam.height=(int) a;
		layoutParam.setMargins((int) ((point.x/2)-a/2), (int)(point.y*75/100-a/2), 0, 0);

		mImageViewFlashLightControler.setLayoutParams(layoutParam);
	}

	public void onClick_FlashLight(View view) {
		if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
			Toast.makeText(this, "该设备没有闪光灯", Toast.LENGTH_SHORT).show();
			// return;
		}

		if ((Boolean) mImageViewFlashLight.getTag()) {
			closeFlashLight();
		} else {
			openFlashLight();
		}
	}

	protected void openFlashLight() {
		TransitionDrawable drawable = (TransitionDrawable) mImageViewFlashLight.getDrawable();
		drawable.startTransition(100);
		mImageViewFlashLight.setTag(true);

		try {

			mCamera = Camera.open();
			int textureId = 0;
			mCamera.setPreviewTexture(new SurfaceTexture(textureId));
			mCamera.startPreview();

			mParameters = mCamera.getParameters();
			mParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
			mCamera.setParameters(mParameters);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void closeFlashLight() {
		TransitionDrawable drawable = (TransitionDrawable) mImageViewFlashLight.getDrawable();

		if ((Boolean) mImageViewFlashLight.getTag()) {
			drawable.reverseTransition(100);
			mImageViewFlashLight.setTag(false);

			if (mCamera != null) {
				mParameters = mCamera.getParameters();
				mParameters.setFlashMode(Parameters.FLASH_MODE_OFF);
				mCamera.setParameters(mParameters);
				mCamera.stopPreview();
				mCamera.release();
				mCamera = null;
			}
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		closeFlashLight();
	}
}