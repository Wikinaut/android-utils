package com.mcakir.utils;

import android.text.TextUtils;
import android.util.Log;

public class Logger {

	private String prefix;

	private static final String TAG = "<< Android App >>";
	
	public static final boolean IS_ACTIVE = true;
	
	private Logger(String prefiks) {
		this.prefix = !TextUtils.isEmpty(prefiks) ? prefiks.trim() : "Logger";
	}
	
	public static Logger getLogger(String prefiks){
		return new Logger(prefiks);
	}

	public void debug(String message) {
		if(IS_ACTIVE && !TextUtils.isEmpty(message)){

			Log.d(TAG, prefix + "." + message);

		}
	}

	public void info(String message) {
		if(IS_ACTIVE && !TextUtils.isEmpty(message)){

			Log.i(TAG, prefix + "." + message);

		}
	}

	public void warning(String message) {
		if(IS_ACTIVE && !TextUtils.isEmpty(message)){

			Log.w(TAG, prefix + "." + message);

		}
	}

	public void error(String message) {
		if(IS_ACTIVE && !TextUtils.isEmpty(message)){

			Log.e(TAG, prefix + "." + message);

		}
	}
}
