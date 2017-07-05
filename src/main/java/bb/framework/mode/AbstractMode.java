package bb.framework.mode;

import bb.framework.event.ModeListener;
import bb.framework.util.Assert;

/**
 * Created by willie on 7/1/17.
 */
public abstract class AbstractMode implements Mode {
	private String name;
	private ModeController modeController;

	public AbstractMode(String name) {
		Assert.notNull(name, "name can't be null");
		this.name = name;
	}

	public void setModeController(ModeController modeController) {
		this.modeController = modeController;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void start() {
		checkModeController();
		modeController.start();
	}

	@Override
	public void addModeListener(ModeListener listener) {
		Assert.notNull(listener, "listener can't be null");
		checkModeController();
		modeController.addModeListener(listener);
	}

	private void checkModeController() {
		if (modeController == null) {
			throw new IllegalStateException("modeController can't be null");
		}
	}
}
