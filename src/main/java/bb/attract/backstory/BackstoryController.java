package bb.attract.backstory;

import bb.core.AbstractGameController;
import bb.core.event.GameEvent;
import bb.core.event.GameListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by willie on 6/18/17.
 */
public class BackstoryController extends AbstractGameController {

	public BackstoryController(BackstoryModel model, BackstoryScreen screen, GameListener gameListener) {
		super(model, screen, gameListener);

		setKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				fireGameEvent(GameEvent.SCREEN_ABORTED);
			}
		});
	}
}
