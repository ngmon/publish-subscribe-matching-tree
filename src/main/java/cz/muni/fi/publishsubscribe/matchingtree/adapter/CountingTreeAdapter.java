package cz.muni.fi.publishsubscribe.matchingtree.adapter;

import java.util.List;

import cz.muni.fi.publishsubscribe.matchingtree.MatchingTree;
import cz.muni.fi.publishsubscribe.matchingtree.Subscription;

public class CountingTreeAdapter {

	private MatchingTree matchingTree = new MatchingTree();

	public void subscribe(PredicateAdapter predicate) {
		// create matching tree subscription
		FilterAdapter filterAdapter = predicate.getFilter();
		List<ConstraintAdapter<? extends Comparable<?>>> constraints = filterAdapter
				.getConstraints();
		Subscription subscription = new Subscription();
		for (ConstraintAdapter<? extends Comparable<?>> constraint : constraints) {
			subscription.addPredicate(constraint.getPredicate());
		}

		matchingTree.preprocess(subscription);
	}

	public void match(EventAdapter event) {
		matchingTree.match(event);
	}

}
