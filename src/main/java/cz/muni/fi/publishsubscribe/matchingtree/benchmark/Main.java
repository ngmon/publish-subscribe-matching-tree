package cz.muni.fi.publishsubscribe.matchingtree.benchmark;

public class Main {

	public static void main(String[] args) {
		//com.google.caliper.Runner.main(new String[] { "cz.muni.fi.publishsubscribe.countingtree.benchmark.TestBenchmark" });
		
		//com.google.caliper.Runner.main(new String[] { "cz.muni.fi.publishsubscribe.matchingtree.benchmark.EveryEventLessThanOrEqual" });
		//com.google.caliper.Runner.main(new String[] { "cz.muni.fi.publishsubscribe.matchingtree.benchmark.OneAttributeOperatorsLessThanAndGreaterThanOrEqual" });
		com.google.caliper.Runner.main(new String[] { "cz.muni.fi.publishsubscribe.matchingtree.benchmark.TwelveLongAttributesLessThan" });
		
		//com.google.caliper.Runner.main(new String[] { "cz.muni.fi.publishsubscribe.matchingtree.benchmark.Subscribe" });
	}

}
