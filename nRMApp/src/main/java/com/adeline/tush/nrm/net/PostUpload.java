package com.adeline.tush.nrm.net;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
/*import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;*/
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.adeline.tush.nrm.shared.Shared;

public class PostUpload extends AsyncTask<Object, Void, Long> {
	public TheObserver m;

	@Override
	protected Long doInBackground(Object... arg0) {
		try {
			executeMultipartPost((String) arg0[0], (String[]) arg0[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void executeMultipartPost(String url, String[] arg0)
			throws Exception {
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(url);
/*			MultipartEntity reqEntity = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE);
			if (Shared.camera_flag) {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Bitmap bm = BitmapFactory.decodeFile("sdcard/index_02.jpg");
				bm.compress(CompressFormat.JPEG, 75, bos);
				byte[] data = Base64.encode(bos.toByteArray(), Base64.NO_WRAP
						| Base64.URL_SAFE);
				ByteArrayBody bab = new ByteArrayBody(data, "index_02.jpg");
				reqEntity.addPart("picture", bab);
				reqEntity.addPart("picture_type", new StringBody("png"));
			}
			for (int i = 0; i < arg0.length; i++) {
				reqEntity.addPart(arg0[i].split("=")[0],
						new StringBody(arg0[i].split("=")[1]));
			}

			postRequest.setEntity(reqEntity);*/
			HttpResponse response = httpClient.execute(postRequest);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent(), "UTF-8"));
			String sResponse;
			StringBuilder s = new StringBuilder();

			while ((sResponse = reader.readLine()) != null) {
				s = s.append(sResponse);
			}
			System.out.println("Response: " + s);
		} catch (Exception e) {
			// handle exception here
			Log.e(e.getClass().getName(), e.getMessage());
		}
	}

	public void setObserver(TheObserver s) {
		this.m = s;
	}

	@Override
	protected void onPostExecute(Long arg0) {
		m.Callback();
		return;
	}

}
