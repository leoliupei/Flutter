package com.liupeinye.flutter;

import com.liupeinye.flutter.auth.AuthListener;
import com.liupeinye.flutter.auth.Oauth2AccessToken;
import com.liupeinye.flutter.auth.TWeibo;
import com.liupeinye.flutter.util.PrefUtil;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class ActivityNavigation extends ActionBarActivity {

	private ActionBar mActionBar;
	private PullToRefreshAttacher mAttacher;
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigation);

		PrefUtil.init(this);

		mListView = (ListView) findViewById(R.id.main_list);
		mActionBar = getSupportActionBar();

		mAttacher = PullToRefreshAttacher.get(ActivityNavigation.this);

		mAttacher.addRefreshableView(mListView,
				new PullToRefreshAttacher.OnRefreshListener() {

					@Override
					public void onRefreshStarted(View view) {
						// TODO Auto-generated method stub
						new AsyncTask<Void, Void, Void>() {

							@Override
							protected Void doInBackground(Void... params) {
								try {
									Thread.sleep(3000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								return null;
							}

							@Override
							protected void onPostExecute(Void result) {
								super.onPostExecute(result);

								// Notify PullToRefreshAttacher that the refresh
								// has finished
								mAttacher.setRefreshComplete();
							}
						}.execute();

					}
				});
		// mActionBar.setDisplayHomeAsUpEnabled(true);

		TWeibo tWeibo = TWeibo.getInstance(this);

		Oauth2AccessToken token = PrefUtil.getToken();

		if (token.isSessionValid()) {
			
			//产生List
			System.out.println(token);
			System.out.println(PrefUtil.getString("openid", ""));
			System.out.println(PrefUtil.getString("nick", ""));
			
		} else {

			tWeibo.startAuth(new AuthListener() {

				@Override
				public void onException(String exception) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onError(String error) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onComplete(Bundle values) {
					// TODO Auto-generated method stub
					Oauth2AccessToken token = new Oauth2AccessToken(values
							.getString("access_token"), values
							.getString("refresh_token"), values
							.getString("expires_in"));
					PrefUtil.putToken(token);
					PrefUtil.putString("openkey", values.getString("openkey"));
					PrefUtil.putString("nick", values.getString("nick"));
					PrefUtil.putString("name", values.getString("name"));
					PrefUtil.putString("openid", values.getString("openid"));
				}

				@Override
				public void onCancel() {
					// TODO Auto-generated method stub

				}
			});
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_navigation, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

}
