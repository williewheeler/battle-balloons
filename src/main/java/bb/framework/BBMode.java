package bb.framework;

import bb.framework.util.Assert;

/**
 * Created by willie on 7/1/17.
 */
public class BBMode {
	private String name;
	private BBModeStateMachine stateMachine;

	public BBMode(String name, BBModeStateMachine stateMachine) {
		Assert.notNull(name, "name can't be null");
		Assert.notNull(stateMachine, "stateMachine can't be null");
		this.name = name;
		this.stateMachine = stateMachine;
	}

	public String getName() {
		return name;
	}

	public void start() {
		stateMachine.start();
	}

	public void stop() {
		stateMachine.stop();
	}
}
