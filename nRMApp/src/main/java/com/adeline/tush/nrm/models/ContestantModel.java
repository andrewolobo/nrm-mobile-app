package com.adeline.tush.nrm.models;

import java.util.ArrayList;

public class ContestantModel {
	public ArrayList<Contestant> contestants;

	public class Contestant {
		public int id;
		public String name;
		public String description;
		public int active;
		public String created;
		public String updated;
	}

}
