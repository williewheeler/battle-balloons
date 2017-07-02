package bb.game.arena.view;

import bb.game.arena.event.GameEvents;
import bb.game.arena.model.ArenaScene;
import bb.game.arena.model.DirectionIntent;
import bb.game.arena.model.Player;
import bb.common.BBContext;
import bb.common.factory.AudioFactory;
import bb.common.BBScreen;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaScreen extends BBScreen {
	private ArenaHeader arenaHeader;
	private ArenaPane arenaPane;
	private ArenaFooter arenaFooter;
	private AudioHandler audioHandler;

	public ArenaScreen(BBContext context) {
		super(context, new ArenaScene());

		ArenaScene scene = (ArenaScene) getScene();
		this.arenaHeader = new ArenaHeader(context, scene);
		this.arenaPane = new ArenaPane(context, scene);
		this.arenaFooter = new ArenaFooter(context, scene);

		setLayout(new BorderLayout());
		add(arenaHeader, BorderLayout.NORTH);
		add(arenaPane, BorderLayout.CENTER);
		add(arenaFooter, BorderLayout.SOUTH);

		this.audioHandler = new AudioHandler();
		scene.addGameListener(audioHandler);
	}

	@Override
	public KeyListener buildKeyListener() {
		return new KeyHandler();
	}

	@Override
	public boolean isActive() {
		return true;
	}

	private class KeyHandler extends KeyAdapter {

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
			ArenaScene scene = (ArenaScene) getScene();
			Player player = scene.getPlayer();
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

	private class AudioHandler implements GameListener {

		@Override
		public void handleEvent(GameEvent event) {
			AudioFactory audioFactory = getContext().getAudioFactory();
			if (event == GameEvents.PLAYER_WALKS) {
				audioFactory.playerWalks();
			} else if (event == GameEvents.PLAYER_COLLISION) {
				audioFactory.playerCollision();
			} else if (event == GameEvents.PLAYER_THROWS_BALLOON) {
				audioFactory.playerThrowsBalloon();
			} else if (event == GameEvents.JUDO_HIT) {
				audioFactory.judoHit();
			}
		}
	}
}
