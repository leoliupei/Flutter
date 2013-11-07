package com.liupeinye.flutter.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import android.os.Bundle;

public class JsonUtil {
	
	public static Bundle parseUrlBundle(String url) {
		try {
			URL u = new URL(url);
			Bundle bundle = decodeUrlBundle(u.getQuery());
			bundle.putAll(decodeUrlBundle(u.getRef()));
			return bundle;
		} catch (MalformedURLException e) {
			return new Bundle();
		}

	}

	public static Bundle decodeUrlBundle(String s) {
		Bundle params = new Bundle();
		if (s != null) {
			String array[] = s.split("&");
			for (String parameter : array) {
				String v[] = parameter.split("=");
				if (v.length > 1)
					params.putString(URLDecoder.decode(v[0]),
							URLDecoder.decode(v[1]));
			}
		}
		return params;
	}

}
