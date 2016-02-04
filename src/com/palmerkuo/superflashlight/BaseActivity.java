package com.palmerkuo.superflashlight;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class BaseActivity extends Activity {

	protected int mCorrentWarningLightInterval = 300;
	protected int mCorrentPoliceLightInterval = 200;

	protected int mDefaultBrightness;
	
	protected SharedPreferences mSharedPreferences;
	
	protected ImageView mImageViewFlashLight;
	protected ImageView mImageViewFlashLightControler;
	protected ImageView mImageViewWainingLight1;
	protected ImageView mImageViewWainingLight2;
	protected EditText mEditTextMorseCode;
	protected ImageView imageView_Bulb;

	protected SeekBar mSeekBarWarningLight;
	protected SeekBar mSeekBarPoliceLight;
	protected Button mButtonAddShortcut;
	protected Button mButtonRemoveShortcut;

	protected Camera mCamera;
	protected Parameters mParameters;
	
	
	

	protected enum UIType {
		UI_TYPE_MAIN, UI_TYPE_FLASHLIGHT, UI_TYPE_WAININGLIGHT, UI_TYPE_MORAS, UI_TYPE_BULB, UI_TYPE_COLOR, UI_TYPE_POLICE, UI_TYPE_SETTING
	}

	protected UIType mCorrentType = UIType.UI_TYPE_FLASHLIGHT;
	protected UIType mLastedType = UIType.UI_TYPE_FLASHLIGHT;

	protected FrameLayout mUIFlashLight;
	protected LinearLayout mUIMain;
	protected LinearLayout mUIWainingLight;
	protected LinearLayout mUIMorseLight;
	protected FrameLayout mUIBulb;
	protected FrameLayout mUIColorLight;
	protected FrameLayout mUIPoliceLight;
	protected LinearLayout mUISettings;

	protected int mFinishCount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mImageViewFlashLight = (ImageView) findViewById(R.id.imageview_flashlight);
		mImageViewFlashLightControler = (ImageView) findViewById(R.id.imageview_flashlight_controler);
		mImageViewWainingLight1 = (ImageView) findViewById(R.id.imageview_waining1);
		mImageViewWainingLight2 = (ImageView) findViewById(R.id.imageview_waining2);
		mEditTextMorseCode = (EditText) findViewById(R.id.edittext_morse_code);
		imageView_Bulb = (ImageView) findViewById(R.id.imageView_Bulb);
		mSeekBarWarningLight = (SeekBar) findViewById(R.id.seekbar_waining_light);
		mSeekBarPoliceLight = (SeekBar) findViewById(R.id.seekbar_police_light);
		mButtonAddShortcut = (Button) findViewById(R.id.button_add_shortcut);
		mButtonRemoveShortcut = (Button) findViewById(R.id.button_remove_shortcut);

		mSharedPreferences=getSharedPreferences("config", Context.MODE_PRIVATE);
		
		mUIFlashLight = (FrameLayout) findViewById(R.id.framelayout_flashlight);
		mUIMain = (LinearLayout) findViewById(R.id.linearlayout_uimain);
		mUIWainingLight = (LinearLayout) findViewById(R.id.linearlayout_waining);
		mUIMorseLight = (LinearLayout) findViewById(R.id.linearlayout_uimorse);
		mUIBulb = (FrameLayout) findViewById(R.id.framelayout_bulb);
		mUIColorLight = (FrameLayout) findViewById(R.id.framelayout_color_light);
		mUIPoliceLight = (FrameLayout) findViewById(R.id.framelayout_police_light);
		mUISettings = (LinearLayout) findViewById(R.id.linearlayout_settings);
		
		mDefaultBrightness=getScreenBrightness();
		
		mSeekBarWarningLight.setProgress(mCorrentWarningLightInterval-50);
		mSeekBarPoliceLight.setProgress(mCorrentPoliceLightInterval-100);
		
		mCorrentWarningLightInterval=mSharedPreferences.getInt("warning_light_interval", 500);
		mCorrentPoliceLightInterval=mSharedPreferences.getInt("police_light_interval",100);
		
	}

	protected void hideAllUI() {
		mUIMain.setVisibility(View.GONE);
		mUIFlashLight.setVisibility(View.GONE);
		mUIWainingLight.setVisibility(View.GONE);
		mUIMorseLight.setVisibility(View.GONE);
		mUIBulb.setVisibility(View.GONE);
		mUIColorLight.setVisibility(View.GONE);
		mUIPoliceLight.setVisibility(View.GONE);
		mUISettings.setVisibility(View.GONE);
	}

	protected void screenBrightness(float value) {
		try {
			WindowManager.LayoutParams layout = getWindow().getAttributes();
			layout.screenBrightness = value;
			getWindow().setAttributes(layout);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected int getScreenBrightness() {
		int value = 0;
		try {
			value = android.provider.Settings.System.getInt(
					getContentResolver(),
					android.provider.Settings.System.SCREEN_BRIGHTNESS);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return value;
	}

	@Override
	public void finish() {
		mFinishCount++;
		if (mFinishCount == 1) {
			Toast.makeText(this, "再按一次退出", Toast.LENGTH_LONG).show();
		} else if (mFinishCount == 2) {
			super.finish();
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		mFinishCount = 0;
		return super.dispatchTouchEvent(ev);
	}

}
