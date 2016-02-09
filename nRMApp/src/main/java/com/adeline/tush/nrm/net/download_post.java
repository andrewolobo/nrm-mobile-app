package com.adeline.tush.nrm.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;

public class download_post extends AsyncTask<Object, Void, Long> {
	public TheObserver m;
	private static String total;

	@Override
	protected Long doInBackground(Object... arg0) {
		send((String) arg0[0], (String[]) arg0[1]);
		return null;
	}

	public static String send(String url, String[] variables) {
		BufferedReader s = null;
		String ts;
		total = "";
		try {
			url = url + "?";

			if (variables.length != 0) {
				for (int x = 0; x < variables.length - 1; x++) {
					url += variables[x].split("=")[0] + "="
							+ variables[x].split("=")[1] + "&";
				}

				url += variables[variables.length - 1].split("=")[0] + "="
						+ variables[variables.length - 1].split("=")[1];
			} else {
				url += variables[0].split("=")[0] + "="
						+ variables[0].split("=")[1];
			}
			URL site = new URL(url);
			URLConnection ucon = site.openConnection();
			System.out.println(url);
			s = new BufferedReader(new InputStreamReader(ucon.getInputStream()));
			while ((ts = s.readLine()) != null) {
				total += ts;
			}

		} catch (Exception e) {
			ts = e.toString();
		}
		System.out.println(total);
		return total;

	}

	@Override
	protected void onPostExecute(Long arg) {
		m.Callback();
		System.out.println(total);
		this.cancel(true);

		return;
	}

	public void setObserver(TheObserver observer) {
		m = observer;
	}

}
