package com.adeline.tush.nrm;

import java.util.Arrays;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

/*import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;*/

public class LoginActivity extends Activity {
	public Context context;
/*	protected CallbackManager callbackManager;*/

	public void onCreate(Bundle os) {
		super.onCreate(os);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
/*		FacebookSdk.sdkInitialize(getApplicationContext());*/
		context = this;
		ImageView login = (ImageView) findViewById(R.id.imageView1);
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent n = new Intent(context,
						NavigationActivity.class);
				startActivity(n);
			}
		});
		/*callbackManager = CallbackManager.Factory.create();
		LoginManager.getInstance().registerCallback(callbackManager,
				new FacebookCallback<LoginResult>() {

					@Override
					public void onSuccess(LoginResult result) {
						Toast.makeText(context,
								"Please wait while we log you in..",
								Toast.LENGTH_LONG).show();
						AccessToken accessToken = result.getAccessToken();
						GraphRequest request = GraphRequest.newMeRequest(
								accessToken,
								new GraphRequest.GraphJSONObjectCallback() {
									@Override
									public void onCompleted(JSONObject user,
											GraphResponse response) {
										if (user != null) {
											*//*
											 * Toast.makeText( context, "Hello "
											 * + user.toString() +
											 * ", you have been logged in.",
											 * Toast.LENGTH_LONG).show();
											 *//*
											Intent n = new Intent(context,
													NavigationActivity.class);
											startActivity(n);
											finish();
										}
									}
								});
						request.executeAsync();

					}

					@Override
					public void onCancel() {
						Toast.makeText(context, "Process Cancelled",
								Toast.LENGTH_LONG).show();

					}

					@Override
					public void onError(FacebookException error) {
						Toast.makeText(
								context,
								"It appears an error has occured: "
										+ error.toString(), Toast.LENGTH_LONG)
								.show();

					}
				});
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Toast.makeText(context, "Please wait... ", Toast.LENGTH_LONG)
						.show();
				//Intent n = new Intent(context, NavigationActivity.class);
				//startActivity(n);
				//finish();
				login();

			}

		});*/
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
/*		callbackManager.onActivityResult(requestCode, resultCode, data);*/
	}

	@SuppressWarnings("unused")
	private void login() {/*
		LoginManager.getInstance().logInWithReadPermissions(this,
*//*				Arrays.asList("public_profile", "email"));*/
	}

	@Override
	public void onResume() {
		super.onResume();
/*		AppEventsLogger.activateApp(this);*/
	}

}
