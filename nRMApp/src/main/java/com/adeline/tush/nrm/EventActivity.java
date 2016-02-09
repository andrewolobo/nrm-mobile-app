package com.adeline.tush.nrm;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adeline.tush.nrm.models.Payload;
import com.adeline.tush.nrm.net.TheObserver;
import com.adeline.tush.nrm.net.ci_get;
import com.adeline.tush.nrm.shared.Shared;
import com.google.gson.Gson;

public class EventActivity extends Activity {
	public Context context;

	public void onCreate(Bundle os) {
		super.onCreate(os);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		context = this;
		setContentView(R.layout.event_activity);
		ci_get d = new ci_get();
		Toast.makeText(context, "Loading Feed", Toast.LENGTH_LONG).show();
		d.setObserver(new TheObserver() {

			@Override
			public void Callback() {
				System.out.println("EMPTY_FUNCTION_RETURN");
			}

			@Override
			public void Callback(String result) {
				System.out.println(result);
				Gson g = new Gson();
				Payload events = g.fromJson(result, Payload.class);

				for (int i = 0; i < events.events.size(); i++) {
					View view = LayoutInflater.from(context).inflate(
							R.layout.event_layout, null);
					LinearLayout scroll = (LinearLayout) findViewById(R.id.scroll);
					TextView title = (TextView) view
							.findViewById(R.id.textView1);
					TextView description = (TextView) view
							.findViewById(R.id.textView2);
					TextView date = (TextView) view
							.findViewById(R.id.textView3);
					title.setTypeface(Typeface.createFromAsset(getAssets(),
							"fonts/ROCKEB.TTF"));
					title.setText(events.events.get(i).event_name);
					description.setText(events.events.get(i).description);
					date.setText(events.events.get(i).start_date);
					scroll.addView(view);
				}

			}

		});
		d.execute(Shared.url + "EventsFeed/mobile", new String[] {});

	}
}
