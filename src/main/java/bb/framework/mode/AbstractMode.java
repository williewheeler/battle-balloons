package bb.framework.mode;

import bb.framework.event.ModeListener;
import bb.framework.util.Assert;

/**
 * Created by willie on 7/1/17.
 */
public abstract class AbstractMode implements Mode {
	private String name;
	private ModeController stateMachine;

	public AbstractMode(String name) {
		Assert.notNull(name, "name can't be null");
		this.name = name;
	}

	public void setStateMachine(ModeController stateMachine) {
		this.stateMachine = stateMachine;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void start() {
		stateMachine.start();
	}

	@Override
	public void addModeListener(ModeListener listener) {
		Assert.notNull(listener, "listener can't be null");
		stateMachine.addModeListener(listener);
	}
}
