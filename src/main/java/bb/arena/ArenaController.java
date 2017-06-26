package bb.arena;

import bb.framework.event.ArenaEvent;
import bb.arena.event.ArenaEvents;
import bb.framework.event.ArenaListener;
import bb.arena.model.ArenaModel;
import bb.arena.model.DirectionIntent;
import bb.arena.model.Player;
import bb.arena.view.ArenaScreen;
import bb.framework.AbstractGameController;
import bb.common.view.AudioFactory;
import bb.framework.event.GameListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by willie on 6/17/17.
 */
public class ArenaController extends AbstractGameController {
	private AudioHandler audioHandler;

	public ArenaController(
			ArenaModel model,
			ArenaScreen screen,
			AudioFactory audioFactory,
			GameListener gameListener) {

		super(model, screen, gameListener);

		setKeyListener(new KeyHandler(model.getPlayer()));

		this.audioHandler = new AudioHandler(audioFactory);
		model.addGameListener(audioHandler);
	}

	private class KeyHandler extends KeyAdapter {
		private Player player;

		public KeyHandler(Player player) {
			this.player = player;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			updatePlayerIntent(e.getKeyCode(), true);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			super.keyReleased(e);
			updatePlayerIntent(e.getKeyCode(), false);
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

	private class AudioHandler implements ArenaListener {
		private AudioFactory audioFactory;

		public AudioHandler(AudioFactory audioFactory) {
			this.audioFactory = audioFactory;
		}

		@Override
		public void handleEvent(ArenaEvent event) {
			if (event == ArenaEvents.PLAYER_WALKS) {
				audioFactory.playerWalks();
			} else if (event == ArenaEvents.PLAYER_COLLISION) {
				audioFactory.playerCollision();
			} else if (event == ArenaEvents.PLAYER_THROWS_BALLOON) {
				audioFactory.playerThrowsBalloon();
			} else if (event == ArenaEvents.JUDO_HIT) {
				audioFactory.judoHit();
			}
		}
	}
}
