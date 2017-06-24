package bb.framework;

import bb.framework.model.GameModel;
import bb.framework.view.GameScreen;

import java.awt.event.KeyListener;

/**
 * Created by willie on 6/17/17.
 */
public interface GameController {

	GameModel getGameModel();

	GameScreen getGameScreen();

	KeyListener getKeyListener();

	void start();

	void stop();
}
