package bb.framework.model;

import bb.framework.util.Assert;

/**
 * Created by willie on 6/24/17.
 */
public class AbstractAttractModel implements GameModel {
	private int ttl;
	private int counter;

	public AbstractAttractModel(int ttl) {
		Assert.isPositive(ttl, "ttl must be positive");
		this.ttl = ttl;
		this.counter = 0;
	}

	public int getCounter() {
		return counter;
	}

	@Override
	public boolean isActive() {
		return ttl > 0;
	}

	@Override
	public void update() {
		this.counter++;
		this.ttl--;
	}
}
