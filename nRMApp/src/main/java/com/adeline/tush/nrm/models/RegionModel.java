package com.adeline.tush.nrm.models;

import java.util.ArrayList;

public class RegionModel {
	public ArrayList<Regions> regions;

	public class Regions {
		public int id;
		public String region_name;
		public String description;
		public String updated;
		public String created;
	}

}
