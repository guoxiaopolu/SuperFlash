package com.palmerkuo.superflashlight;

import java.util.HashMap;
import java.util.Map;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MorseLight extends WainingLight{
	
	private final int DOT_TIME=200;		//点时间
	private final int LINE_TIME=DOT_TIME*3;	//线时间
	private final int DOT_LINE=DOT_TIME;	//点到线时间
	
	private final int CHAR_CHAR=DOT_TIME*3;		//字符到字符时间
	private final int WORD_WORD=DOT_TIME*7;		//单词到单词时间

	
	private String mMorseCode;
	private Map<Character,String> mMorseCodeMap=new HashMap<Character,String>();
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		mMorseCodeMap.put('a', ".-");
		mMorseCodeMap.put('b',"--");
	}
	
	public void onClick_SentMorseCode(View view){
/**		if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
			Toast.makeText(this, "该设备没有闪光灯", Toast.LENGTH_SHORT).show();
			return;
		}
*/
		if(verifyMorseCode()){
			sentWord(mMorseCode);
		}
	}
	
	protected void sleepExt(long i){
		try {
			Thread.sleep(i);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void sentDot(){
		openFlashLight();
		sleepExt(DOT_TIME);
		closeFlashLight();
	}
	
	private void sentLine(){
		openFlashLight();
		sleepExt(DOT_LINE);
		closeFlashLight();
	}
	
	private void sentChar(char c){
		String morseCode=mMorseCodeMap.get(c);
		char lastChar=' ';
		if(morseCode!=null){
			for(int i=0;i<morseCode.length();i++){
				char dotLine=morseCode.charAt(i);
				if(dotLine=='.'){
					sentDot();
				}else if(dotLine=='-'){
					sentLine();
				}if(i>0&&i<morseCode.length()-1){
					if(lastChar=='.'&&dotLine=='-'){
						sleepExt(DOT_LINE);
					}
				}
				lastChar=dotLine;
			}
		}
	}
	
	private void sentWord(String word){
		for(int i=0;i<word.length();i++){
			char c=word.charAt(i);
			sentChar(c);
			if(i<word.length()-1){
				sleepExt(CHAR_CHAR);
			}
		}
	}
	
	private void sentSentence(String sentence){
		String[] words=sentence.split(" +");
		for(int i=0;i<words.length;i++){
			sentWord(words[i]);
			if(i<words.length-1){
				sleepExt(WORD_WORD);
			}
		}
		Toast.makeText(this, "莫尔斯电码已发送成功", Toast.LENGTH_LONG).show();
	}
	
	private boolean verifyMorseCode(){
		mMorseCode=mEditTextMorseCode.getText().toString();
		if("".equals(mMorseCode)){
			Toast.makeText(this, "请输入正确的莫尔斯电码字符", Toast.LENGTH_LONG).show();
			return false;
		}
		for(int i=0;i<mMorseCode.length();i++){ 
			char c=mMorseCode.charAt(i);
			if(!(c>'a' && c<'z')&& !(c>'0'&&c<'9')&&!(c==' ')){
				Toast.makeText(this, "请输入正确的莫尔斯字符", Toast.LENGTH_LONG).show();
				return false;
			}
		}
		
		return true;
		
	}
}
