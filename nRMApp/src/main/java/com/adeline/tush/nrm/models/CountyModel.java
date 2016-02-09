package com.adeline.tush.nrm.models;

import java.util.ArrayList;

public class CountyModel {

		public ArrayList<County> county;
		
		public class County{
			public int id;
			public String name;
			public int district_id;
			public String description;
			public String created;
			public String updated;
		}
}
