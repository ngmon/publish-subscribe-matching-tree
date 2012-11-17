package cz.muni.fi.publishsubscribe.matchingtree.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import cz.muni.fi.publishsubscribe.matchingtree.Attribute;
import cz.muni.fi.publishsubscribe.matchingtree.AttributeValue;
import cz.muni.fi.publishsubscribe.matchingtree.Event;
import cz.muni.fi.publishsubscribe.matchingtree.MatchingTree;
import cz.muni.fi.publishsubscribe.matchingtree.Subscription;

public class MatchingTreeSimpleTestCase {

	@Test
	public void testEmptyTree() {
		MatchingTree emptyTree = new MatchingTree();
		Event event = new Event();
		event.putAttribute(new Attribute<Long>("processId",
				new AttributeValue<Long>(1000L, Long.class)));
		event.putAttribute(new Attribute<String>("applicationName",
				new AttributeValue<String>("Apache Server", String.class)));

		List<Subscription> subscriptions = emptyTree.match(event);
		assertTrue(subscriptions.isEmpty());
	}
}
