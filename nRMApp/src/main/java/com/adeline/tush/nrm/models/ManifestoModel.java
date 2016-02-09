package com.adeline.tush.nrm.models;

import java.util.ArrayList;

public class ManifestoModel {
	public ArrayList<Manifesto> manifestos;

	public class Manifesto {
		public int id;
		public int nrm_id;
		public String manifesto_text;
		public String created;
		public String updated;
	}

}
