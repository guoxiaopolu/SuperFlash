package com.palmerkuo.superflashlight;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * ϵͳ������
 *
 *
 */
public class MySystemParams {
	private final String TAG = "SystemParams";
	private static MySystemParams params;
	public int screenWidth;// ��Ļ��ȣ���λΪpx
	public int screenHeight;// ��Ļ�߶ȣ���λΪpx
	public int densityDpi;// ��Ļ�ܶȣ���λΪdpi
	public float scale;// ����ϵ����ֵΪ densityDpi/160
	public float fontScale;// ��������ϵ����ͬscale

	public final static int SCREEN_ORIENTATION_VERTICAL = 1; // ��Ļ״̬������
	public final static int SCREEN_ORIENTATION_HORIZONTAL = 2; // ��Ļ״̬������
	public int screenOrientation = SCREEN_ORIENTATION_VERTICAL;// ��ǰ��Ļ״̬��Ĭ��Ϊ����

	/**
	 * ˽�й��췽��
	 *
	 * @param activity
	 */
	private MySystemParams(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
		densityDpi = dm.densityDpi;
		scale = dm.density;
		fontScale = dm.scaledDensity;

		screenOrientation = screenHeight > screenWidth ? SCREEN_ORIENTATION_VERTICAL : SCREEN_ORIENTATION_HORIZONTAL;
	}

	/**
	 * ��ȡʵ��
	 *
	 * @param activity
	 * @return
	 */
	public static MySystemParams getInstance(Activity activity) {
		if (params == null) {
			params = new MySystemParams(activity);
		}
		return params;
	}

	/**
	 * ��ȡһ����ʵ��
	 *
	 * @param activity
	 * @return
	 */
	public static MySystemParams getNewInstance(Activity activity) {
		if (params != null) {
			params = null;
		}
		return getInstance(activity);
	}

	/**
	 * ������Ϣ
	 */
	public String toString() {

		return TAG + ":[screenWidth: " + screenWidth + " screenHeight: " + screenHeight + " scale: " + scale
				+ " fontScale: " + fontScale + " densityDpi: " + densityDpi + " screenOrientation: "
				+ (screenOrientation == SCREEN_ORIENTATION_VERTICAL ? "vertical" : "horizontal") + "]";
	}
}
