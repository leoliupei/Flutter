package com.liupeinye.flutter.auth;

import android.text.TextUtils;

public class Oauth2AccessToken {
	private String mAccessToken = "";
	private String mRefreshToken = "";
	private long mExpiresTime = 0;

	public String toString() {
		return mAccessToken + "," + mRefreshToken + "," + mExpiresTime;
	}

	/**
	 * Oauth2AccessToken 的构造函数
	 */
	public Oauth2AccessToken() {
	}

	/**
	 * Oauth2AccessToken的构造函数，根据accessToken 和expires_in 生成Oauth2AccessToken实例
	 * 
	 * @param accessToken
	 *            访问令牌
	 * @param expires_in
	 *            有效期，单位：毫秒；仅当从服务器获取到expires_in时适用，表示距离超过认证时间还有多少秒
	 */
	public Oauth2AccessToken(String accessToken, String refreshToken,
			String expires_in) {
		mAccessToken = accessToken;
		mRefreshToken = refreshToken;
		mExpiresTime = System.currentTimeMillis() + Long.parseLong(expires_in)
				* 1000;
	}

	/**
	 * Oauth2AccessToken的构造函数，根据accessToken 和expires_in 生成Oauth2AccessToken实例
	 * 
	 * @param accessToken
	 *            访问令牌
	 * @param expires_in
	 *            有效期，单位：毫秒；
	 */
	public Oauth2AccessToken(String accessToken, String refreshToken,
			long expires_in) {
		mAccessToken = accessToken;
		mRefreshToken = refreshToken;
		mExpiresTime = expires_in;
	}

	/**
	 * AccessToken是否有效,如果accessToken为空或者expiresTime过期，返回false，否则返回true
	 * 
	 * @return 如果accessToken为空或者expiresTime过期，返回false，否则返回true
	 */
	public boolean isSessionValid() {
		return (!TextUtils.isEmpty(mAccessToken)
				&& !TextUtils.isEmpty(mRefreshToken) && (mExpiresTime == 0 || (System
				.currentTimeMillis() < mExpiresTime)));
	}

	/**
	 * 获取accessToken
	 */
	public String getToken() {
		return this.mAccessToken;
	}

	public String getRefreshToken() {
		return this.mRefreshToken;
	}

	/**
	 * 获取超时时间，单位: 毫秒，表示从格林威治时间1970年01月01日00时00分00秒起至现在的总 毫秒数
	 */
	public long getExpiresTime() {
		return mExpiresTime;
	}

	/**
	 * 设置过期时间长度值，仅当从服务器获取到数据时使用此方法
	 * 
	 */
	public void setExpiresIn(String expiresIn) {
		if (expiresIn != null && !expiresIn.equals("0")) {
			setExpiresTime(System.currentTimeMillis()
					+ Long.parseLong(expiresIn) * 1000);
		}
	}

	/**
	 * 设置过期时刻点 时间值
	 * 
	 * @param mExpiresTime
	 *            单位：毫秒，表示从格林威治时间1970年01月01日00时00分00秒起至现在的总 毫秒数
	 * 
	 */
	public void setExpiresTime(long mExpiresTime) {
		this.mExpiresTime = mExpiresTime;
	}

	/**
	 * 设置accessToken
	 * 
	 * @param mToken
	 */
	public void setToken(String mToken) {
		this.mAccessToken = mToken;
	}

	public void setRefreshToken(String mToken) {
		this.mRefreshToken = mToken;
	}
}