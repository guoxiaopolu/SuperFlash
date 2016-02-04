package com.palmerkuo.superflashlight;

import com.palmerkuo.superflashlight.widget.HideTextView;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Bulb extends MorseLight{

	protected HideTextView bulb_Text;
	private TransitionDrawable mDrawable;

	protected boolean mBulbState;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		mBulbState=false;
		mDrawable=(TransitionDrawable)imageView_Bulb.getDrawable();
		bulb_Text=(HideTextView)findViewById(R.id.bulb_Text);
	}
	
	public void onClick_Bulb(View view){
		if(!mBulbState){
			mDrawable.startTransition(500);
			mBulbState=true;
		//	screenBrightness(1f);
		}else{
			mDrawable.reverseTransition(500);
			mBulbState=false;
		//	screenBrightness(0f);
		}
	}
}
