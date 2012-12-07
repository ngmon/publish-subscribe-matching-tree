package cz.muni.fi.publishsubscribe.matchingtree.adapter;

public class PredicateAdapter {

	private FilterAdapter filter;

	public void addFilter(FilterAdapter filter) {
		this.filter = filter;
	}

	public FilterAdapter getFilter() {
		return filter;
	}

}
