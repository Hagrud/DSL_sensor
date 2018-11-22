package io.github.polet.ens.model;

public class Event implements Comparable<Event>{
	
	private final long date;
	private final IElement elem;
	
	public Event(IElement e, long d) {
		this.date = d;
		this.elem = e;
	}
	
	public IElement getElement() {
		return elem;
	}
	
	public long date() {
		return date;
	}

	@Override
	public int compareTo(Event o) {
		return Long.compare(date, o.date);
	}

}
