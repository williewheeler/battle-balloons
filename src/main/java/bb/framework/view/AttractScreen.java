package bb.framework.view;

/**
 * Created by willie on 6/25/17.
 */
public class AttractScreen extends GameScreen {
	private int ttl;

	public AttractScreen(int ttl) {
		this.ttl = ttl;
	}

	public int getTtl() {
		return ttl;
	}

	@Override
	public void update() {
		super.update();
		this.ttl--;
	}

	@Override
	public boolean isActive() {
		return ttl > 0;
	}
}
