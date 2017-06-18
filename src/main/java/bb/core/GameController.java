package bb.core;

import javax.swing.JComponent;
import java.awt.event.KeyListener;

/**
 * Created by willie on 6/17/17.
 */
public interface GameController {

	void start();

	void stop();

	JComponent getScreen();

	KeyListener getKeyListener();
}
