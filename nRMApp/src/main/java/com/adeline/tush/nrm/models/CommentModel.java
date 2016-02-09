package com.adeline.tush.nrm.models;

import java.util.ArrayList;

public class CommentModel {
	public ArrayList<Comment> comments;

	public class Comment {
		public String username;
		public String comment;
		public String created;
		public String updated;
	}
}
