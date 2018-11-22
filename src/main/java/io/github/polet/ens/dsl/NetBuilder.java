package io.github.polet.ens.dsl;

import io.github.polet.ens.generator.ToC;
import io.github.polet.ens.model.Net;
import io.github.polet.ens.model.elements.Sensor;

public class NetBuilder {
	
	Net network;
	
	public static NetBuilder network(String name) {
		System.out.println("create net : " + name);
		NetBuilder inst = new NetBuilder();
		inst.network = new Net();
		inst.network.setName(name);
		return inst;
	}
	
	public SensorBuilder sensor(String name) {
		System.out.println("create sensor : " + name);
		Sensor s = new Sensor();
		s.setName(name);
		this.network.addElement(s);
		return new SensorBuilder(this, s);
	}
	
	private NetBuilder() {}

	public void run(String database_name) {
		ToC t = new ToC();
		t.setDataBase(database_name);
		t.run(network);
	}

}
