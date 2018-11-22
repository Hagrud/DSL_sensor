package io.github.polet.ens.generator;

import io.github.polet.ens.model.*;
import io.github.polet.ens.model.elements.Sensor;

public abstract class Visitor<T> {

	public abstract void visit(Net net);
	public abstract void visit(Sensor net);
	
	public abstract void addEvent(IElement sensor, long deltaNext);

}

