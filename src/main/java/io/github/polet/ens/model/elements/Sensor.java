package io.github.polet.ens.model.elements;

import io.github.polet.ens.generator.Visitor;
import io.github.polet.ens.model.IElement;
import io.github.polet.ens.model.ILaw;
import io.github.polet.ens.model.NamedElement;

public class Sensor implements IElement, NamedElement{
		
	private IElement parent = null;
	private String name;
	private ILaw law;
	
	private String data;
	
	public Sensor() {}

	public void setLaw(ILaw l) {
		this.law = l;
	}
	
	@Override
	public boolean isActive() {
		return parent == null || parent.isActive();
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void init(Visitor visitor) {
		System.out.println("Initialise sensor : " + this.name);
		if(law == null) {
			System.out.println("WARNING : sensor " + this.name + " has no law.");
		}else {
			visitor.addEvent(this, law.deltaNext());
		}
	}

	@Override
	public void update(@SuppressWarnings("rawtypes") Visitor visitor) {
		//update the sensor data
		this.data = law.nextData();
		
		//allow reading
		if(isActive()) {
			visitor.visit(this);
		}
		
		//call for next time
		if(law.hasNext())
			visitor.addEvent(this, law.deltaNext());
	}
	
	public String getData() {
		return data;
	}
}
