package com.williewheeler.battleballoons.game.view;

import com.williewheeler.battleballoons.common.BBConfig;
import com.williewheeler.battleballoons.common.view.BBViewContext;
import com.williewheeler.battleballoons.common.world.BBScene;
import com.williewheeler.battleballoons.common.view.SceneScreen;
import com.williewheeler.battleballoons.game.GameScreenNames;
import com.williewheeler.battleballoons.game.world.GameScene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.halfling.world.entity.model.DirectionIntent;
import io.halfling.world.entity.brain.ActorBrain;
import io.halfling.core.Assert;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.williewheeler.battleballoons.game.view.GameConfig.ARENA_MARGIN_LEFT_RIGHT_PX;

// TODO Implement Game Over as part of this screen, since we want to overlay "Game Over" on the arena. [WLW]

/**
 * Created by willie on 6/4/17.
 */
public class GameScreen extends SceneScreen {
	private static final Logger log = LoggerFactory.getLogger(GameScreen.class);
	
	public static GameScreen create(BBConfig config, BBViewContext context, BBScene scene) {
		Assert.notNull(config, "config can't be null");
		Assert.notNull(context, "context can't be null");
		Assert.notNull(scene, "scene can't be null");
		GameScreen screen = new GameScreen(config, context, scene);
		screen.postConstruct();
		return screen;
	}

	private GameScreen(BBConfig config, BBViewContext context, BBScene scene) {
		super(GameScreenNames.ARENA_SCREEN, config, context, scene);
	}
	
	@Override
	public JComponent buildJComponent() {
		BBViewContext context = (BBViewContext) getContext();
		GameScene scene = (GameScene) getScene();

		JComponent arenaHeader = new GameHeader(context, scene);
		JComponent arenaPane = buildArenaPane();
		JComponent arenaFooter = new GameFooter(context, scene);

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
		BBViewContext context = (BBViewContext) getContext();
		GameScene scene = (GameScene) getScene();

		JPanel wrapper = new JPanel();
		wrapper.setBackground(null);
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
		wrapper.add(Box.createRigidArea(new Dimension(ARENA_MARGIN_LEFT_RIGHT_PX, 0)));
		wrapper.add(new GamePane(context, scene));
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
			GameScene scene = (GameScene) getScene();
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
}
