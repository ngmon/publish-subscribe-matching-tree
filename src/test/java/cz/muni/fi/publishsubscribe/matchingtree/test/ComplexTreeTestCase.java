package cz.muni.fi.publishsubscribe.matchingtree.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cz.muni.fi.publishsubscribe.matchingtree.Attribute;
import cz.muni.fi.publishsubscribe.matchingtree.AttributeValue;
import cz.muni.fi.publishsubscribe.matchingtree.ComparisonResult;
import cz.muni.fi.publishsubscribe.matchingtree.Event;
import cz.muni.fi.publishsubscribe.matchingtree.MatchingTree;
import cz.muni.fi.publishsubscribe.matchingtree.Predicate;
import cz.muni.fi.publishsubscribe.matchingtree.PredicateTest;
import cz.muni.fi.publishsubscribe.matchingtree.Subscription;
import cz.muni.fi.publishsubscribe.matchingtree.TestOperation;
import cz.muni.fi.publishsubscribe.matchingtree.TestResult;

public class ComplexTreeTestCase {

	private static final String PROCESS_ID_ATTR = "processId";
	private static final String APPLICATION_ATTR = "application";

	private static final String APACHE_SERVER = "Apache Server";
	private static final String POSTGRE_SQL = "PostgreSQL";

	private MatchingTree tree;

	private Subscription subscription01;
	private Subscription subscription02;
	private Subscription subscription03;
	private Subscription subscription04;
	private Subscription subscription05;

	private Predicate apachePredicate;
	private Predicate pIdLessThan1000Predicate;

	private Predicate postgreSqlPredicate;
	private Predicate pIdGreaterOrEqualTo1000Predicate;

	private Predicate pIdGreaterThan2000Predicate;
	private Predicate pIdGreaterOrEqualTo2000Predicate;

	@Before
	public void prepareTree() {
		tree = new MatchingTree();

		// Apache, processId < 1000
		PredicateTest<String> examineApplicationAttrTest = new PredicateTest<>(
				APPLICATION_ATTR, null, TestOperation.EXAMINE);
		apachePredicate = new Predicate<>(examineApplicationAttrTest,
				new TestResult<String>(APACHE_SERVER, String.class));

		PredicateTest<Long> pIdCompareTo1000 = new PredicateTest<>(
				PROCESS_ID_ATTR, new AttributeValue<Long>(1000L, Long.class),
				TestOperation.COMPARE);
		pIdLessThan1000Predicate = new Predicate<>(pIdCompareTo1000,
				new TestResult<ComparisonResult>(ComparisonResult.SMALLER,
						ComparisonResult.class));

		subscription01 = new Subscription();
		subscription01.addPredicate(apachePredicate);
		subscription01.addPredicate(pIdLessThan1000Predicate);

		tree.preprocess(subscription01);

		// PostgreSQL, processId >= 1000
		postgreSqlPredicate = new Predicate<>(examineApplicationAttrTest,
				new TestResult<String>(POSTGRE_SQL, String.class));

		pIdGreaterOrEqualTo1000Predicate = new Predicate<>(pIdCompareTo1000,
				new TestResult<ComparisonResult>(
						ComparisonResult.GREATER_OR_EQUAL,
						ComparisonResult.class));

		subscription02 = new Subscription();
		subscription02.addPredicate(postgreSqlPredicate);
		subscription02.addPredicate(pIdGreaterOrEqualTo1000Predicate);

		tree.preprocess(subscription02);

		// processId > 2000
		PredicateTest<Long> pIdCompareTo2000 = new PredicateTest<>(
				PROCESS_ID_ATTR, new AttributeValue<Long>(2000L, Long.class),
				TestOperation.COMPARE);
		pIdGreaterThan2000Predicate = new Predicate<>(pIdCompareTo2000,
				new TestResult<ComparisonResult>(ComparisonResult.GREATER,
						ComparisonResult.class));

		subscription03 = new Subscription();
		subscription03.addPredicate(pIdGreaterThan2000Predicate);

		tree.preprocess(subscription03);

		// Apache
		subscription04 = new Subscription();
		subscription04.addPredicate(apachePredicate);
		tree.preprocess(subscription04);

		// PostgreSQL, processId >= 2000
		pIdGreaterOrEqualTo2000Predicate = new Predicate<>(pIdCompareTo2000,
				new TestResult<ComparisonResult>(
						ComparisonResult.GREATER_OR_EQUAL,
						ComparisonResult.class));

		subscription05 = new Subscription();
		subscription05.addPredicate(postgreSqlPredicate);
		subscription05.addPredicate(pIdGreaterOrEqualTo2000Predicate);

		tree.preprocess(subscription05);
	}

	@Test
	public void testNoMatchingSubscribers() {
		Event event = new Event();
		event.putAttribute(new Attribute<>(APPLICATION_ATTR,
				new AttributeValue<>("foo", String.class)));

		List<Subscription> subscriptions = tree.match(event);
		assertEquals(0, subscriptions.size());
	}

	@Test
	public void testApacheEvent() {
		Event event = new Event();
		event.putAttribute(new Attribute<>(APPLICATION_ATTR,
				new AttributeValue<>(APACHE_SERVER, String.class)));
		event.putAttribute(new Attribute<>(PROCESS_ID_ATTR,
				new AttributeValue<>(1234L, Long.class)));

		List<Subscription> subscriptions = tree.match(event);
		assertEquals(1, subscriptions.size());
		assertTrue(subscriptions.contains(subscription04));
	}
	
	@Test
	public void testApache1000Event() {
		Event event = new Event();
		event.putAttribute(new Attribute<>(APPLICATION_ATTR,
				new AttributeValue<>(APACHE_SERVER, String.class)));
		event.putAttribute(new Attribute<>(PROCESS_ID_ATTR,
				new AttributeValue<>(1000L, Long.class)));

		List<Subscription> subscriptions = tree.match(event);
		assertEquals(1, subscriptions.size());
		assertTrue(subscriptions.contains(subscription04));
	}
	
	@Test
	public void testApache999Event() {
		Event event = new Event();
		event.putAttribute(new Attribute<>(APPLICATION_ATTR,
				new AttributeValue<>(APACHE_SERVER, String.class)));
		event.putAttribute(new Attribute<>(PROCESS_ID_ATTR,
				new AttributeValue<>(999L, Long.class)));
		
		List<Subscription> subscriptions = tree.match(event);
		assertEquals(2, subscriptions.size());
		assertTrue(subscriptions.contains(subscription01));
		assertTrue(subscriptions.contains(subscription04));
	}
	
	@Test
	public void testFoo2000Event() {
		Event event = new Event();
		event.putAttribute(new Attribute<>(APPLICATION_ATTR,
				new AttributeValue<>("foo", String.class)));
		event.putAttribute(new Attribute<>(PROCESS_ID_ATTR,
				new AttributeValue<>(2000L, Long.class)));
		
		List<Subscription> subscriptions = tree.match(event);
		assertEquals(0, subscriptions.size());
	}
	
	@Test
	public void testFoo3000Event() {
		Event event = new Event();
		event.putAttribute(new Attribute<>(APPLICATION_ATTR,
				new AttributeValue<>("foo", String.class)));
		event.putAttribute(new Attribute<>(PROCESS_ID_ATTR,
				new AttributeValue<>(3000L, Long.class)));
		
		List<Subscription> subscriptions = tree.match(event);
		assertEquals(1, subscriptions.size());
		assertTrue(subscriptions.contains(subscription03));
	}
	
	@Test
	public void postgreSql3000Event() {
		Event event = new Event();
		event.putAttribute(new Attribute<>(APPLICATION_ATTR,
				new AttributeValue<>(POSTGRE_SQL, String.class)));
		event.putAttribute(new Attribute<>(PROCESS_ID_ATTR,
				new AttributeValue<>(3000L, Long.class)));

		List<Subscription> subscriptions = tree.match(event);
		assertEquals(3, subscriptions.size());
		assertTrue(subscriptions.contains(subscription02));
		assertTrue(subscriptions.contains(subscription03));
		assertTrue(subscriptions.contains(subscription05));
	}
	
	@Test
	public void postgreSql1000Event() {
		Event event = new Event();
		event.putAttribute(new Attribute<>(APPLICATION_ATTR,
				new AttributeValue<>(POSTGRE_SQL, String.class)));
		event.putAttribute(new Attribute<>(PROCESS_ID_ATTR,
				new AttributeValue<>(1000L, Long.class)));

		List<Subscription> subscriptions = tree.match(event);
		assertEquals(1, subscriptions.size());
		assertTrue(subscriptions.contains(subscription02));
	}
	
	@Test
	public void apache3000Event() {
		Event event = new Event();
		event.putAttribute(new Attribute<>(APPLICATION_ATTR,
				new AttributeValue<>(APACHE_SERVER, String.class)));
		event.putAttribute(new Attribute<>(PROCESS_ID_ATTR,
				new AttributeValue<>(3000L, Long.class)));

		List<Subscription> subscriptions = tree.match(event);
		assertEquals(2, subscriptions.size());
		assertTrue(subscriptions.contains(subscription03));
		assertTrue(subscriptions.contains(subscription04));
	}
}
