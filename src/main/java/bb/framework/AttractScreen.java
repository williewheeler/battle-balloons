package bb.framework;

import bb.BBContext;
import bb.framework.event.GameListener;

/**
 * Created by willie on 6/25/17.
 */
public abstract class AttractScreen extends GameScreen {
	private int ttl;

	public AttractScreen(BBContext context, GameListener gameListener, int ttl) {
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
