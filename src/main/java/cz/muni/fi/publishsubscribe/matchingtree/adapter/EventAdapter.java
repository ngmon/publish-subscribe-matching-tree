package cz.muni.fi.publishsubscribe.matchingtree.adapter;

import cz.muni.fi.publishsubscribe.matchingtree.Attribute;
import cz.muni.fi.publishsubscribe.matchingtree.Event;

public class EventAdapter extends Event {

	/*-private Map<String, Attribute<? extends Comparable<?>>> attributes = new HashMap<>();

	public void addAttribute(Attribute<? extends Comparable<?>> attribute) {
		this.attributes.put(attribute.getName(), attribute);
	}

	public Map<String, Attribute<? extends Comparable<?>>> getAttributes() {
		return attributes;
	}

	public Attribute<? extends Comparable<?>> getAttributeByName(
			String attributeName) {
		return this.attributes.get(attributeName);
	}*/
	
	public void addAttribute(Attribute<? extends Comparable<?>> attribute) {
		super.putAttribute(attribute);
	}
	
}
