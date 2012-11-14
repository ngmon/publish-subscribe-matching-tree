package cz.muni.fi.publishsubscribe.matchingtree;

public class Predicate<TEST_TYPE extends Comparable, RESULT_TYPE extends Comparable> {

	private PredicateTest<TEST_TYPE> test;
	private TestResult<RESULT_TYPE> result;

	public Predicate(PredicateTest<TEST_TYPE> test,
			TestResult<RESULT_TYPE> result) {
		this.test = test;
		this.result = result;
	}

	public PredicateTest<TEST_TYPE> getTest() {
		return test;
	}

	public TestResult<RESULT_TYPE> getResult() {
		return result;
	}

	public <TEST_TYPE_2 extends Comparable, RESULT_TYPE_2 extends Comparable> boolean isCoveredBy(
			Predicate<TEST_TYPE_2, RESULT_TYPE_2> other) {
		return isCoveredBy(other.getTest(), other.getResult());
	}

	public <TEST_TYPE_2 extends Comparable, RESULT_TYPE_2 extends Comparable> boolean isCoveredBy(
			PredicateTest<TEST_TYPE_2> otherTest,
			TestResult<RESULT_TYPE_2> otherResult) {
		String thisAttrName = test.getAttributeName();
		String otherAttrName = otherTest.getAttributeName();
		if (!thisAttrName.equals(otherAttrName))
			return false;

		TestOperation thisOperation = test.getOperation();
		TestOperation otherOperation = otherTest.getOperation();

		TEST_TYPE testValue = test.getValue() == null ? null : test.getValue()
				.getValue();
		TEST_TYPE_2 otherTestValue = otherTest.getValue() == null ? null
				: otherTest.getValue().getValue();

		RESULT_TYPE resultValue = result.getValue();
		RESULT_TYPE_2 otherResultValue = otherResult.getValue();

		switch (thisOperation) {
		case EXAMINE: {
			switch (otherOperation) {
			// this shouldn't happen when preprocessing
			case EXAMINE:
				return result.equals(otherResult);
			case COMPARE: {
				// TODO - typecast
				ComparisonResult otherComparisonResultValue = (ComparisonResult) otherResultValue;
				switch (otherComparisonResultValue) {
				case GREATER:
					return resultValue.compareTo(otherTestValue) > 0;
				case GREATER_OR_EQUAL:
					return resultValue.compareTo(otherTestValue) >= 0;
				case SMALLER:
					return resultValue.compareTo(otherTestValue) < 0;
				case SMALLER_OR_EQUAL:
					return resultValue.compareTo(otherTestValue) <= 0;
				}
			}
			}
		}

		case COMPARE: {
			switch (otherOperation) {
			case EXAMINE:
				return false;
			case COMPARE: {

				// TODO - typecast
				ComparisonResult comparisonResultValue = (ComparisonResult) resultValue;
				ComparisonResult otherComparisonResultValue = (ComparisonResult) otherResultValue;

				switch (comparisonResultValue) {
				case GREATER: {
					switch (otherComparisonResultValue) {
					case GREATER:
						// equality (result == 0) shouldn't happen when
						// preprocessing
						return testValue.compareTo(otherTestValue) >= 0;
					case GREATER_OR_EQUAL:
						return testValue.compareTo(otherTestValue) >= 0;
					case SMALLER:
					case SMALLER_OR_EQUAL:
						return false;
					}
				}
				case GREATER_OR_EQUAL: {
					switch (otherComparisonResultValue) {
					case GREATER:
						return testValue.compareTo(otherTestValue) > 0;
					case GREATER_OR_EQUAL:
						// equality (result == 0) shouldn't happen when
						// preprocessing
						return testValue.compareTo(otherTestValue) >= 0;
					case SMALLER:
					case SMALLER_OR_EQUAL:
						return false;
					}
				}
				case SMALLER: {
					switch (otherComparisonResultValue) {
					case GREATER:
					case GREATER_OR_EQUAL:
						return false;
					case SMALLER:
						// equality (result == 0) shouldn't happen when
						// preprocessing
						return testValue.compareTo(otherTestValue) <= 0;
					case SMALLER_OR_EQUAL:
						return testValue.compareTo(otherTestValue) <= 0;
					}
				}
				case SMALLER_OR_EQUAL:
					switch (otherComparisonResultValue) {
					case GREATER:
					case GREATER_OR_EQUAL:
						return false;
					case SMALLER:
						return testValue.compareTo(otherTestValue) < 0;
					case SMALLER_OR_EQUAL:
						// equality (result == 0) shouldn't happen when
						// preprocessing
						return testValue.compareTo(otherTestValue) <= 0;
					}
				}
			}
			}
		}
		}

		return false;
	}

}
