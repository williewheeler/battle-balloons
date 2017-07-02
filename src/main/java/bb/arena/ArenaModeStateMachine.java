package bb.arena;

import bb.common.AbstractBBModeStateMachine;
import bb.common.BBContext;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.view.BBScreenManager;

/**
 * Created by willie on 7/1/17.
 */
public class ArenaModeStateMachine extends AbstractBBModeStateMachine {

	public ArenaModeStateMachine(BBContext context, BBScreenManager screenManager, GameListener modeListener) {
		super(context, screenManager, modeListener);
	}

	@Override
	public void handleEvent(GameEvent event) {
		// TODO
	}

	@Override
	public void start() {
		transitionTo(new ArenaScreen(getContext(), this));
	}
}
