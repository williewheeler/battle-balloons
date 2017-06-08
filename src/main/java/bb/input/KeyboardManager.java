package bb.input;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

/**
 * Created by willie on 6/7/17.
 */
public class KeyboardManager implements KeyEventDispatcher {
	private static final Logger log = LoggerFactory.getLogger(KeyboardManager.class);

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		log.trace("KeyEvent: {}", e);
		return false;
	}
}
