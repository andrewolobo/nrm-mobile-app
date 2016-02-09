package com.adeline.tush.nrm.net;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;

public class ci_get extends AsyncTask<Object, Void, Long> {
	public TheObserver m;
	private static String total;

	@Override
	protected Long doInBackground(Object... arg0) {
		send((String) arg0[0], (String[]) arg0[1]);
		return null;
	}

	public static String send(String url, String[] variables) {
		total = "";
		String totals = "";
		try {
			for (int x = 0; x < variables.length; x++) {
				totals += "/" + variables[x];
			}
			URL urls = new URL(url+totals);
			URLConnection ucon = urls.openConnection();
			System.out.println(url+totals);

			/*
			 * Define InputStreams to read from the URLConnection.
			 */
			InputStream is = ucon.getInputStream();
			DataInputStream bis = new DataInputStream(is);
			total = "";
			String null_check = "";
			while (null_check != null) {
				null_check = bis.readLine();
				if (null_check != null) {
					total += null_check;
				}
			}

		} catch (Exception e) {
			System.err.println("An Error Occured:" + e.toString());
		}
		System.out.println(total);
		return total;

	}

	@Override
	protected void onPostExecute(Long arg) {
		m.Callback(total);
		System.out.println(total);
		cancel(true);

		return;
	}
   
	public void setObserver(TheObserver observer) {
		m = observer;
	}

}
