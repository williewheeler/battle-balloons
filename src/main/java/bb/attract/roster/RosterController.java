package bb.attract.roster;

import bb.core.AbstractGameController;
import bb.core.event.GameEvent;
import bb.core.event.GameListener;
import bb.core.view.SpriteFactory;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by willie on 6/19/17.
 */
public class RosterController extends AbstractGameController {
	private Actor lexi;

	public RosterController(
			RosterModel model,
			RosterScreen screen,
			GameListener gameListener) {

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
