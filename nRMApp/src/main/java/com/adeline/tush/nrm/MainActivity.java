package com.adeline.tush.nrm;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity {
	public Context context;
	static LayoutParams params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		final ImageView logo = (ImageView) findViewById(R.id.imageView1);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		Timer t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {

				Intent n = new Intent(context, LoginActivity.class);
				startActivity(n);
				finish();

			}
		}, 4000);
		TranslateAnimation animate = new TranslateAnimation(1500, 0, 0, 0);
		animate.setDuration(1000);
		animate.setFillAfter(true);
		logo.setAnimation(animate);
	}

}
