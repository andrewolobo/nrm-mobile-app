package com.adeline.tush.nrm.models;

import java.util.ArrayList;

public class Payload {
	public ArrayList<Events> events;
	public ArrayList<News> news;

	public class Events {
		public Events() {

		}

		public int id;
		public String event_name;
		public String start_date;
		public String description;
		public String end_date;
		public String created;
		public String updated;

	}

	public class News {
		public News() {

		}

		public int id;
		public String story_title;
		public String story;
		public String description;
		public String published;
		public String author;
		public String updated;
		public String created;

	}
}
