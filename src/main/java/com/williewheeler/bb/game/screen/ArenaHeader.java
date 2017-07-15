package com.williewheeler.bb.game.screen;

import com.williewheeler.bb.game.scene.ArenaScene;
import com.williewheeler.retroge.actor.Player;
import com.williewheeler.bb.common.BBContext;
import com.williewheeler.bb.common.resource.FontFactory;
import com.williewheeler.bb.common.resource.SpriteFactory;
import com.williewheeler.retroge.util.Assert;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static com.williewheeler.bb.common.BBConfig.*;
import static com.williewheeler.bb.game.screen.ArenaConfig.ARENA_HEADER_P1_ANCHOR_PX;
import static com.williewheeler.bb.game.screen.ArenaConfig.ARENA_HEADER_SIZE_PX;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaHeader extends JComponent {
	private BBContext context;
	private ArenaScene scene;
	
	/**
	 * A copy of the player's number of lives. We manage this separately from the scene because we want the display
	 * update to occur only after the new ArenaScreen appears.
	 */
	private int numLivesCopy;

	public ArenaHeader(BBContext context, ArenaScene scene) {
		Assert.notNull(context, "context can't be null");
		Assert.notNull(scene, "scene can't be null");
		this.context = context;
		this.scene = scene;
		this.numLivesCopy = scene.getPlayer().getLives();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return ARENA_HEADER_SIZE_PX;
	}

	@Override
	public void paint(Graphics g) {
		paintScore(g);
		paintLives(g);
	}

	private void paintScore(Graphics g) {
		FontFactory fontFactory = context.getFontFactory();

		Player player = scene.getPlayer();

		g.setFont(fontFactory.getLargeFont());
		FontMetrics fm = g.getFontMetrics();
		String scoreStr = String.valueOf(player.getScore());
		int offset = fm.stringWidth(scoreStr) + 1;

		g.setColor(Color.CYAN);
		g.drawString(scoreStr, ARENA_HEADER_P1_ANCHOR_PX - offset, fm.getHeight());
	}

	// TODO Don't render outside of the allowed area.
	// Don't want player 2 score overlapping with player 1 lives.
	private void paintLives(Graphics g) {
		SpriteFactory spriteFactory = context.getSpriteFactory();
		BufferedImage sprite = spriteFactory.getLexiLife();

		// Skip life 0, since that's the life that's playing
		for (int i = 1; i < numLivesCopy; i++) {
			int lifeX = ARENA_HEADER_P1_ANCHOR_PX + (i - 1) * 8;
			g.drawImage(sprite, lifeX, -2, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX, null);
		}
	}
}
