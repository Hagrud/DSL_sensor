package io.github.polet.ens.dsl;

import io.github.polet.ens.model.elements.Sensor;
import io.github.polet.ens.model.laws.CSVLaw;

public class SensorBuilder {
	
	final NetBuilder parent;
	final Sensor sensor;

	public SensorBuilder(NetBuilder netBuilder, Sensor s) {
		parent = netBuilder;
		sensor = s;
	}
	
	public NetBuilder followCSV(String file) {
		sensor.setLaw(new CSVLaw(file));
		return parent;
	}

}
