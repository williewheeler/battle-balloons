package bb.framework.view;

import bb.common.BBContext;
import bb.framework.event.GameListener;

/**
 * A game screen with a defined time-to-live (TTL). This is useful for attract mode screens, level transition screens,
 * game over screens and any other screens that should automatically disappear after some period of time.
 *
 * Created by willie on 6/25/17.
 */
public abstract class TtlScreen extends BBScreen {
	private int ttl;

	public TtlScreen(BBContext context, GameListener gameListener, int ttl) {
		super(context, gameListener);
		this.ttl = ttl;
	}

	@Override
	public boolean isActive() {
		return ttl > 0;
	}

	@Override
	public void updateModel() {
		super.updateModel();
		this.ttl--;
	}
}
