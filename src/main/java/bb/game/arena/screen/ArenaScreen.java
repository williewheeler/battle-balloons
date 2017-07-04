package bb.game.arena.screen;

import bb.common.BBConfig;
import bb.common.BBContext;
import bb.common.actor.event.ActorEvents;
import bb.common.resource.AudioFactory;
import bb.common.screen.SceneScreen;
import bb.framework.actor.DirectionIntent;
import bb.framework.actor.brain.ActorBrain;
import bb.framework.event.ActorEvent;
import bb.framework.event.ActorListener;
import bb.game.GameScreenNames;
import bb.game.arena.scene.ArenaScene;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static bb.common.BBConfig.ARENA_MARGIN_LEFT_RIGHT_PX;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaScreen extends SceneScreen {
	private AudioHandler audioHandler;

	public static ArenaScreen create(BBConfig config, BBContext context, ArenaScene scene) {
		ArenaScreen screen = new ArenaScreen(config, context, scene);
		screen.postConstruct();
		return screen;
	}

	private ArenaScreen(BBConfig config, BBContext context, ArenaScene scene) {
		super(GameScreenNames.ARENA_SCREEN, config, context, scene);
		this.audioHandler = new AudioHandler();
		scene.addActorListener(audioHandler);
	}

	@Override
	public JComponent buildJComponent() {
		BBContext context = (BBContext) getContext();
		ArenaScene scene = (ArenaScene) getScene();

		JComponent arenaHeader = new ArenaHeader(context, scene);
		JComponent arenaPane = buildArenaPane();
		JComponent arenaFooter = new ArenaFooter(context, scene);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(arenaHeader);
		panel.add(arenaPane);
		panel.add(arenaFooter);
		return panel;
	}

	@Override
	public KeyListener buildKeyHandler() {
		return new KeyHandler();
	}

	private JComponent buildArenaPane() {
		BBContext context = (BBContext) getContext();
		ArenaScene scene = (ArenaScene) getScene();

		JPanel wrapper = new JPanel();
		wrapper.setBackground(null);
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
		wrapper.add(Box.createRigidArea(new Dimension(ARENA_MARGIN_LEFT_RIGHT_PX, 0)));
		wrapper.add(new ArenaPane(context, scene));
		wrapper.add(Box.createRigidArea(new Dimension(ARENA_MARGIN_LEFT_RIGHT_PX, 0)));
		return wrapper;
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
			BBContext context = (BBContext) getContext();
			AudioFactory audioFactory = context.getAudioFactory();
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
