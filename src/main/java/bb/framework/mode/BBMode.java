package bb.framework.mode;

import bb.framework.event.ModeListener;

/**
 * Created by willie on 7/1/17.
 */
public interface BBMode {

	String getName();

	void start();

	void addModeListener(ModeListener listener);
}
