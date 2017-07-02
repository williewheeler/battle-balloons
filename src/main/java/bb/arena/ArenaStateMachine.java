package bb.arena;

import bb.common.AbstractBBModeStateMachine;
import bb.common.BBContext;
import bb.framework.event.ScreenEvent;
import bb.framework.util.Assert;
import bb.framework.view.BBScreenManager;

/**
 * Created by willie on 7/1/17.
 */
public class ArenaStateMachine extends AbstractBBModeStateMachine {
	private ArenaScreenFactory screenFactory;

	public ArenaStateMachine(BBContext context, BBScreenManager screenManager) {
		super(screenManager);
		Assert.notNull(context, "context can't be null");
		this.screenFactory = new ArenaScreenFactory(context);
	}

	@Override
	public void handleEvent(ScreenEvent event) {
		// TODO
	}

	@Override
	public void start() {
		transitionTo(screenFactory.createArenaScreen());
	}
}
