package cz.muni.fi.publishsubscribe.matchingtree;

import java.util.HashMap;
import java.util.Map;

public class Node {

	private static Long nextId = 0L;
	private Long id;
	private boolean isLeaf = false;

	private PredicateTest<Comparable<?>> test;
	private Map<TestResult<Comparable<?>>, Node> resultNodes = new HashMap<TestResult<Comparable<?>>, Node>();
	private Node starNode;

	private Subscription subscription;

	public Node() {
		this.id = nextId++;
	}

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

	public void putResultNode(TestResult<Comparable<?>> result, Node node) {
		resultNodes.put(result, node);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
