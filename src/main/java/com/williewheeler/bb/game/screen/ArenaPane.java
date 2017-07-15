package com.williewheeler.bb.game.screen;

import com.williewheeler.bb.common.BBContext;
import com.williewheeler.bb.common.actor.view.ActorViewFactory;
import com.williewheeler.bb.common.scene.BBScenePane;
import com.williewheeler.bb.game.scene.ArenaScene;
import com.williewheeler.retroge.util.Assert;

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
public class ArenaPane extends JPanel {
	private static final int BORDER_SIZE_PX = 2;

	public ArenaPane(BBContext context, ArenaScene scene) {
		Assert.notNull(context, "context can't be null");
		Assert.notNull(scene, "scene can't be null");

		initDefaults();
		initComponents(context, scene);
	}

	@Override
	public Dimension getPreferredSize() {
		return ArenaConfig.ARENA_PANE_SIZE_PX;
	}

	private void initDefaults() {
		setBackground(Color.YELLOW);
	}

	private void initComponents(BBContext context, ArenaScene scene) {
		ActorViewFactory avf = context.getActorViewFactory();
		setLayout(new GridBagLayout());
		add(new BBScenePane(avf, scene), buildSceneGBC());
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
