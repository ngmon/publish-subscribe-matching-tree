package cz.muni.fi.publishsubscribe.matchingtree;

import java.util.ArrayList;
import java.util.List;

public class Subscription {

	private List<Predicate<Comparable<?>>> predicates = new ArrayList<>();

	public void addPredicate(Predicate<Comparable<?>> predicate) {
		this.predicates.add(predicate);
	}

	public List<Predicate<Comparable<?>>> getPredicates() {
		return predicates;
	}

}
