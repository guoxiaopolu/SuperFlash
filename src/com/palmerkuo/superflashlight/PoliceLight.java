package com.palmerkuo.superflashlight;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.palmerkuo.superflashlight.widget.*;

public class PoliceLight extends ColorLight{

	protected boolean mPoliceState;
	protected HideTextView mHideTextViewPoliceLight;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mHideTextViewPoliceLight=(HideTextView)findViewById(R.id.text_police_light);
		
	}
	
	class PoliceThread extends Thread{
		public void run(){
			mPoliceState=true;
			while(mPoliceState){
				mHandler.sendEmptyMessage(Color.BLUE);
				sleepExt(mCorrentPoliceLightInterval);
				mHandler.sendEmptyMessage(Color.BLACK);
				sleepExt(mCorrentPoliceLightInterval);
				mHandler.sendEmptyMessage(Color.RED);
				sleepExt(mCorrentPoliceLightInterval);
				mHandler.sendEmptyMessage(Color.BLACK);
				sleepExt(mCorrentPoliceLightInterval);
			}
		}
	}
	
	private Handler mHandler=new Handler(){
		public void handleMessage(Message msg){
			int color=msg.what;
			mUIPoliceLight.setBackgroundColor(color);
		}
	};
}
