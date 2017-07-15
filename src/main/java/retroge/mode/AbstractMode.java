package retroge.mode;

import retroge.event.ModeListener;
import retroge.event.ModeEvent;
import retroge.screen.Screen;
import retroge.screen.ScreenManager;
import retroge.util.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by willie on 7/1/17.
 */
public abstract class AbstractMode implements Mode {
	private final String name;
	private final ScreenManager screenManager;
	private final List<ModeListener> modeListeners = new LinkedList<>();
	
	private Screen currentScreen;

	public AbstractMode(String name, ScreenManager screenManager) {
		Assert.notNull(name, "name can't be null");
		Assert.notNull(screenManager, "screenManager can't be null");
		this.name = name;
		this.screenManager = screenManager;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public Screen getCurrentScreen() {
		return currentScreen;
	}
	
	@Override
	public void stop() {
		fireModeEvent(ModeEvent.MODE_STOPPED);
	}
	
	protected void transitionTo(Screen screen) {
		if (screen == null) {
			throw new NullPointerException("screen can't be null");
		}
		this.currentScreen = screen;
		screenManager.startScreen(screen);
	}
	
	@Override
	public void addModeListener(ModeListener listener) {
		Assert.notNull(listener, "listener can't be null");
		modeListeners.add(listener);
	}
	
	protected void fireModeEvent(int type) {
		ModeEvent event = new ModeEvent(type);
		modeListeners.forEach(listener -> listener.handleEvent(event));
	}
}
