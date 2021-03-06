package com.williewheeler.battleballoons.game.view;

import com.williewheeler.battleballoons.common.view.BBViewContext;
import com.williewheeler.battleballoons.common.world.entity.view.ActorViewFactory;
import com.williewheeler.battleballoons.common.view.BBScenePane;
import com.williewheeler.battleballoons.game.world.GameScene;
import io.halfling.world.entity.model.Player;
import io.halfling.core.Assert;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Pane to render the large bordered region in which the battle takes place.
 *
 * Created by willie on 6/4/17.
 */
public class GamePane extends JPanel {
	private static final int BORDER_SIZE_PX = 2;
	
	private GameScene scene;
	
	public GamePane(BBViewContext context, GameScene scene) {
		Assert.notNull(context, "context can't be null");
		Assert.notNull(scene, "scene can't be null");

		this.scene = scene;
		initDefaults();
		initComponents(context, scene);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return GameConfig.ARENA_PANE_SIZE_PX;
	}
	
	private void initDefaults() {
		setBackground(getLevelColor());
	}

	private void initComponents(BBViewContext context, GameScene scene) {
		this.scene = scene;
		ActorViewFactory avf = context.getActorViewFactory();
		setLayout(new GridBagLayout());
		add(new BBScenePane(avf, scene), buildSceneGBC());
	}

	private Color getLevelColor() {
		assert (scene != null);
		Player player = scene.getPlayer();
		int level = player.getLevel();
		int colorIndex = level % GameConfig.COLOR_SCHEME.length;
		return GameConfig.COLOR_SCHEME[colorIndex];
	}
	
	private GridBagConstraints buildSceneGBC() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(BORDER_SIZE_PX, BORDER_SIZE_PX, BORDER_SIZE_PX, BORDER_SIZE_PX);
		return gbc;
	}
}
