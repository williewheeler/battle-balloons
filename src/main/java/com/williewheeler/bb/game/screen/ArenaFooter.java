package com.williewheeler.bb.game.screen;

import com.williewheeler.bb.common.BBContext;
import com.williewheeler.bb.common.resource.FontFactory;
import com.williewheeler.bb.game.scene.ArenaScene;
import com.williewheeler.retroge.actor.Player;
import com.williewheeler.retroge.util.Assert;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;

import static com.williewheeler.bb.game.screen.ArenaConfig.*;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaFooter extends JComponent {
	private FontFactory fontFactory;
	private ArenaScene scene;

	public ArenaFooter(BBContext context, ArenaScene scene) {
		Assert.notNull(context, "context can't be null");
		Assert.notNull(scene, "scene can't be null");

		this.fontFactory = context.getFontFactory();
		this.scene = scene;
	}

	@Override
	public Dimension getPreferredSize() {
		return ARENA_FOOTER_SIZE_PX;
	}

	@Override
	public void paint(Graphics g) {
		Player player = scene.getPlayer();
		g.setFont(fontFactory.getSmallFont());
		FontMetrics fm = g.getFontMetrics();
		g.setColor(Color.RED);
		g.drawString("LEVEL", ARENA_FOOTER_LEVEL_LABEL_OFFSET_PX, fm.getHeight());
		g.setColor(Color.CYAN);
		g.drawString(String.valueOf(player.getLevel()), ARENA_FOOTER_LEVEL_VALUE_OFFSET_PX, fm.getHeight());
	}
}
