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

		Node lastNode = null;
		TestResult<Comparable<?>> lastResult = null;

		while (found && i < predicatesCount) {
			Predicate<Comparable<?>, Comparable<?>> currentPredicate = predicates
					.get(i);
			PredicateTest<Comparable<?>> currentTest = currentPredicate
					.getTest();
			TestResult<Comparable<?>> currentResult = currentPredicate
					.getResult();

			if (currentNode.isLeaf()) {
				Node node = new Node();
				node.setTest(currentTest);
				node.setStarNode(currentNode);
				lastNode.putResultNode(lastResult, node);
				currentNode = node;
				found = false;
			} else if (currentNode.getTest().equals(currentTest)) {
				Node resultNode = currentNode.getResultNode(currentResult);
				if (resultNode == null)
					found = false;
				else {
					lastNode = currentNode;
					lastResult = currentResult;
					currentNode = resultNode;
					i++;
				}
			} else {
				for (TestResult<Comparable<?>> result : currentNode
						.getResultNodes().keySet()) {
					if (currentPredicate.isCoveredBy(currentNode.getTest(),
							result)) {
						lastNode = currentNode;
						lastResult = result;
						currentNode = currentNode.getResultNode(result);
					} else if (currentNode.getStarNode() != null) {
						lastNode = currentNode;
						lastResult = null;  // meaning star node
						currentNode = currentNode.getStarNode();
					}
					else {
						Node node = new Node();
						node.setTest(currentTest);
						currentNode.setStarNode(node);
						lastNode = currentNode;
						lastResult = null;
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
				currentNode.putResultNode(predicates.get(i).getResult(), node);
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
