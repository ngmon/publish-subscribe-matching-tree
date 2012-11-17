package cz.muni.fi.publishsubscribe.matchingtree;

import java.util.HashMap;
import java.util.Map;

public class Event {

	private Map<String, Attribute<? extends Comparable<?>>> attributes = new HashMap<>();

	public void putAttribute(Attribute<? extends Comparable<?>> attribute) {
		this.attributes.put(attribute.getName(), attribute);
	}

	public Map<String, Attribute<? extends Comparable<?>>> getAttributes() {
		return attributes;
	}

	public Attribute<? extends Comparable<?>> getAttributeByName(
			String attributeName) {
		return this.attributes.get(attributeName);
	}

}
