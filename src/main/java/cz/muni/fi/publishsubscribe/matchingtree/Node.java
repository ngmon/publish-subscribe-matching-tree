package cz.muni.fi.publishsubscribe.matchingtree;

import java.util.HashMap;
import java.util.Map;

public class Node {

	private boolean isLeaf;

	private Test<Comparable<?>> test;
	private Map<String, Node> resultEdges = new HashMap<String, Node>();
	private Node starNode;

	private Subscription subscription;

}
