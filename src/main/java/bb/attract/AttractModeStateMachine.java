package bb.attract;

import bb.common.AbstractBBModeStateMachine;
import bb.common.BBContext;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.view.BBScreen;
import bb.framework.view.BBScreenManager;

/**
 * Created by willie on 7/1/17.
 */
public class AttractModeStateMachine extends AbstractBBModeStateMachine {

	public AttractModeStateMachine(BBContext context, BBScreenManager screenManager, GameListener modeListener) {
		super(context, screenManager, modeListener);
	}

	@Override
	public void handleEvent(GameEvent event) {
		BBContext context = getContext();
		BBScreen currentScreen = getCurrentScreen();

		String type = event.getType();
		if (type == GameEvent.START_1P_GAME || type == GameEvent.START_2P_GAME) {
			// TODO Handle choice between 1p and 2p game. [WLW]
			stop();
		} else if (type == GameEvent.SCREEN_ABORTED) {
			transitionTo(new TitleScreen(context, this));
		} else if (type == GameEvent.SCREEN_EXPIRED) {
			if (currentScreen instanceof TitleScreen) {
				transitionTo(new BackstoryScreen(context, this));
			} else if (currentScreen instanceof BackstoryScreen) {
				transitionTo(new RosterScreen(context, this));
			} else if (currentScreen instanceof RosterScreen) {
				transitionTo(new TitleScreen(context, this));
			} else {
				throw new IllegalArgumentException("Unexpected screen: " + currentScreen);
			}
		} else {
			throw new IllegalArgumentException("Unexpected game event type: " + type);
		}
	}

	@Override
	public void start() {
		transitionTo(new TitleScreen(getContext(), this));
	}
}
