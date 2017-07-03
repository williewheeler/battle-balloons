package bb.game.arena.view;

import bb.common.BBContext;
import bb.common.scene.ScenePane;
import bb.framework.util.Assert;
import bb.game.arena.model.ArenaScene;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import static bb.common.BBConfig.ARENA_PANE_SIZE_PX;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaPane extends JPanel {
	private static final int BORDER_SIZE_PX = 2;

	private ArenaScene scene;
	private ScenePane scenePane;

	public ArenaPane(BBContext context, ArenaScene scene) {
		Assert.notNull(context, "context can't be null");
		Assert.notNull(scene, "scene can't be null");

		this.scenePane = new ScenePane(context, scene);

		setBackground(Color.YELLOW);
		setLayout(new GridBagLayout());

		add(scenePane, buildSceneGBC());
	}

	@Override
	public Dimension getPreferredSize() {
		return ARENA_PANE_SIZE_PX;
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
