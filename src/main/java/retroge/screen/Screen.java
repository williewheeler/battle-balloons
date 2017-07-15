package retroge.screen;

import retroge.event.ScreenListener;

import javax.swing.JComponent;
import java.awt.event.KeyListener;

/**
 * A screen supports user interaction. It has a timer-driven {@link JComponent} for animated
 * rendering, and a {@link KeyListener} to support user inputs.
 *
 * Created by willie on 7/3/17.
 */
public interface Screen {

	String getName();

	JComponent getJComponent();

	KeyListener getKeyHandler();

	void start();

	void stop();
	
	void addScreenListener(ScreenListener listener);
}
