package bb.common;

import bb.framework.BBModeStateMachine;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.util.Assert;
import bb.framework.view.BBScreen;
import bb.framework.view.BBScreenManager;

/**
 * Created by willie on 7/1/17.
 */
public abstract class AbstractBBModeStateMachine implements BBModeStateMachine {
	private BBContext context;
	private BBScreenManager screenManager;
	private GameListener modeListener;
	private BBScreen currentScreen;

	public AbstractBBModeStateMachine(BBContext context, BBScreenManager screenManager, GameListener modeListener) {
		Assert.notNull(context, "context can't be null");
		Assert.notNull(screenManager, "screenManager can't be null");
		Assert.notNull(modeListener, "modeListener can't be null");
		this.context = context;
		this.screenManager = screenManager;
		this.modeListener = modeListener;
	}

	public BBContext getContext() {
		return context;
	}

	public BBScreen getCurrentScreen() {
		return screenManager.getCurrentScreen();
	}

	@Override
	public void stop() {
		modeListener.handleEvent(new GameEvent(this, GameEvent.MODE_EXPIRED));
	}

	@Override
	public void transitionTo(BBScreen screen) {
		Assert.notNull(screen, "screen can't be null");
		if (currentScreen != null) {
			screenManager.stopCurrentScreen();
		}
		this.currentScreen = screen;
		screenManager.startScreen(screen);
	}
}
