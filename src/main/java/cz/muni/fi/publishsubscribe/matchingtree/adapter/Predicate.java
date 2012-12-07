package cz.muni.fi.publishsubscribe.matchingtree.adapter;

public class Predicate {

	private Filter filter;

	public void addFilter(Filter filter) {
		this.filter = filter;
	}

	public Filter getFilter() {
		return filter;
	}

}
