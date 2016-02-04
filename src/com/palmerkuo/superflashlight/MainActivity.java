package com.palmerkuo.superflashlight;

import android.graphics.Color;
import android.view.View;

public class MainActivity extends Settings {

	public void onClick_ToFlashLight(View view) {
		hideAllUI();
		mUIFlashLight.setVisibility(View.VISIBLE);

		mLastedType = UIType.UI_TYPE_FLASHLIGHT;
		mCorrentType = UIType.UI_TYPE_FLASHLIGHT;
	}

	public void onClick_ToWainingLight(View view) {
		hideAllUI();
		mUIWainingLight.setVisibility(View.VISIBLE);

		mLastedType = UIType.UI_TYPE_WAININGLIGHT;
		mCorrentType = UIType.UI_TYPE_WAININGLIGHT;

		new WainingLightThread().start();
	}

	public void onClick_ToMoras(View view) {
		hideAllUI();
		mUIMorseLight.setVisibility(View.VISIBLE);

		mLastedType = UIType.UI_TYPE_MORAS;
		mCorrentType = UIType.UI_TYPE_MORAS;

	}

	public void onClick_ToBulb(View view) {
		hideAllUI();
		mUIBulb.setVisibility(View.VISIBLE);

		bulb_Text.hide();
		bulb_Text.setTextColor(Color.BLACK);

		mLastedType = UIType.UI_TYPE_BULB;
		mCorrentType = UIType.UI_TYPE_BULB;
	}

	public void onClick_ToColor_Light(View view) {
		hideAllUI();
		mUIColorLight.setVisibility(View.VISIBLE);

		// screenBrightness(1f);
		mLastedType = UIType.UI_TYPE_COLOR;
		mCorrentType = UIType.UI_TYPE_COLOR;

		mHideTextView_ColorLight.setTextColor(Color.rgb(
				255 - Color.red(mCorrentColor),
				255 - Color.green(mCorrentColor),
				255 - Color.blue(mCorrentColor)));
	}

	public void onClick_ToPolice(View view){
		hideAllUI();
		mUIPoliceLight.setVisibility(View.VISIBLE);
		mLastedType=UIType.UI_TYPE_POLICE;
		mCorrentType=UIType.UI_TYPE_POLICE;
		mHideTextViewPoliceLight.hide();
		new PoliceThread().start();
		
	}
	
	public void onClick_ToSetting(View view){
		hideAllUI();
		mUISettings.setVisibility(View.VISIBLE);
		mLastedType=UIType.UI_TYPE_SETTING;
		mCorrentType=mLastedType;
	}
	
	public void onClick_Controler(View view) {
		hideAllUI();

		if (mCorrentType != UIType.UI_TYPE_MAIN) {
			mUIMain.setVisibility(View.VISIBLE);

			mCorrentType = UIType.UI_TYPE_MAIN;

			mWainingLightFlicker = false;
/*			screenBrightness(mDefaultScreenBrightness/255f);
			if (mBulbCrossFadeFlag) {
				mDrawable.reverseTransition(0);
			}
			mBulbCrossFadeFlag = false;*/
			mPoliceState=false;
			mSharedPreferences.edit().putInt("warning_light_interval", mCorrentWarningLightInterval).commit();
			mSharedPreferences.edit().putInt("police_light_interval", mCorrentPoliceLightInterval).commit();
			
		} else {
			switch (mLastedType) {
			case UI_TYPE_FLASHLIGHT:
				mUIFlashLight.setVisibility(View.VISIBLE);
				mCorrentType = UIType.UI_TYPE_FLASHLIGHT;
				break;
			case UI_TYPE_WAININGLIGHT:
				mUIWainingLight.setVisibility(View.VISIBLE);
				mCorrentType = UIType.UI_TYPE_WAININGLIGHT;
				new WainingLightThread().start();
				break;
			case UI_TYPE_MORAS:
				mUIMorseLight.setVisibility(View.VISIBLE);
				mCorrentType = UIType.UI_TYPE_MORAS;
				break;
			case UI_TYPE_BULB:
				mUIBulb.setVisibility(View.VISIBLE);
				mCorrentType = UIType.UI_TYPE_BULB;
				break;
			case UI_TYPE_COLOR:
				mUIColorLight.setVisibility(View.VISIBLE);
				mCorrentType=UIType.UI_TYPE_COLOR;
				break;
			case UI_TYPE_POLICE:
				mUIPoliceLight.setVisibility(View.VISIBLE);
				mCorrentType=UIType.UI_TYPE_POLICE;
				new PoliceThread().start();
				break;
			case UI_TYPE_SETTING:
				mUISettings.setVisibility(View.VISIBLE);
				mCorrentType=UIType.UI_TYPE_SETTING;
				break;
			default:
				break;
			}
		}
	}

}
