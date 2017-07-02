package bb.common;

/**
 * Created by willie on 7/1/17.
 */
public interface BBScreenManager {

	void startScreen(BBScreen screen);

	BBScreen getCurrentScreen();

	void stopCurrentScreen();
}
