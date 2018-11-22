package io.github.polet.ens.model.laws;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import io.github.polet.ens.model.ILaw;

public class CSVLaw implements ILaw{
	
	private CSVReader reader = null;
	private String[] form;
	private long inital_timer;
	
	private String[] next;
	private long delta_timer = 0;

	
	public CSVLaw(String filename) {
        try {
            reader = new CSVReader(new FileReader(filename));
            form = reader.readNext();
            next = reader.readNext();
            inital_timer = Long.parseLong(next[0]);
        } catch (IOException e) {
        	System.out.println("WARNING : csv file '" + filename + "' cant be open.");
            e.printStackTrace();
        }

	}
	
	@Override
	public long deltaNext() {
		return delta_timer;
	}

	@Override
	public String nextData() {
		String s = "";
		for(int i = 1; i < form.length; i++) {
			s = s + form[i] + "=" + next[i] + " ";
		}
		try {
			next = reader.readNext();
		} catch (IOException e) { // TODO add debug message
			e.printStackTrace();
		}
		
		if(next != null) {
			this.delta_timer = Long.parseLong(next[0]) - inital_timer;
		}
		
		return s;
	}
	
	@Override
	public boolean hasNext() {
		return next != null;
	}
	
}
