package bb.framework.util;

/**
 * Created by willie on 6/24/17.
 */
public final class Assert {

	private Assert() {
	}

	public static void notNull(Object o, String message) {
		if (o == null) {
			throw new IllegalArgumentException(message);
		}
	}
}
