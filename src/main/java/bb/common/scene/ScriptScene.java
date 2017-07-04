package bb.common.scene;

/**
 * Created by willie on 7/3/17.
 */
public abstract class ScriptScene extends BBScene {
	private int counter = 0;
	private boolean active = true;

	@Override
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public void update() {
		super.update();
		doScript(counter);
		this.counter++;
	}

	// TODO A little tricky to work with frame counts here.
	// Might be better to give the user a way to specify actions in units of time.
	// But not all values would come in, so we would have to account for that. [WLW]
	public abstract void doScript(int counter);
}
