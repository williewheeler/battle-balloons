package bb.attract;

import bb.framework.AbstractGameController;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.view.GameScreen;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by willie on 6/18/17.
 */
public class BackstoryController extends AbstractGameController {

	public BackstoryController(GameScreen screen, GameListener gameListener) {
		super(null, screen, gameListener);

		setKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				fireGameEvent(GameEvent.SCREEN_ABORTED);
			}
		});
	}
}
