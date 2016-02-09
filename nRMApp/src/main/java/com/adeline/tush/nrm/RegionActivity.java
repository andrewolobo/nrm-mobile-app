package com.adeline.tush.nrm;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.adeline.tush.nrm.models.ContestantModel;
import com.adeline.tush.nrm.models.ContestantModel.Contestant;
import com.adeline.tush.nrm.models.DistrictModel;
import com.adeline.tush.nrm.models.DistrictModel.District;
import com.adeline.tush.nrm.models.RegionModel;
import com.adeline.tush.nrm.models.RegionModel.Regions;
import com.adeline.tush.nrm.net.TheObserver;
import com.adeline.tush.nrm.net.ci_get;
import com.adeline.tush.nrm.shared.Shared;
import com.google.gson.Gson;

public class RegionActivity extends Activity {
	public Context context;

	public void onCreate(Bundle os) {
		super.onCreate(os);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		context = this;
		setContentView(R.layout.activity_region);
		final TextView description = (TextView) findViewById(R.id.textView2);
		ci_get d = new ci_get();
		Toast.makeText(context, "Loading Regions", Toast.LENGTH_LONG).show();
		d.setObserver(new TheObserver() {

			@Override
			public void Callback() {
				System.out.println("EMPTY_FUNCTION_RETURN");
			}

			@Override
			public void Callback(String result) {
				System.out.println(result);
				Gson g = new Gson();
				RegionModel regions = g.fromJson(result, RegionModel.class);

				final ListView listView = (ListView) findViewById(R.id.listView1);
				final ArrayList<String> r = new ArrayList<String>();
				final HashMap<String, Integer> rs = new HashMap<String, Integer>();
				for (Regions object : regions.regions) {
					r.add(object.region_name);
					rs.put(object.region_name, object.id);
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						context, android.R.layout.simple_list_item_1, r);
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {

						ci_get r_district = new ci_get();
						r_district.setObserver(new TheObserver() {

							@Override
							public void Callback() {

							}

							@Override
							public void Callback(String result) {
								description
										.setText("Please Select a District:");
								Gson g = new Gson();
								DistrictModel districtModel = g.fromJson(
										result, DistrictModel.class);
								r.clear();
								for (District object : districtModel.districts) {
									r.add(object.district_name);
									rs.put(object.district_name, object.id);
								}
								ArrayAdapter<String> adapter = new ArrayAdapter<String>(
										context,
										android.R.layout.simple_list_item_1, r);
								listView.setAdapter(adapter);
								listView.setOnItemClickListener(new OnItemClickListener() {

									@Override
									public void onItemClick(
											AdapterView<?> arg0, View arg1,
											int arg2, long arg3) {
										ci_get r_district = new ci_get();
										r_district
												.setObserver(new TheObserver() {

													@Override
													public void Callback() {

													}

													@Override
													public void Callback(
															String result) {
														description
																.setText("Please Select your Candidate");
														Gson g = new Gson();
														ContestantModel contestantModel = g
																.fromJson(
																		result,
																		ContestantModel.class);
														r.clear();
														for (Contestant object : contestantModel.contestants) {
															r.add(object.name);
															rs.put(object.name,
																	object.id);
														}
														ArrayAdapter<String> adapter = new ArrayAdapter<String>(
																context,
																android.R.layout.simple_list_item_1,
																r);
														listView.setAdapter(adapter);
														listView.setOnItemClickListener(new OnItemClickListener() {

															@Override
															public void onItemClick(
																	AdapterView<?> arg0,
																	View arg1,
																	int arg2,
																	long arg3) {
																// /EventsFeed/listManifesto/1
																Shared.manifesto = rs
																		.get(r.get(arg2))
																		.toString();
																Intent intent = new Intent(
																		context,
																		ManifestoActivity.class);
																startActivity(intent);

															}

														});

													}

												});
										r_district.execute(Shared.url
												+ "EventsFeed/listContestants/"
												+ rs.get((String) r.get(arg2)),
												new String[] {});

									}

								});

							}

						});
						r_district.execute(
								Shared.url + "EventsFeed/listDistricts/"
										+ rs.get((String) r.get(arg2)),
								new String[] {});
					}

				});
				listView.setAdapter(adapter);

			}

		});
		d.execute(Shared.url + "EventsFeed/listRegions", new String[] {});

	}
}
