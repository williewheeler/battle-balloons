package bb.input;

import bb.model.DirectionIntent;
import bb.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

/**
 * This component maps key events to player intents.
 *
 * Created by willie on 6/7/17.
 */
public class KeyboardManager implements KeyEventDispatcher {
	private static final Logger log = LoggerFactory.getLogger(KeyboardManager.class);

	private Player player;

	public KeyboardManager(Player player) {
		this.player = player;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		int keyId = e.getID();
		int keyCode = e.getKeyCode();

		if (keyId == KeyEvent.KEY_PRESSED) {
			updatePlayerIntent(keyCode, true);
		} else if (keyId == KeyEvent.KEY_RELEASED) {
			updatePlayerIntent(keyCode, false);
		}

		return true;
	}

	private void updatePlayerIntent(int keyCode, boolean value) {
		DirectionIntent moveIntent = player.getMoveIntent();
		DirectionIntent fireIntent = player.getFireIntent();

		switch (keyCode) {
			case KeyEvent.VK_T:
				moveIntent.up = value;
				break;
			case KeyEvent.VK_G:
				moveIntent.down = value;
				break;
			case KeyEvent.VK_F:
				moveIntent.left = value;
				break;
			case KeyEvent.VK_H:
				moveIntent.right = value;
				break;
			case KeyEvent.VK_UP:
				fireIntent.up = value;
				break;
			case KeyEvent.VK_DOWN:
				fireIntent.down = value;
				break;
			case KeyEvent.VK_LEFT:
				fireIntent.left = value;
				break;
			case KeyEvent.VK_RIGHT:
				fireIntent.right = value;
				break;
		}
	}
}
