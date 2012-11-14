package cz.muni.fi.publishsubscribe.matchingtree;

public class TestResult<T1 extends Comparable<?>> {

	private final T1 value;
	private final Class<T1> type;

	public TestResult(T1 value, Class<T1> type) {
		this.value = value;
		this.type = type;
	}

	public T1 getValue() {
		return value;
	}

	public Class<T1> getType() {
		return type;
	}

}
