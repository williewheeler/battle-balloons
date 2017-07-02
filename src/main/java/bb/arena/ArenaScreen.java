package bb.arena;

import bb.common.BBContext;
import bb.arena.event.ArenaEvents;
import bb.arena.model.ArenaModel;
import bb.arena.model.DirectionIntent;
import bb.arena.model.Player;
import bb.arena.view.ArenaFooter;
import bb.arena.view.ArenaHeader;
import bb.arena.view.ArenaPane;
import bb.common.view.AudioFactory;
import bb.framework.event.ArenaEvent;
import bb.framework.event.ArenaListener;
import bb.framework.event.GameListener;
import bb.framework.view.BBScreen;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaScreen extends BBScreen {
	private ArenaModel model;
	private ArenaHeader arenaHeader;
	private ArenaPane arenaPane;
	private ArenaFooter arenaFooter;
	private AudioHandler audioHandler;

	public ArenaScreen(BBContext context, GameListener gameListener) {
		super(context, gameListener);
		this.model = new ArenaModel();
		this.arenaHeader = new ArenaHeader(context, model);
		this.arenaPane = new ArenaPane(context, model);
		this.arenaFooter = new ArenaFooter(context, model);

		setLayout(new BorderLayout());
		add(arenaHeader, BorderLayout.NORTH);
		add(arenaPane, BorderLayout.CENTER);
		add(arenaFooter, BorderLayout.SOUTH);

		this.audioHandler = new AudioHandler();
		model.addGameListener(audioHandler);
	}

	@Override
	public KeyListener buildKeyListener() {
		return new KeyHandler();
	}

	@Override
	public boolean isActive() {
		return true;
	}

	// TODO Get rid of this once ArenaModel is gone.
	@Deprecated
	@Override
	public void updateModel() {
		super.updateModel();
		model.update();
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
			Player player = model.getPlayer();
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

		@Override
		public void handleEvent(ArenaEvent event) {
			AudioFactory audioFactory = getContext().getAudioFactory();
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
