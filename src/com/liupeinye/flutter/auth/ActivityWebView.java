package com.liupeinye.flutter.auth;

import com.liupeinye.flutter.util.Constants;
import com.liupeinye.flutter.util.JsonUtil;
import com.liupeinye.flutter.util.RequestUtil;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ActivityWebView extends ActionBarActivity {

	public static AuthListener mListener;
	private WebView mWebView;
	private boolean normalExit = false;

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (!normalExit && mListener != null) {
			mListener.onCancel();
		}
		super.onDestroy();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mWebView = new WebView(this);
		setContentView(mWebView);
		mWebView.setVerticalScrollBarEnabled(false);
		mWebView.setHorizontalScrollBarEnabled(false);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setDomStorageEnabled(true);
		mWebView.setWebViewClient(new MyWebViewClient());
		mWebView.loadUrl(RequestUtil.getAuthUrl());
	}

	private class MyWebViewClient extends WebViewClient {
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			if (url.startsWith(Constants.REDIRECT_URL)) {
				handleRedirectUrl(url);
				finish();
				normalExit = true;
				return;
			}
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
		}

		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			// TODO Auto-generated method stub
			if (mListener != null) {
				mListener.onError("errorCode: " + errorCode + " description: "
						+ description + " failingUrl: " + failingUrl);
			}
			super.onReceivedError(view, errorCode, description, failingUrl);
		}
		
		

		@Override
		public void onReceivedSslError(WebView view, SslErrorHandler handler,
				SslError error) {
			// TODO Auto-generated method stub
			handler.proceed();
		}

	}

	private void handleRedirectUrl(String url) {
		Bundle values = JsonUtil.parseUrlBundle(url);

		String error = values.getString("error");
		String error_code = values.getString("error_code");

		if (error == null && error_code == null && mListener != null) {
			mListener.onComplete(values);
		} else if (error.equals("access_denied")) {
			mListener.onCancel();
		} else {
			mListener.onException(error_code);
		}
	}

}
