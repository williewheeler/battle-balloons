package bb.attract.title;

import bb.core.AbstractGameController;
import bb.core.event.GameEvent;
import bb.core.event.GameListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by willie on 6/17/17.
 */
public class TitleController extends AbstractGameController {

	public TitleController(TitleModel model, TitleScreen screen, GameListener gameListener) {
		super(model, screen, gameListener);

		setKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				switch (e.getKeyCode()) {
					case KeyEvent.VK_1:
						fireGameEvent(GameEvent.START_1P_GAME);
						break;
					case KeyEvent.VK_2:
						fireGameEvent(GameEvent.START_2P_GAME);
						break;
				}
			}
		});
	}
}
