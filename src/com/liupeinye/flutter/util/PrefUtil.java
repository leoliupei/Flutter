package com.liupeinye.flutter.util;

import com.liupeinye.flutter.auth.Oauth2AccessToken;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtil {

	private static final String PREF_NAME = "flutter";
	private static SharedPreferences pref;

	public static void init(Context context) {
		pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
	}

	public static void putString(String key, String value) {
		pref.edit().putString(key, value).commit();
	}

	public static String getString(String key, String defValue) {
		return pref.getString(key, defValue);
	}

	public static void putLong(String key, long value) {
		pref.edit().putLong(key, value).commit();
	}

	public static long getLong(String key, long defValue) {
		return pref.getLong(key, defValue);
	}

	public static void putBool(String key, boolean value) {
		pref.edit().putBoolean(key, value).commit();
	}

	public static boolean getBool(String key, boolean defValue) {
		return pref.getBoolean(key, defValue);
	}

	public static void putToken(Oauth2AccessToken token) {
		putString("access_token", token.getToken());
		putString("refresh_token", token.getRefreshToken());
		putLong("expires_in", token.getExpiresTime());
	}

	public static Oauth2AccessToken getToken() {
		Oauth2AccessToken token = new Oauth2AccessToken(pref.getString(
				"access_token", ""), pref.getString("refresh_token", ""),
				pref.getLong("expires_in", 0));
		return token;
	}

}
