package bb.framework.util;

/**
 * Created by willie on 7/2/17.
 */
public class CyclicCounter {
	private int upperBound;
	private int count;

	/**
	 * @param upperBound exclusive upper bound
	 */
	public CyclicCounter(int upperBound) {
		Assert.isStrictlyPositive(upperBound, "upperBound must be strictly positive");
		this.upperBound = upperBound;
		this.count = 0;
	}

	public int getUpperBound() {
		return upperBound;
	}

	public int getCount() {
		return count;
	}

	public void increment() {
		this.count = (count + 1) % upperBound;
	}
}
