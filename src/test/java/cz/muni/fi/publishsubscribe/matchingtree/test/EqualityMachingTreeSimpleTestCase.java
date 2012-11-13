package cz.muni.fi.publishsubscribe.matchingtree.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import cz.muni.fi.publishsubscribe.matchingtree.equality.Event;
import cz.muni.fi.publishsubscribe.matchingtree.equality.MatchingTree;
import cz.muni.fi.publishsubscribe.matchingtree.equality.Subscription;

public class EqualityMachingTreeSimpleTestCase {

	@Test
	public void testEmptyTree() {
		MatchingTree emptyTree = new MatchingTree();
		Event event = new Event(0L, "Apache", 1000L, 1);
		List<Subscription> subscriptions = emptyTree.match(event);
		assertTrue(subscriptions.isEmpty());
	}

	@Test
	public void testEmptySubscription() {
		MatchingTree tree = new MatchingTree();
		Subscription subscription = new Subscription();
		tree.preprocess(subscription);
		Event event = new Event(0L, "Apache", 1000L, 1);
		List<Subscription> subscriptions = tree.match(event);
		assertEquals(1, subscriptions.size());
	}

	@Test
	public void noMatchingSubscriptionInNonEmptyTree() {
		MatchingTree tree = new MatchingTree();

		// Apache, 1000
		Subscription apache1000Subscription = new Subscription();
		apache1000Subscription.setApplication("Apache");
		apache1000Subscription.setProcessId(1000L);
		tree.preprocess(apache1000Subscription);

		// 2000
		Subscription processId2000Subscription = new Subscription();
		processId2000Subscription.setProcessId(2000L);
		tree.preprocess(processId2000Subscription);
		
		Event event = new Event(0L, "PostgreSQL", 3000L, 1);
		List<Subscription> subscriptions = tree.match(event);
		assertTrue(subscriptions.isEmpty());
	}
	
	@Test
	public void testThreeAttributes() {
		MatchingTree tree = new MatchingTree();
		
		// Apache, 1000, 10
		Subscription apache1000_10Subscription = new Subscription();
		apache1000_10Subscription.setApplication("Apache");
		apache1000_10Subscription.setProcessId(1000L);
		apache1000_10Subscription.setSeverity(10);
		tree.preprocess(apache1000_10Subscription);
		
		// Apache, ?, 10
		Subscription apache10Subscription = new Subscription();
		apache10Subscription.setApplication("Apache");
		apache10Subscription.setSeverity(10);
		tree.preprocess(apache10Subscription);
		
		Event event = new Event(0L, "Apache", 1000L, 10);
		List<Subscription> subscriptions = tree.match(event);
		assertEquals(2, subscriptions.size());
		assertTrue(subscriptions.contains(apache1000_10Subscription));
		assertTrue(subscriptions.contains(apache10Subscription));
		
		event = new Event(0L, "Apache", 0L, 10);
		subscriptions = tree.match(event);
		assertEquals(1, subscriptions.size());
		assertTrue(subscriptions.contains(apache10Subscription));
	}
}
