package com.adeline.tush.nrm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.adeline.tush.nrm.models.ManifestoModel;
import com.adeline.tush.nrm.net.TheObserver;
import com.adeline.tush.nrm.net.ci_get;
import com.adeline.tush.nrm.shared.Shared;
import com.google.gson.Gson;

public class ManifestoActivity extends Activity {
	public Context context;

	public void onCreate(Bundle os) {
		super.onCreate(os);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		context = this;
		setContentView(R.layout.manifesto_activity);
		ci_get d = new ci_get();
		Toast.makeText(context, "Loading Manifesto", Toast.LENGTH_LONG).show();
		d.setObserver(new TheObserver() {

			@Override
			public void Callback() {
				// TODO Auto-generated method stub

			}

			@Override
			public void Callback(String result) {
				Gson g = new Gson();
				ManifestoModel manifesto = g.fromJson(result,
						ManifestoModel.class);
				TextView manifestot = (TextView) findViewById(R.id.textView1);
				manifestot.setText(manifesto.manifestos.get(0).manifesto_text);

			}
		});
		d.execute(Shared.url + "EventsFeed/listManifesto/" + Shared.manifesto,
				new String[] {});
	}

}
