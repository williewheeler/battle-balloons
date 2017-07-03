package bb.game.arena.view;

import bb.common.BBContext;
import bb.common.screen.BBScreen;
import bb.common.event.ActorEvents;
import bb.common.factory.AudioFactory;
import bb.framework.actor.ActorBrain;
import bb.framework.actor.DirectionIntent;
import bb.framework.event.ActorEvent;
import bb.framework.event.ActorListener;
import bb.game.arena.model.ArenaScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static bb.common.BBConfig.ARENA_MARGIN_LEFT_RIGHT_PX;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaScreen extends BBScreen {
	private static final Logger log = LoggerFactory.getLogger(ArenaScene.class);

	private JComponent arenaPane;
	private JComponent arenaHeader;
	private JComponent arenaFooter;
	private AudioHandler audioHandler;

	public ArenaScreen(BBContext context, ArenaScene scene) {
		super(context, scene);

		this.arenaHeader = new ArenaHeader(context, scene);
		this.arenaPane = buildArenaPane(context, scene);
		this.arenaFooter = new ArenaFooter(context, scene);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(arenaHeader);
		add(arenaPane);
		add(arenaFooter);

		this.audioHandler = new AudioHandler();
		scene.addActorListener(audioHandler);
	}

	private JComponent buildArenaPane(BBContext context, ArenaScene scene) {
		this.arenaPane = new ArenaPane(context, scene);

		JPanel wrapper = new JPanel();
		wrapper.setBackground(null);
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
		wrapper.add(Box.createRigidArea(new Dimension(ARENA_MARGIN_LEFT_RIGHT_PX, 0)));
		wrapper.add(arenaPane);
		wrapper.add(Box.createRigidArea(new Dimension(ARENA_MARGIN_LEFT_RIGHT_PX, 0)));

		return wrapper;
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
			ActorBrain brain = scene.getPlayer().getActor().getBrain();
			DirectionIntent moveIntent = brain.getMoveDirectionIntent();
			DirectionIntent fireIntent = brain.getFireDirectionIntent();

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

	// TODO Move this outside the arena since this could happen in attract mode too. [WLW]
	private class AudioHandler implements ActorListener {

		@Override
		public void handleEvent(ActorEvent event) {
			AudioFactory audioFactory = getContext().getAudioFactory();
			if (event == ActorEvents.PLAYER_WALKS) {
				audioFactory.playerWalks();
			} else if (event == ActorEvents.PLAYER_COLLISION) {
				audioFactory.playerCollision();
			} else if (event == ActorEvents.PLAYER_THROWS_BALLOON) {
				audioFactory.playerThrowsBalloon();
			} else if (event == ActorEvents.JUDO_HIT) {
				audioFactory.judoHit();
			}
		}
	}
}
