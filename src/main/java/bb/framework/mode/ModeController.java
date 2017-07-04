package bb.framework.mode;

import bb.framework.event.ModeListener;
import bb.framework.event.ScreenListener;
import bb.framework.screen.Screen;

/**
 * Mode controller implemented as a state machine.
 *
 * Created by willie on 7/1/17.
 */
public interface ModeController extends ScreenListener {

	/**
	 * Start the state machine.
	 */
	void start();

	/**
	 * Effect a state transition.
	 *
	 * @param screen
	 */
	void transitionTo(Screen screen);

	/**
	 * Yield control to the parent controller.
	 */
	void yield();

	void addModeListener(ModeListener modeListener);
}
