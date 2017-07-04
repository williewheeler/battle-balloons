package bb.framework.screen;

/**
 * Created by willie on 7/1/17.
 */
public interface ScreenManager {

	/**
	 * Stops the current screen if there is one, and then starts the given screen.
	 *
	 * @param screen screen to start
	 */
	void startScreen(Screen screen);

	/**
	 * Stops the current screen if there is one.
	 */
	void stopCurrentScreen();
}
