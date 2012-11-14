package cz.muni.fi.publishsubscribe.matchingtree;

public class Predicate<T1 extends Comparable<?>, T2 extends Comparable<?>> {

	private Test<T1> test;
	private TestResult<T2> result;

	public Predicate(Test<T1> test, TestResult<T2> result) {
		this.test = test;
		this.result = result;
	}

	public Test<T1> getTest() {
		return test;
	}

	public TestResult<T2> getResult() {
		return result;
	}

	public boolean isCoveredBy(Test<Comparable<?>> otherTest, TestResult<T2> otherResult) {
		String thisAttrName = test.getAttributeName();
		String otherAttrName = otherTest.getAttributeName();
		if (!thisAttrName.equals(otherAttrName))
			return false;

		TestOperation thisOperation = test.getOperation();
		TestOperation otherOperation = otherTest.getOperation();

		switch (thisOperation) {
		case EXAMINE: {
			switch (otherOperation) {
			// this shouldn't happen when preprocessing
			case EXAMINE:
				return result.equals(otherResult);
			case COMPARE:
				//return result.com
				return true;
			}
		}
		}
		
		return false;
	}

}
