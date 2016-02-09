package com.adeline.tush.nrm;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.adeline.tush.nrm.models.CountyModel;
import com.adeline.tush.nrm.models.CountyModel.County;
import com.adeline.tush.nrm.models.DistrictModel;
import com.adeline.tush.nrm.models.DistrictModel.District;
import com.adeline.tush.nrm.models.RegionModel;
import com.adeline.tush.nrm.models.RegionModel.Regions;
import com.adeline.tush.nrm.models.SectorModel;
import com.adeline.tush.nrm.models.SectorModel.Sector;
import com.adeline.tush.nrm.net.TheObserver;
import com.adeline.tush.nrm.net.ci_get;
import com.adeline.tush.nrm.shared.Shared;
import com.google.gson.Gson;

public class SectorActivity extends Activity {
	public Context context;
	public int d_counter;
	public int r_counter;
	public static String county_id;
	public static String sector_id;

	public void onCreate(Bundle os) {
		super.onCreate(os);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		d_counter = 0;
		r_counter = 0;
		context = this;
		setContentView(R.layout.sector_activity);
		((TextView) findViewById(R.id.textView1)).setTypeface(Typeface
				.createFromAsset(getAssets(), "fonts/ROCKEB.TTF"));
		((TextView) findViewById(R.id.textView4)).setTypeface(Typeface
				.createFromAsset(getAssets(), "fonts/ROCKEB.TTF"));

		ci_get getRegions = new ci_get();
		getRegions.setObserver(new TheObserver() {

			@Override
			public void Callback() {

			}

			@Override
			public void Callback(String result) {

				Gson g = new Gson();
				RegionModel region = g.fromJson(result, RegionModel.class);
				if (region.regions.size() > 0) {
					final HashMap<String, Integer> r_map = new HashMap<String, Integer>();
					final ArrayList<String> r_array = new ArrayList<String>();
					r_array.add("Please Select a Region");
					for (Regions object : region.regions) {
						r_array.add(object.region_name);
						r_map.put(object.region_name, object.id);

					}

					Spinner r_spinner = (Spinner) findViewById(R.id.spinner1);

					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							getApplicationContext(),
							android.R.layout.simple_spinner_item, r_array);
					r_spinner
							.setOnItemSelectedListener(new OnItemSelectedListener() {
								@Override
								public void onItemSelected(
										AdapterView<?> parentView,
										View selectedItemView, int position,
										long id) {
									if (position != 0) {
										String region_id = r_map
												.get((String) r_array
														.get(position))
												+ "";
										ci_get getDistricts = new ci_get();
										getDistricts
												.setObserver(new TheObserver() {

													@Override
													public void Callback() {

													}

													@Override
													public void Callback(
															String result) {

														Gson g = new Gson();
														DistrictModel district = g
																.fromJson(
																		result,
																		DistrictModel.class);
														if (district.districts
																.size() > 0) {
															final HashMap<String, Integer> d_map = new HashMap<String, Integer>();
															final ArrayList<String> d_array = new ArrayList<String>();
															d_array.add("Please select a district");
															for (District object : district.districts) {
																d_array.add(object.district_name);
																d_map.put(
																		object.district_name,
																		object.id);

															}

															Spinner d_spinner = (Spinner) findViewById(R.id.spinner2);

															ArrayAdapter<String> adapter = new ArrayAdapter<String>(
																	getApplicationContext(),
																	android.R.layout.simple_spinner_item,
																	d_array);
															d_spinner
																	.setOnItemSelectedListener(new OnItemSelectedListener() {

																		@Override
																		public void onItemSelected(
																				AdapterView<?> arg0,
																				View arg1,
																				int arg2,
																				long arg3) {
																			if (arg2 != 0) {
																				String district_id = d_map
																						.get((String) d_array
																								.get(arg2))
																						+ "";
																				ci_get getCounties = new ci_get();
																				getCounties
																						.setObserver(new TheObserver() {

																							@Override
																							public void Callback() {

																							}

																							@Override
																							public void Callback(
																									String result) {

																								Gson g = new Gson();
																								CountyModel county = g
																										.fromJson(
																												result,
																												CountyModel.class);
																								if (county.county
																										.size() > 0) {
																									final HashMap<String, Integer> c_map = new HashMap<String, Integer>();
																									final ArrayList<String> c_array = new ArrayList<String>();
																									c_array.add("Please select a County");
																									for (County object : county.county) {
																										c_array.add(object.name);
																										c_map.put(
																												object.name,
																												object.id);

																									}

																									Spinner d_spinner = (Spinner) findViewById(R.id.spinner3);

																									ArrayAdapter<String> adapter = new ArrayAdapter<String>(
																											getApplicationContext(),
																											android.R.layout.simple_spinner_item,
																											c_array);
																									d_spinner
																											.setOnItemSelectedListener(new OnItemSelectedListener() {

																												@Override
																												public void onItemSelected(
																														AdapterView<?> arg0,
																														View arg1,
																														int arg2,
																														long arg3) {
																													if (arg2 != 0) {
																														String county_id = r_map
																																.get((String) r_array
																																		.get(arg2))
																																+ "";
																														SectorActivity.county_id = county_id;
																														final Spinner sector = new Spinner(
																																context);
																														LinearLayout spinnerLayout = (LinearLayout) findViewById(R.id.spinnerLayout);
																														spinnerLayout
																																.addView(sector);
																														final HashMap<String, Integer> s_map = new HashMap<String, Integer>();
																														final ArrayList<String> s_array = new ArrayList<String>();
																														sector.setOnItemSelectedListener(new OnItemSelectedListener() {

																															@Override
																															public void onItemSelected(
																																	AdapterView<?> arg0,
																																	View arg1,
																																	int arg2,
																																	long arg3) {
																																if (arg2 != 0) {
																																	SectorActivity.sector_id = s_map
																																			.get((String) s_array
																																					.get(arg2))
																																			+ "";
																																	Shared.sector_id = SectorActivity.sector_id;
																																	Shared.county_id = SectorActivity.county_id;
																																	Intent n = new Intent(
																																			context,
																																			TargetActivity.class);
																																	startActivity(n);
																																}
																															}

																															@Override
																															public void onNothingSelected(
																																	AdapterView<?> arg0) {

																															}

																														});
																														ci_get sectors = new ci_get();
																														sectors.setObserver(new TheObserver() {

																															@Override
																															public void Callback() {

																															}

																															@Override
																															public void Callback(
																																	String result) {
																																Gson g = new Gson();
																																SectorModel sect = g
																																		.fromJson(
																																				result,
																																				SectorModel.class);

																																s_array.add("Please select a Sector");
																																for (Sector object : sect.sector) {
																																	s_array.add(object.name);
																																	s_map.put(
																																			object.name,
																																			object.id);

																																}
																																ArrayAdapter<String> adapter = new ArrayAdapter<String>(
																																		getApplicationContext(),
																																		android.R.layout.simple_spinner_item,
																																		s_array);
																																adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
																																sector.setAdapter(adapter);

																															}

																														});
																														sectors.execute(
																																Shared.url
																																		+ "EventsFeed/listSectors/"
																																		+ county_id,
																																new String[] {});

																													}

																												}

																												@Override
																												public void onNothingSelected(
																														AdapterView<?> arg0) {

																												}

																											});

																									adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
																									d_spinner
																											.setAdapter(adapter);
																								}
																							}

																						});
																				getCounties
																						.execute(
																								Shared.url
																										+ "EventsFeed/listCounties/"
																										+ district_id,
																								new String[] {});
																			}

																		}

																		@Override
																		public void onNothingSelected(
																				AdapterView<?> arg0) {

																		}
																	});

															adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
															d_spinner
																	.setAdapter(adapter);
														}
													}

												});
										getDistricts.execute(Shared.url
												+ "EventsFeed/listDistricts/"
												+ region_id, new String[] {});
									}

								}

								@Override
								public void onNothingSelected(
										AdapterView<?> parentView) {
									// your code here
								}

							});
					adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					r_spinner.setAdapter(adapter);

				}
			}

		});
		getRegions.execute(Shared.url + "EventsFeed/listRegions",
				new String[] {});
	}
}
