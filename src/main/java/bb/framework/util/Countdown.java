package bb.framework.util;

/**
 * Created by willie on 7/2/17.
 */
public class Countdown {
	private int count;

	public Countdown(int count) {
		Assert.isStrictlyPositive(count, "count must be strictly positive");
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public boolean isActive() {
		return count > 0;
	}

	public void decrement() {
		this.count = Math.max(0, count - 1);
	}
}
