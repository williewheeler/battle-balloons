package bb.common.mode;

import bb.framework.event.ModeEvent;
import bb.framework.event.ModeListener;
import bb.framework.util.Assert;
import bb.common.screen.BBScreen;
import bb.common.screen.BBScreenManager;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by willie on 7/1/17.
 */
public abstract class AbstractModeStateMachine implements BBModeStateMachine {
	private BBScreenManager screenManager;
	private BBScreen currentScreen;
	private final List<ModeListener> modeListeners = new LinkedList<>();

	public AbstractModeStateMachine(BBScreenManager screenManager) {
		Assert.notNull(screenManager, "screenManager can't be null");
		this.screenManager = screenManager;
	}

	public BBScreen getCurrentScreen() {
		return currentScreen;
	}

	@Override
	public void transitionTo(BBScreen screen) {
		Assert.notNull(screen, "screen can't be null");
		if (currentScreen != null) {
			screenManager.stopCurrentScreen();
		}
		this.currentScreen = screen;
		currentScreen.addScreenListener(this);
		screenManager.startScreen(screen);
	}

	@Override
	public void yield() {
		fireModeEvent(ModeEvent.MODE_STOPPED);
	}

	public void addModeListener(ModeListener listener) {
		Assert.notNull(listener, "listener can't be null");
		modeListeners.add(listener);
	}

	protected void fireModeEvent(int type) {
		ModeEvent event = new ModeEvent(type);
		modeListeners.forEach(listener -> listener.handleEvent(event));
	}
}
