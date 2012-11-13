package cz.muni.fi.publishsubscribe.matchingtree.equality;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * A matching tree - composed of Nodes, subscriptions are in leaves
 */
public class MatchingTree {

	private Node root = new Node(0);

	/**
	 * Adds one subscription to the tree leaf
	 * @param subscription A subscription POJO, attributes might be null,
	 *     meaning we don't care about values which the event attribute might have
	 */
	public void preprocess(Subscription subscription) {

		// traversing through nodes, creating new nodes if necessary
		Node node = root;
		for (int i = 0; i < Constants.EVENT_ATTRIBUTE_COUNT; i++) {
			Comparable<?> attributeValue = subscription.getAttributeValue(i);
			node = node.getChildCreateIfNeeded(attributeValue);
		}

		// assigning subscription to the leaf
		node.addSubscription(subscription);
	}

	/**
	 * Matches an event against all subscriptions in the tree
	 * @param event An event POJO, all attributes must be filled in (no nulls)
	 * @return List of Subscriptions which match the event
	 */
	public List<Subscription> match(Event event) {
		List<Subscription> matchedSubscriptions = new ArrayList<Subscription>();

		// depth-first search
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);

		while (!stack.empty()) {
			Node currentNode = stack.pop();
			// bottom of the tree -> add the subscriptions
			if (currentNode.isLeaf()) {
				matchedSubscriptions.addAll(currentNode.getSubscriptions());
			} else {
				// I assume each event attribute always have a value
				Comparable<?> eventAttributeValue = event
						.getAttributeValue(currentNode.getAttributeIndex());
				// child node for the exact attribute value
				Node childNode = currentNode.getChild(eventAttributeValue);
				if (childNode != null)
					stack.push(childNode);
				// star node will always be traversed (if it's there)
				Node starNode = currentNode.getStarNode();
				if (starNode != null)
					stack.push(starNode);
			}
		}

		return matchedSubscriptions;
	}

}
