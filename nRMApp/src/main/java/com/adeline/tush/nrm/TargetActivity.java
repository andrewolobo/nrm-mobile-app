package com.adeline.tush.nrm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.adeline.tush.nrm.models.TargetModel;
import com.adeline.tush.nrm.net.TheObserver;
import com.adeline.tush.nrm.net.ci_get;
import com.adeline.tush.nrm.shared.Shared;
import com.google.gson.Gson;

public class TargetActivity extends Activity {
	public Context context;
	public String current_target;

	public void onCreate(Bundle os) {
		super.onCreate(os);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_target);
		ci_get getTarget = new ci_get();
		context = this;
		final LinearLayout scroll = (LinearLayout) findViewById(R.id.scroll);
		Toast.makeText(context, "Loading..", Toast.LENGTH_LONG).show();
		getTarget.setObserver(new TheObserver() {

			@Override
			public void Callback() {

			}

			@Override
			public void Callback(String result) {
				Gson g = new Gson();
				TargetModel targets = g.fromJson(result, TargetModel.class);

				for (int i = 0; i < targets.target.size(); i++) {
					View view = LayoutInflater.from(context).inflate(
							R.layout.event_layout, null);
					view.setTag((String) (targets.target.get(i).id + ""));
					view.setOnClickListener(new OnClickListener() {

						@SuppressWarnings("deprecation")
						@Override
						public void onClick(View arg0) {
							current_target = (String) arg0.getTag();
							View mw = getLayoutInflater().inflate(
									R.layout.pop_up, null);
							PopupWindow m = new PopupWindow(mw, 139, 138, true);
							m.setBackgroundDrawable(new BitmapDrawable());
							m.setOutsideTouchable(true);
							mw.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View arg0) {
									Shared.target_id = current_target;
									Intent n = new Intent(context,
											CommentsActivity.class);
									startActivity(n);
								}

							});

							m.showAsDropDown(arg0, (arg0.getWidth() / 2),
									-(arg0.getHeight() / 2));
						}

					});

					TextView title = (TextView) view
							.findViewById(R.id.textView1);
					TextView story = (TextView) view
							.findViewById(R.id.textView2);

					title.setText(targets.target.get(i).target_name);
					story.setText(targets.target.get(i).target_description);
					scroll.addView(view);
				}
			}

		});
		getTarget.execute(Shared.url + "EventsFeed/getTargets/"
				+ Shared.county_id + "/" + Shared.sector_id, new String[] {});
	}

}
