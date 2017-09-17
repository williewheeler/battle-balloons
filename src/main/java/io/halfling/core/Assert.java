package io.halfling.core;

/**
 * Created by willie on 6/24/17.
 */
public final class Assert {

	private Assert() {
	}

	public static void notNull(Object o, String message) {
		isTrue(o != null, message);
	}
	
	public static void isInRange(int n, int lower, int upper, String message) {
		isTrue(lower <= n && n <= upper, message);
	}
	
	public static void isStrictlyPositive(int n, String message) {
		isTrue(n > 0, message);
	}

	public static void isTrue(boolean test, String message) {
		if (!test) {
			throw new IllegalArgumentException(message);
		}
	}
}
