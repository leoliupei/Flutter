package com.liupeinye.flutter.auth;

import android.os.Bundle;

public interface AuthListener {

	public void onComplete(Bundle values);

	public void onException(String exception);

	public void onError(String error);

	public void onCancel();
}
