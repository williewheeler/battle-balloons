package bb.framework.mode;

import bb.framework.event.ModeListener;

/**
 * Created by willie on 7/1/17.
 */
public interface Mode {

	String getName();
	
	void start();
	
	void stop();
	
	void addModeListener(ModeListener listener);
}
