package cz.muni.fi.publishsubscribe.matchingtree;

import java.util.List;

public class MatchingTree {

	private Node root = null;

	public boolean preprocess(Subscription subscription) {
		List<Predicate<Comparable<?>, Comparable<?>>> predicates = subscription
				.getPredicates();
		if (predicates.isEmpty())
			return false;
		int predicatesCount = predicates.size();

		boolean found = true;
		if (root == null) {
			root = new Node();
			root.setTest(predicates.get(0).getTest());
			found = false;
		}

		Node currentNode = root;
		int i = 0;

		while (found && i < predicatesCount) {
			Predicate<Comparable<?>, Comparable<?>> currentPredicate = predicates
					.get(i);
			Test<Comparable<?>> currentTest = currentPredicate.getTest();
			TestResult<Comparable<?>> currentResult = currentPredicate
					.getResult();

			if (currentNode.isLeaf()) {
				// TODO
			} else if (currentNode.getTest().equals(currentTest)) {
				Node resultNode = currentNode.getResultNode(currentResult);
				if (resultNode == null)
					found = false;
				else {
					currentNode = resultNode;
					i++;
				}
			} else {
				for (TestResult<Comparable<?>> result : currentNode
						.getResultNodes().keySet()) {
					if (currentPredicate.isCoveredBy(currentNode.getTest(),
							result))
						currentNode = currentNode.getResultNode(result);
					else if (currentNode.getStarNode() != null)
						currentNode = currentNode.getStarNode();
					else {
						Node node = new Node();
						node.setTest(currentTest);
						currentNode.setStarNode(node);
						currentNode = node;
						found = false;
					}
				}
			}
		}

		if (!found) {
			while (i < predicatesCount) {
				Node node = new Node();
				if (i == predicatesCount)
					node.setSubscription(subscription);
				else
					node.setTest(predicates.get(i + 1).getTest());
				currentNode.addResultNode(predicates.get(i).getResult(), node);
				i++;
			}
		} else {
			if (!currentNode.isLeaf()) {
				while (currentNode.getStarNode() != null)
					currentNode = currentNode.getStarNode();
				if (!currentNode.isLeaf()) {
					Node node = new Node();
					node.setSubscription(subscription);
					currentNode.setStarNode(node);
				}
			}
		}

		return true;
	}

	public List<Subscription> match(Event event) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
