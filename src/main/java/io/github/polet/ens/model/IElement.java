package io.github.polet.ens.model;

import io.github.polet.ens.generator.Visitor;

public interface IElement {

	public void init(Visitor visitor);
	public void update(Visitor visitor); 
	public boolean isActive();
	
}
