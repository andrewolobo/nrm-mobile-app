package com.adeline.tush.nrm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.adeline.tush.nrm.models.CommentModel;
import com.adeline.tush.nrm.net.TheObserver;
import com.adeline.tush.nrm.net.ci_get;
import com.adeline.tush.nrm.shared.Shared;
import com.google.gson.Gson;

public class CommentsActivity extends Activity {
	public Context context;

	public void onCreate(Bundle os) {
		super.onCreate(os);
		context = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_comments);
		ci_get getComments = new ci_get();
		getComments.setObserver(new TheObserver() {

			@Override
			public void Callback() {

			}

			@Override
			public void Callback(String result) {
				Gson g = new Gson();
				CommentModel comments = g.fromJson(result, CommentModel.class);
				Toast.makeText(context, comments.comments.get(0).comment,
						Toast.LENGTH_LONG).show();

			}

		});
		getComments.execute(Shared.url + "EventsFeed/listComments/"
				+ Shared.target_id, new String[] {});

	}

}
