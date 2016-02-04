package com.palmerkuo.superflashlight;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class WainingLight extends FlashLight {

	protected boolean mWainingLightFlicker; // true:…¡À∏ false£∫Õ£÷π…¡À∏
	protected boolean mWainingLightState; // true£∫on-off false£∫off-on

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	class WainingLightThread extends Thread {
		public void run() {
			mWainingLightFlicker = true;
			while (mWainingLightFlicker) {
				try {
					Thread.sleep(mCorrentWarningLightInterval);
					mWainingHandler.sendEmptyMessage(0);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	private Handler mWainingHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (mWainingLightState) {
				mImageViewWainingLight1
						.setImageResource(R.drawable.warning_shangbu);
				mImageViewWainingLight2
						.setImageResource(R.drawable.warning_dibu);

				mWainingLightState = false;
			} else {
				mImageViewWainingLight1
						.setImageResource(R.drawable.warning_dibu);
				mImageViewWainingLight2
						.setImageResource(R.drawable.warning_shangbu);

				mWainingLightState = true;
			}
		}
	};
}
