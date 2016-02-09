package com.adeline.tush.nrm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NavigationActivity extends Activity {
	public Context context;

	public void onCreate(Bundle os) {
		super.onCreate(os);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		context = this;
		setContentView(R.layout.activity_navigation);
		LinearLayout events = (LinearLayout) findViewById(R.id.events);
		((TextView) findViewById(R.id.textView1)).setTypeface(Typeface
				.createFromAsset(getAssets(), "fonts/ROCKEB.TTF"));
		((TextView) findViewById(R.id.textView25)).setTypeface(Typeface
				.createFromAsset(getAssets(), "fonts/ROCKEB.TTF"));
		((TextView) findViewById(R.id.textView3)).setTypeface(Typeface
				.createFromAsset(getAssets(), "fonts/ROCKEB.TTF"));
		((TextView) findViewById(R.id.textView35)).setTypeface(Typeface
				.createFromAsset(getAssets(), "fonts/ROCKEB.TTF"));
		((TextView) findViewById(R.id.manifesto)).setTypeface(Typeface
				.createFromAsset(getAssets(), "fonts/ROCKEB.TTF"));
		events.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context, EventActivity.class);
				startActivity(intent);

			}

		});
		LinearLayout news = (LinearLayout) findViewById(R.id.news);
		news.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context, NewsActivity.class);
				startActivity(intent);

			}

		});
		LinearLayout manifesto = (LinearLayout) findViewById(R.id.manifesto_linear);
		manifesto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context, RegionActivity.class);
				startActivity(intent);

			}

		});
		((LinearLayout) findViewById(R.id.sectors))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(context,
								SectorActivity.class);
						startActivity(intent);

					}

				});
	}
}
