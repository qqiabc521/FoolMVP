package com.ljj.foolmvp.appcomm.util;

import android.content.SharedPreferences;

import com.ljj.foolmvp.appcomm.BaseApplication;
import com.ljj.foolmvp.appcomm.json.JSONFormatException;
import com.ljj.foolmvp.appcomm.json.JSONToBeanHandler;

public class PrefsManager {

	private static BaseApplication getContext(){
		return BaseApplication.getInstance();
	}

	public static int getInt(String tbl, String key, int def) {
		if (getContext() == null) {
			return def;
		}
		SharedPreferences prefs = getContext().getAppSharedPreferences(tbl);
		return prefs.getInt(key, def);
	}

	public static long getLong(String tbl, String key, long def) {
		if (getContext() == null) {
			return def;
		}
		SharedPreferences prefs = getContext().getAppSharedPreferences(tbl);
		return prefs.getLong(key, def);
	}

	public static String getString(String tbl, String key, String def) {
		if (getContext() == null) {
			return def;
		}
		SharedPreferences prefs = getContext().getAppSharedPreferences(tbl);
		return prefs.getString(key, def);
	}

	public static boolean getBoolean(String tbl, String key, boolean def) {
		if (getContext() == null) {
			return def;
		}
		SharedPreferences prefs = getContext().getAppSharedPreferences(tbl);;
		return prefs.getBoolean(key, def);
	}

	public static float getFloat(String tbl, String key, float def) {
		if (getContext() == null) {
			return def;
		}

		SharedPreferences prefs = getContext().getAppSharedPreferences(tbl);
		return prefs.getFloat(key, def);
	}

	public static <T> T getObject(String tbl, String key,Class<T> cls){
		if (getContext() == null) {
			return null;
		}
		String content = getString(tbl,key,null);
		if(content == null){
			return null;
		}
		try {
			return JSONToBeanHandler.fromJsonString(content,cls);
		} catch (JSONFormatException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void putObject(String tbl, String key, Object value){
		if (getContext() == null) {
			return;
		}
		String content = null;
		if(value != null) {
			try {
				content = JSONToBeanHandler.toJsonString(value);
			} catch (JSONFormatException e) {
				e.printStackTrace();
			}
		}
		putString(tbl,key,content);
	}

	public static void putInt(String tbl, String key, int value) {
		if (getContext() == null) {
			return;
		}
		SharedPreferences prefs = getContext().getAppSharedPreferences(tbl);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt(key, value);
		editor.apply();
	}

	public static void putLong(String tbl, String key, long value, boolean immediately) {
		if (getContext() == null) {
			return;
		}
		SharedPreferences prefs = getContext().getAppSharedPreferences(tbl);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putLong(key, value);
		if(!immediately){
			editor.apply();
		}
		else{
			editor.commit();
		}
	}

	public static void putString(String tbl, String key, String value) {
		if (getContext() == null) {
			return;
		}
		SharedPreferences prefs = getContext().getAppSharedPreferences(tbl);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.apply();
	}

	public static void putBoolean(String tbl, String key, boolean value) {
		if (getContext() == null) {
			return;
		}
		SharedPreferences prefs = getContext().getAppSharedPreferences(tbl);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean(key, value);
		editor.apply();
	}

	public static void putFloat(String tbl, String key, float value) {
		if (getContext() == null) {
			return;
		}
		SharedPreferences prefs = getContext().getAppSharedPreferences(tbl);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putFloat(key, value);
		editor.apply();
	}
}
