package com.palmerkuo.superflashlight;

import com.palmerkuo.superflashlight.ColorPickerDialog.OnColorChangedListener;
import com.palmerkuo.superflashlight.widget.HideTextView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ColorLight extends Bulb implements OnColorChangedListener{

	protected int mCorrentColor=Color.RED;
	protected HideTextView mHideTextView_ColorLight;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		mHideTextView_ColorLight=(HideTextView)findViewById(R.id.text_color_light);
	}
	
	public void onClick_ShowColorPicker(View view){
		new ColorPickerDialog(this, this, Color.RED).show();
	}

	@Override
	public void colorChanged(int color) {
		mUIColorLight.setBackgroundColor(color);
		mCorrentColor=color;
	}
}
