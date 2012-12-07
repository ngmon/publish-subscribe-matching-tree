package cz.muni.fi.publishsubscribe.matchingtree.adapter;

import java.util.ArrayList;
import java.util.List;

public class Filter {

	private List<Constraint<? extends Comparable<?>>> constraints = new ArrayList<>();

	public <T1 extends Comparable<T1>, T2 extends Constraint<T1>> boolean addConstraint(
			T2 constraint) {
		return this.constraints.add(constraint);
	}

	public List<Constraint<? extends Comparable<?>>> getConstraints() {
		return constraints;
	}

}
