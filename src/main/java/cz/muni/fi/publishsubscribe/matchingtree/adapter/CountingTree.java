package cz.muni.fi.publishsubscribe.matchingtree.adapter;

import java.util.List;

import cz.muni.fi.publishsubscribe.matchingtree.MatchingTree;
import cz.muni.fi.publishsubscribe.matchingtree.Subscription;

public class CountingTree {

	private MatchingTree matchingTree = new MatchingTree();

	public void subscribe(Predicate predicate) {
		// create matching tree subscription
		Filter filterAdapter = predicate.getFilter();
		List<Constraint<? extends Comparable<?>>> constraints = filterAdapter
				.getConstraints();
		Subscription subscription = new Subscription();
		for (Constraint<? extends Comparable<?>> constraint : constraints) {
			subscription.addPredicate(constraint.getPredicate());
		}

		matchingTree.preprocess(subscription);
	}

	public void match(Event event) {
		matchingTree.match(event);
	}

}
