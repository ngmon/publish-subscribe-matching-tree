package cz.muni.fi.publishsubscribe.matchingtree;

import java.util.HashMap;
import java.util.Map;

public class Node {

	private boolean isLeaf = false;

	private Test<Comparable<?>> test;
	private Map<String, Node> resultNodes = new HashMap<String, Node>();
	private Node starNode;

	private Subscription subscription;

	public Test<Comparable<?>> getTest() {
		return test;
	}

	public void setTest(Test<Comparable<?>> test) {
		this.test = test;
		this.isLeaf = false;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public Map<String, Node> getResultNodes() {
		return resultNodes;
	}

	public Node getResultNode(String result) {
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

	public void addResultNode(String result, Node node) {
		resultNodes.put(result, node);
	}

}
