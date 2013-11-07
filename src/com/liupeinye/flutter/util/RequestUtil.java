package com.liupeinye.flutter.util;

public class RequestUtil {

	public static String getRefreshAuthUrl(String refresh_token) {
		return Constants.REFRESH_URL + refresh_token;
	}

	public static String getTimeLineUrl(int pageFlag, long pageTime, int type,
			int contentType, String access_token, String openId) {
		return Constants.TIMELINE_URL + "format=json&pageflag=" + pageFlag
				+ "&pagetime=" + pageTime + "&reqnum=" + Constants.REQUEST_NUM
				+ "&type=" + type + "&contenttype=" + contentType
				+ "&oauth_consumer_key=" + Constants.APP_KEY + "&access_token="
				+ access_token + "&openid=" + openId + "&clientip="
				+ DeviceUtil.getAndroidSDKVersion()
				+ "&oauth_version=2.a&scope=all";
	}

}
