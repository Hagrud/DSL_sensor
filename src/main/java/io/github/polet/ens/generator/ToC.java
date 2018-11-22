package io.github.polet.ens.generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.TreeSet;

import io.github.polet.ens.model.*;
import io.github.polet.ens.model.elements.Sensor;

public class ToC extends Visitor<StringBuffer> {

	private final static String CURRENT_STATE = "current_state";
	private long current_time = 0;
	private String db_name;
	
	public ToC() {

	}

	@Override
	public void visit(Net net) {
		System.out.println("Initialise network : " + net.getName());
		for(IElement e : net.getElements()) {
			e.init(this);
		}
	}

	@Override
	public void visit(Sensor s) {
		try {
			String toSend = s.getName() + s.getData() + this.current_time + "000000";
			URL url = new URL(db_name);		//peut-etre qu'initialiser qu'une fois ça marche ?
			URLConnection connection = url.openConnection();
	        connection.setDoOutput(true);
	        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
	        out.write(toSend);
	        out.close();
	        
	        	// plus besoin de cette partie ?
	        BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    connection.getInputStream()));
	        String decodedString;
	        while ((decodedString = in.readLine()) != null) {
	        	System.out.println(decodedString);
	        }
	        in.close();
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Init
	 */
	public void setDataBase(String database_name) {
		this.db_name = database_name;
	}

	/**
	 * Event 
	 **/
	
	TreeSet<Event> events = new TreeSet<Event>();

	@Override
	public void addEvent(IElement e, long deltaNext) {
		events.add(new Event(e, deltaNext + this.current_time));
	}
	
	/**
	 * Run time
	 */
	public void run(Net network) {
		this.current_time = System.currentTimeMillis();

		network.accept(this);
		System.out.println("Initialisation done. (" + events.size() + " events)");
		
		while(!events.isEmpty()) {
			Event e = events.pollFirst();
			this.current_time = e.date();
			e.getElement().update(this);
		}
	}
}
