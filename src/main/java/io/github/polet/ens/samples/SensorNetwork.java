package io.github.polet.ens.samples;

import io.github.polet.ens.dsl.NetBuilder;

public class SensorNetwork {

	public static void main(String[] args) {
		NetBuilder.network("net")
			.sensor("test").followCSV("/home/pep/Desktop/ens/CR06/DSL_sensor/test/test.csv")
			.run("http://localhost:8086/write?db=sec");
	}

}
