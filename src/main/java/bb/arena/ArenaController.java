package bb.arena;

import bb.BB;
import bb.arena.model.ArenaModel;
import bb.arena.model.DirectionIntent;
import bb.arena.model.Player;
import bb.arena.event.ArenaEvent;
import bb.arena.event.ArenaEvents;
import bb.arena.event.ArenaListener;
import bb.arena.view.ArenaScreen;
import bb.core.GameController;
import bb.core.audio.AudioFactory;

import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static bb.BBConfig.FRAME_PERIOD_MS;

/**
 * Created by willie on 6/17/17.
 */
public class ArenaController implements GameController {
	private BB bb;
	private ArenaModel arenaModel;
	private ArenaScreen arenaScreen;
	private TickHandler tickHandler;
	private KeyHandler keyHandler;
	private AudioHandler audioHandler;
	private Timer timer;

	public ArenaController(BB bb) {
		this.bb = bb;
		this.arenaModel = new ArenaModel();
		this.arenaScreen = new ArenaScreen(arenaModel, bb.getFontFactory(), bb.getSpriteFactory());
		this.tickHandler = new TickHandler();
		this.audioHandler = new AudioHandler(bb.getAudioFactory());
		this.keyHandler = new KeyHandler(arenaModel.getPlayer());

		this.timer = new Timer(FRAME_PERIOD_MS, tickHandler);
		arenaModel.addGameListener(audioHandler);
	}

	@Override
	public void start() {
		timer.start();
	}

	@Override
	public void stop() {
		timer.stop();
	}

	@Override
	public JComponent getScreen() {
		return arenaScreen;
	}

	@Override
	public KeyListener getKeyListener() {
		return keyHandler;
	}

	private class TickHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			arenaModel.update();
			bb.repaint();
		}
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
