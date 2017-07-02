package bb.attract;

import bb.common.AbstractBBModeStateMachine;
import bb.common.BBContext;
import bb.framework.event.ScreenEvent;
import bb.framework.util.Assert;
import bb.framework.view.BBScreen;
import bb.framework.view.BBScreenManager;

/**
 * Created by willie on 7/1/17.
 */
public class AttractStateMachine extends AbstractBBModeStateMachine {
	private AttractScreenFactory screenFactory;

	public AttractStateMachine(BBContext context, BBScreenManager screenManager) {
		super(screenManager);
		Assert.notNull(context, "context can't be null");
		this.screenFactory = new AttractScreenFactory(context);
	}

	@Override
	public void handleEvent(ScreenEvent event) {
		BBScreen currentScreen = getCurrentScreen();

		int type = event.getType();
		if (type == ScreenEvent.START_1P_GAME || type == ScreenEvent.START_2P_GAME) {
			// TODO Handle choice between 1p and 2p game. [WLW]
			yield();
		} else if (type == ScreenEvent.SCREEN_ABORTED) {
			transitionTo(screenFactory.createTitleScreen());
		} else if (type == ScreenEvent.SCREEN_EXPIRED) {
			if (currentScreen instanceof TitleScreen) {
				transitionTo(screenFactory.createBackstoryScreen());
			} else if (currentScreen instanceof BackstoryScreen) {
				transitionTo(screenFactory.createRosterScreen());
			} else if (currentScreen instanceof RosterScreen) {
				transitionTo(screenFactory.createTitleScreen());
			} else {
				throw new IllegalArgumentException("Unexpected screen: " + currentScreen);
			}
		} else {
			throw new IllegalArgumentException("Unexpected game event type: " + type);
		}
	}

	@Override
	public void start() {
		transitionTo(screenFactory.createTitleScreen());
	}
}
