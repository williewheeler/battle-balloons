package bb.core;

import bb.core.model.GameModel;

import javax.swing.JComponent;
import java.awt.event.KeyListener;

/**
 * Created by willie on 6/17/17.
 */
public interface GameController {

	GameModel getGameModel();

	JComponent getGameScreen();

	KeyListener getKeyListener();

	void start();

	void stop();
}
