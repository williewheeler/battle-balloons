package bb.common.mode;

import bb.framework.event.ModeListener;
import bb.framework.event.ScreenListener;
import bb.common.screen.BBScreen;

/**
 * Created by willie on 7/1/17.
 */
public interface BBModeStateMachine extends ScreenListener {

	/**
	 * Start the state machine.
	 */
	void start();

	/**
	 * Effect a state transition to the specified screen.
	 *
	 * @param screen
	 */
	void transitionTo(BBScreen screen);

	/**
	 * Yield control to the parent state machine.
	 */
	void yield();

	void addModeListener(ModeListener modeListener);
}
