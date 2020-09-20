package model.comparators;

import java.util.Comparator;

import model.Client;

public class NumberClientComparator implements Comparator<Client>{

	@Override
	public int compare(Client c1, Client c2) {
		int comp;
		double phoneC1 = Double.parseDouble(c1.getPhone()); 
		double phoneC2 = Double.parseDouble(c2.getPhone());
		if(phoneC1>phoneC2) {
			comp = 1;
		}else if(phoneC1<phoneC2) {
			comp = -1;
		}else {
			comp = 0;
		}
		return comp;
	}

}
