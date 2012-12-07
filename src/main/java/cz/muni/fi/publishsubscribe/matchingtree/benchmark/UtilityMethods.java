package cz.muni.fi.publishsubscribe.matchingtree.benchmark;

import cz.muni.fi.publishsubscribe.matchingtree.adapter.Constraint;
import cz.muni.fi.publishsubscribe.matchingtree.adapter.Filter;
import cz.muni.fi.publishsubscribe.matchingtree.adapter.Predicate;

public class UtilityMethods {

	public static Predicate createPredicateFromConstraint(
			Constraint<?> constraint) {
		Filter filter = new Filter();
		filter.addConstraint(constraint);
		Predicate predicate = new Predicate();
		predicate.addFilter(filter);

		return predicate;
	}

}
