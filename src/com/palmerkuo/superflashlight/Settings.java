package com.palmerkuo.superflashlight;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class Settings extends PoliceLight implements OnSeekBarChangeListener {

	private boolean clickstate = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mSeekBarWarningLight.setOnSeekBarChangeListener(this);
		mSeekBarPoliceLight.setOnSeekBarChangeListener(this);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		switch (seekBar.getId()) {
		case R.id.seekbar_waining_light:
			mCorrentWarningLightInterval = progress + 30;
			break;
		case R.id.seekbar_police_light:
			mCorrentPoliceLightInterval = progress + 50;
			break;
		default:
			break;
		}

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO 自动生成的方法存根

	}

	private boolean shortcutInScreen() {
		Cursor cursor = getContentResolver()
				.query(Uri
						.parse("content://com.android.launcher3.settings/favorites"),
						null,
						"intent like ?",
						new String[] { "%component=com.palmerkuo.superflashlight/.MainActivity%" },
						null);
		if (cursor.getCount() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void onClick_AddShortcut(View view) {

		if (clickstate) {
			Intent installShortcut = new Intent(
					"com.android.launcher.action.INSTALL_SHORTCUT");
			installShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "超级手电筒");
			Parcelable icon = Intent.ShortcutIconResource.fromContext(this,
					R.drawable.logosmall);

			Intent flashLightIntent = new Intent();
			flashLightIntent.setClassName("com.palmerkuo.superflashlight",
					"com.palmerkuo.superflashlight.MainActivity");
			flashLightIntent.setAction(Intent.ACTION_MAIN);
			flashLightIntent.addCategory(Intent.CATEGORY_LAUNCHER);
			installShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
					flashLightIntent);
			sendBroadcast(installShortcut);

			Toast.makeText(this, "已成功将快捷方式添加到桌面", Toast.LENGTH_LONG).show();
			clickstate=false;
		} else {
			Toast.makeText(this, "桌面上已有快捷方式", Toast.LENGTH_LONG).show();
		}
	}

	public void onClick_RemoveShortcut(View view) {
		if (!clickstate) {
			Intent uninstallShortcut = new Intent(
					"com.android.launcher.action.UNINSTALL_SHORTCUT");
			uninstallShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "超级手电筒");
			Intent flashLightIntent = new Intent();
			flashLightIntent.setClassName("com.palmerkuo.superflashlight",
					"com.palmerkuo.superflashlight.MainActivity");
			flashLightIntent.setAction(Intent.ACTION_MAIN);
			flashLightIntent.addCategory(Intent.CATEGORY_LAUNCHER);
			uninstallShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT,
					flashLightIntent);

			sendBroadcast(uninstallShortcut);
			clickstate=true;
			Toast.makeText(this, "已成功将快捷方式从桌面移除", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this, "没有快捷方式，无法删除", Toast.LENGTH_LONG).show();
		}
	}
}
