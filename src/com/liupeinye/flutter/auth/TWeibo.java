package com.liupeinye.flutter.auth;

import com.android.volley.RequestQueue;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liupeinye.flutter.R;
import com.liupeinye.flutter.util.Constants;
import com.liupeinye.flutter.util.JsonUtil;
import com.liupeinye.flutter.util.PrefUtil;
import com.liupeinye.flutter.util.RequestUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.TextView;

public class TWeibo {

	private Context mContext;
	private static TWeibo mTWeibo;
	private RequestQueue mQueue;

	public static TWeibo getInstance(Context context) {

		if (mTWeibo == null)
			mTWeibo = new TWeibo(context);
		return mTWeibo;
	}

	public TWeibo(Context context) {
		mContext = context;
		mQueue = Volley.newRequestQueue(mContext);
	}

	public void startAuth(AuthListener listener) {
		Intent intent = new Intent();
		intent.setClass(mContext, ActivityWebView.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		ActivityWebView.mListener = null;
		ActivityWebView.mListener = listener;
		mContext.startActivity(intent);
	}

	public Oauth2AccessToken refreshAuth(Oauth2AccessToken access_token) {
		String refresh_token = access_token.getRefreshToken();
		if (!TextUtils.isEmpty(refresh_token)) {
			mQueue.add(new StringRequest(Method.POST, RequestUtil
					.getRefreshAuthUrl(refresh_token),
					new Response.Listener<String>() {

						@Override
						public void onResponse(String response) {
							// TODO Auto-generated method stub
							Bundle values = JsonUtil.decodeUrlBundle(response);
							Oauth2AccessToken token = new Oauth2AccessToken(
									values.getString("access_token"), values
											.getString("refresh_token"), values
											.getString("expires_in"));
							PrefUtil.putToken(token);
							PrefUtil.putString("nick", values.getString("nick"));
							PrefUtil.putString("name", values.getString("name"));
							PrefUtil.putString("openid",
									values.getString("openid"));
						}
					}, new Response.ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError error) {
							// TODO Auto-generated method stub
						}
					}));
			mQueue.start();
		}
		return null;
	}

}
