package com.liupeinye.flutter.util;

public class Constants {
	
	public static final int REQUEST_NUM = 20;
	public static final String APP_KEY = "801439648";
	public static final String APP_SECRET = "3ab1c12b6faf46169a1c0df559daadb2";
	
	public static final String REDIRECT_URL = "http://www.liupeinye.com";
	public static final String AUTH_URL = "https://open.t.qq.com/cgi-bin/oauth2/authorize?client_id=" + APP_KEY + "&wap=2&response_type=token&redirect_uri=" + REDIRECT_URL;
	public static final String REFRESH_URL = "https://open.t.qq.com/cgi-bin/oauth2/access_token?client_id=" + APP_KEY + "&grant_type=refresh_token&refresh_token=";
	public static final String TIMELINE_URL = "https://open.t.qq.com/api/statuses/home_timeline?";
	//https://open.t.qq.com/cgi-bin/oauth2/access_token?client_id=APP_KEY&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
	//https://open.t.qq.com/api/statuses/home_timeline?format=json&pageflag=0&pagetime=0&reqnum=20&type=0&contenttype=0&oauth_consumer_key=801439648&access_token=745cf797a9bb6df3cf5fa5f06a4c828f&openid=8e9893cd79a456f9b3d82e837a343cfe&clientip=124.127.108.131&oauth_version=2.a&scope=all
}
