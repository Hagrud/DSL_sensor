package io.github.polet.ens.model;

import java.util.ArrayList;
import java.util.List;

import io.github.polet.ens.generator.Visitable;
import io.github.polet.ens.generator.Visitor;

public class Net implements Visitable, NamedElement {
	
	private String name;
	List<IElement> elements = new ArrayList<IElement>();
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	public void addElement(IElement e) {
		elements.add(e);
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public List<IElement> getElements() {
		return elements;
	}
}
