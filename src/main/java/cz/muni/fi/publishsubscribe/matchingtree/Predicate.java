package cz.muni.fi.publishsubscribe.matchingtree;

public class Predicate<T1 extends Comparable<?>> {

	private Test<T1> test;
	private String result;

	public Predicate(Test<T1> test, String result) {
		this.test = test;
		this.result = result;
	}

	public Test<T1> getTest() {
		return test;
	}

	public String getResult() {
		return result;
	}

}
