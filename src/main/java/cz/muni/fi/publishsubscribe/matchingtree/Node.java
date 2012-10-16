package cz.muni.fi.publishsubscribe.matchingtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A matching tree node (if it's a leaf, it can contain associated subscriptions)
 */
public class Node {

	/** Event attribute index */
	private int attributeIndex;
	/** Child nodes map */
	private HashMap<Object, Node> childrenMap;
	private Node starNode;
	/** Subscriptions (if this node is a leaf) */
	private List<Subscription> subscriptions;

	public Node(int attributeIndex) {
		this.attributeIndex = attributeIndex;
		// not needed for leaves, but eliminates the need for testing
		// whether childrenMap == null in getChild...() methods
		this.childrenMap = new HashMap<Object, Node>();
	}

	public Node getChild(Object attributeValue) {
		if (attributeValue == null)
			return starNode;

		Node child = childrenMap.get(attributeValue);
		return child;
	}

	public Node getChildCreateIfNeeded(Comparable<?> attributeValue) {
		if (attributeValue == null) {
			if (starNode == null)
				starNode = new Node(attributeIndex + 1);
			return starNode;
		}

		Node child = childrenMap.get(attributeValue);
		if (child == null) {
			child = new Node(attributeIndex + 1);
			childrenMap.put(attributeValue, child);
		}

		return child;
	}

	public void addSubscription(Subscription subscription) {
		if (subscriptions == null)
			subscriptions = new ArrayList<Subscription>();
		subscriptions.add(subscription);
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public boolean isLeaf() {
		return subscriptions != null;
	}

	public int getAttributeIndex() {
		return attributeIndex;
	}

	public Node getStarNode() {
		return starNode;
	}

}
