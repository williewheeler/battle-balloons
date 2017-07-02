package bb.framework;

import bb.framework.event.GameListener;
import bb.framework.view.BBScreen;

/**
 * Created by willie on 7/1/17.
 */
public interface BBModeStateMachine extends GameListener {

	void start();

	void stop();

	void transitionTo(BBScreen screen);
}
