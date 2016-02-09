package com.adeline.tush.nrm.models;

import java.util.ArrayList;

public class DistrictModel {
	public ArrayList<District> districts;

	public class District {
		public int id;
		public String district_name;
		public String description;
		public int region_id;
		public String created;
		public String updated;

	}

}
