package cz.muni.fi.publishsubscribe.matchingtree.adapter;

import cz.muni.fi.publishsubscribe.matchingtree.AttributeValue;
import cz.muni.fi.publishsubscribe.matchingtree.ComparisonResult;
import cz.muni.fi.publishsubscribe.matchingtree.Predicate;
import cz.muni.fi.publishsubscribe.matchingtree.PredicateTest;
import cz.muni.fi.publishsubscribe.matchingtree.TestOperation;
import cz.muni.fi.publishsubscribe.matchingtree.TestResult;

public class Constraint<T1 extends Comparable<T1>> {

	private Predicate<? extends Comparable<?>, ? extends Comparable<?>> predicate = null;

	private ComparisonResult operatorToComparisonResult(Operator operator) {
		switch (operator) {
		case GREATER_THAN:
			return ComparisonResult.GREATER;
		case GREATER_THAN_OR_EQUAL_TO:
			return ComparisonResult.GREATER_OR_EQUAL;
		case LESS_THAN:
			return ComparisonResult.SMALLER;
		case LESS_THAN_OR_EQUAL_TO:
			return ComparisonResult.SMALLER_OR_EQUAL;
		default:
			throw new IllegalArgumentException(
					"illegal operator in operatorToComparisonResult");
		}
	}

	public Constraint(String attributeName,
			AttributeValue<T1> attributeValue, Operator operator) {
		PredicateTest<?> predicateTest = null;

		switch (operator) {

		case GREATER_THAN:
		case GREATER_THAN_OR_EQUAL_TO:
		case LESS_THAN:
		case LESS_THAN_OR_EQUAL_TO:
			predicateTest = new PredicateTest<>(attributeName, attributeValue,
					TestOperation.COMPARE);
			this.predicate = new Predicate<>(predicateTest,
					new TestResult<ComparisonResult>(
							operatorToComparisonResult(operator),
							ComparisonResult.class));
			break;

		case EQUALS:
			predicateTest = new PredicateTest<>(attributeName, null,
					TestOperation.EXAMINE);
			this.predicate = new Predicate<>(predicateTest,
					new TestResult<String>((String) attributeValue.getValue(),
							String.class));
			break;
		}
	}

	public Predicate<? extends Comparable<?>, ? extends Comparable<?>> getPredicate() {
		return predicate;
	}

}
