package cz.muni.fi.publishsubscribe.matchingtree.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import cz.muni.fi.publishsubscribe.matchingtree.AttributeValue;
import cz.muni.fi.publishsubscribe.matchingtree.ComparisonResult;
import cz.muni.fi.publishsubscribe.matchingtree.Predicate;
import cz.muni.fi.publishsubscribe.matchingtree.PredicateTest;
import cz.muni.fi.publishsubscribe.matchingtree.TestOperation;
import cz.muni.fi.publishsubscribe.matchingtree.TestResult;

public class IsCoveredByTestCase {

	private static final String TEMPERATURE = "temperature";
	private static final String PROCESS_ID = "processId";

	private Predicate<Long, ComparisonResult> processId1000CompareSmallerPredicate;
	private Predicate<Long, ComparisonResult> processId2000CompareSmallerPredicate;
	private Predicate<Long, ComparisonResult> temperature30CompareSmallerPredicate;

	private Predicate<Long, ComparisonResult> processId1000CompareGreaterPredicate;
	private Predicate<Long, ComparisonResult> processId1000CompareGreaterOrEqualPredicate;

	private Predicate<Long, Long> processIdEquals1000Predicate;
	private Predicate<Long, Long> temperatureEquals30Predicate;

	@Before
	public void preparePredicates() {

		PredicateTest<Long> processId1000Compare = new PredicateTest<>(
				PROCESS_ID, new AttributeValue<Long>(1000L, Long.class),
				TestOperation.COMPARE);
		PredicateTest<Long> temperature30Compare = new PredicateTest<>(
				TEMPERATURE, new AttributeValue<Long>(30L, Long.class),
				TestOperation.COMPARE);

		processId1000CompareSmallerPredicate = new Predicate<>(
				processId1000Compare, new TestResult<ComparisonResult>(
						ComparisonResult.SMALLER, ComparisonResult.class));
		temperature30CompareSmallerPredicate = new Predicate<>(
				temperature30Compare, new TestResult<ComparisonResult>(
						ComparisonResult.SMALLER, ComparisonResult.class));

		PredicateTest<Long> processIdEquals1000 = new PredicateTest<>(
				PROCESS_ID, null, TestOperation.EXAMINE);
		PredicateTest<Long> temperatureEquals30 = new PredicateTest<>(
				TEMPERATURE, null, TestOperation.EXAMINE);

		processIdEquals1000Predicate = new Predicate<>(processIdEquals1000,
				new TestResult<Long>(1000L, Long.class));
		temperatureEquals30Predicate = new Predicate<>(temperatureEquals30,
				new TestResult<Long>(30L, Long.class));

		PredicateTest<Long> processId2000Compare = new PredicateTest<>(
				PROCESS_ID, new AttributeValue<Long>(2000L, Long.class),
				TestOperation.COMPARE);
		processId2000CompareSmallerPredicate = new Predicate<>(
				processId2000Compare, new TestResult<ComparisonResult>(
						ComparisonResult.SMALLER, ComparisonResult.class));

		processId1000CompareGreaterPredicate = new Predicate<>(
				processId1000Compare, new TestResult<ComparisonResult>(
						ComparisonResult.GREATER, ComparisonResult.class));
		processId1000CompareGreaterOrEqualPredicate = new Predicate<>(
				processId1000Compare, new TestResult<ComparisonResult>(
						ComparisonResult.GREATER_OR_EQUAL,
						ComparisonResult.class));

	}

	@Test
	public void testDifferentAttributes() {
		assertFalse(temperature30CompareSmallerPredicate
				.isCoveredBy(processId1000CompareSmallerPredicate));
		assertFalse(processId1000CompareSmallerPredicate
				.isCoveredBy(temperature30CompareSmallerPredicate));
	}

	@Test
	public void testExamineSameValue() {
		assertTrue(processIdEquals1000Predicate
				.isCoveredBy(processIdEquals1000Predicate));
		assertTrue(temperatureEquals30Predicate
				.isCoveredBy(temperatureEquals30Predicate));
	}

	@Test
	public void testCompareTwoPredicatesWithSmaller() {
		assertTrue(processId1000CompareSmallerPredicate
				.isCoveredBy(processId2000CompareSmallerPredicate));
		assertFalse(processId2000CompareSmallerPredicate
				.isCoveredBy(processId1000CompareSmallerPredicate));
	}

	@Test
	public void testCompareExamineAndCompareSmaller() {
		assertTrue(processIdEquals1000Predicate
				.isCoveredBy(processId2000CompareSmallerPredicate));
		assertFalse(processIdEquals1000Predicate
				.isCoveredBy(processId1000CompareSmallerPredicate));
		assertFalse(processId2000CompareSmallerPredicate
				.isCoveredBy(processIdEquals1000Predicate));
		assertFalse(processId1000CompareSmallerPredicate
				.isCoveredBy(processIdEquals1000Predicate));
	}

	@Test
	public void testCompareExamineAndCompareGreaterAndGreaterOrEqual() {
		assertFalse(processIdEquals1000Predicate
				.isCoveredBy(processId1000CompareGreaterPredicate));
		assertTrue(processIdEquals1000Predicate
				.isCoveredBy(processId1000CompareGreaterOrEqualPredicate));
	}

	@Test
	public void testCompareSmallerAndGreater() {
		assertFalse(processId1000CompareGreaterPredicate
				.isCoveredBy(processId2000CompareSmallerPredicate));
		assertFalse(processId2000CompareSmallerPredicate
				.isCoveredBy(processId1000CompareGreaterPredicate));
	}

	@Test
	public void testCompareGreater() {
		assertTrue(processId1000CompareGreaterPredicate
				.isCoveredBy(processId1000CompareGreaterOrEqualPredicate));
		assertFalse(processId1000CompareGreaterOrEqualPredicate
				.isCoveredBy(processId1000CompareGreaterPredicate));
	}
}
