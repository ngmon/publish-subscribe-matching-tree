package cz.muni.fi.publishsubscribe.matchingtree;

import java.util.List;

public class Main {

	public static void printSubscriptions(List<Subscription> subscriptions) {
		for (Subscription subscription : subscriptions) {
			System.out.println(subscription.toString());
			System.out.println("Application: " + subscription.getApplication());
			System.out.println("Process ID: " + subscription.getProcessId());
		}
	}

	public static void printEvent(Event event) {
		System.out.println(event.toString());
		System.out.println("ID: " + event.getId());
		System.out.println("Application: " + event.getApplication());
		System.out.println("Process ID: " + event.getProcessId());
	}

	public static void printEventAndSubscriptions(Event event,
			List<Subscription> subscriptions) {
		printEvent(event);
		printSubscriptions(subscriptions);
	}

	public static void matchTreeAndPrintResult(MatchingTree tree, Event event) {
		List<Subscription> matchedSubscriptions = tree.match(event);
		System.out.println("-------");
		System.out.println("Result:");
		System.out.println("-------");
		printEventAndSubscriptions(event, matchedSubscriptions);
	}

	public static void main(String[] args) {
		MatchingTree tree = new MatchingTree();
		
		Subscription subscription = new Subscription();
		subscription.setApplication("Apache Server");
		subscription.setProcessId(4219L);
		tree.preprocess(subscription);
		
		subscription = new Subscription();
		subscription.setApplication("PostgreSQL");
		tree.preprocess(subscription);
		
		subscription = new Subscription();
		tree.preprocess(subscription);

		Long id = 0L;
		Event event = new Event(id++, "Apache Server", 4219L, 1);
		matchTreeAndPrintResult(tree, event);
		event = new Event(id++, "PostgreSQL", 5000L, 1);
		matchTreeAndPrintResult(tree, event);
	}

}
