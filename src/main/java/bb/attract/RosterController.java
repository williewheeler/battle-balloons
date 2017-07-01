package bb.attract;

import bb.framework.AbstractGameController;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.view.GameScreen;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// TODO Move controllers into the screen component [WLW]

/**
 * Created by willie on 6/19/17.
 */
public class RosterController extends AbstractGameController {

	public RosterController(GameScreen screen, GameListener gameListener) {
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
