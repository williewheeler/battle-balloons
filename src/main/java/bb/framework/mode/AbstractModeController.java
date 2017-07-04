package bb.framework.mode;

import bb.framework.event.ModeEvent;
import bb.framework.event.ModeListener;
import bb.framework.screen.Screen;
import bb.framework.screen.ScreenManager;
import bb.framework.util.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by willie on 7/1/17.
 */
public abstract class AbstractModeController implements ModeController {
	private ScreenManager screenManager;
	private Screen currentScreen;
	private final List<ModeListener> modeListeners = new LinkedList<>();

	public AbstractModeController(ScreenManager screenManager) {
		Assert.notNull(screenManager, "screenManager can't be null");
		this.screenManager = screenManager;
	}

	public Screen getCurrentScreen() {
		return currentScreen;
	}

	@Override
	public void transitionTo(Screen screen) {
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
