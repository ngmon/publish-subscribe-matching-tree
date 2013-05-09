package cz.muni.fi.publishsubscribe.matchingtree;

public class PredicateTest<T1 extends Comparable> {

	private String attributeName;
	private AttributeValue<T1> value;
	private TestOperation operation;

	public PredicateTest(String attributeName, AttributeValue<T1> value,
			TestOperation operation) {
		this.attributeName = attributeName;
		this.value = value;
		this.operation = operation;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public AttributeValue<T1> getValue() {
		return value;
	}

	public TestOperation getOperation() {
		return operation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attributeName == null) ? 0 : attributeName.hashCode());
		result = prime * result
				+ ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PredicateTest<?> other = (PredicateTest<?>) obj;
		if (attributeName == null) {
			if (other.attributeName != null)
				return false;
		} else if (!attributeName.equals(other.attributeName))
			return false;
		if (operation != other.operation)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
