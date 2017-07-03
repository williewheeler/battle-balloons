package bb.common.scene;

import bb.framework.util.Assert;
import bb.framework.util.Countdown;

/**
 * Created by willie on 7/2/17.
 */
public class TtlScene extends Scene {
	private Countdown ttlCountdown;

	public TtlScene(int ttl) {
		Assert.isStrictlyPositive(ttl, "ttl must be strictly positive");
		this.ttlCountdown = new Countdown(ttl);
	}

	@Override
	public void update() {
		super.update();
		ttlCountdown.decrement();
	}

	@Override
	public boolean isActive() {
		return ttlCountdown.isActive();
	}
}
