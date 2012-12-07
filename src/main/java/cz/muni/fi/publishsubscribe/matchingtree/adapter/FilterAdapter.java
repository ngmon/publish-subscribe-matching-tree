package cz.muni.fi.publishsubscribe.matchingtree.adapter;

import java.util.ArrayList;
import java.util.List;

public class FilterAdapter {

	private List<ConstraintAdapter<? extends Comparable<?>>> constraints = new ArrayList<>();

	public <T1 extends Comparable<T1>, T2 extends ConstraintAdapter<T1>> boolean addConstraint(
			T2 constraint) {
		return this.constraints.add(constraint);
	}

	public List<ConstraintAdapter<? extends Comparable<?>>> getConstraints() {
		return constraints;
	}

}
