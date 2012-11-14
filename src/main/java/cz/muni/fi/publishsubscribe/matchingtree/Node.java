package cz.muni.fi.publishsubscribe.matchingtree;

import java.util.HashMap;
import java.util.Map;

public class Node {

	private boolean isLeaf = false;

	private PredicateTest<Comparable<?>> test;
	private Map<TestResult<Comparable<?>>, Node> resultNodes = new HashMap<TestResult<Comparable<?>>, Node>();
	private Node starNode;

	private Subscription subscription;

	public PredicateTest<Comparable<?>> getTest() {
		return test;
	}

	public void setTest(PredicateTest<Comparable<?>> test) {
		this.test = test;
		this.isLeaf = false;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public Map<TestResult<Comparable<?>>, Node> getResultNodes() {
		return resultNodes;
	}

	public Node getResultNode(TestResult<Comparable<?>> result) {
		return resultNodes.get(result);
	}

	public Node getStarNode() {
		return starNode;
	}

	public void setStarNode(Node starNode) {
		this.starNode = starNode;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
		this.isLeaf = true;
	}

	public void addResultNode(TestResult<Comparable<?>> result, Node node) {
		resultNodes.put(result, node);
	}

}
