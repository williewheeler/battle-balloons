package com.williewheeler.battleballoons.game.view;

import com.williewheeler.battleballoons.game.world.GameScene;
import io.halfling.world.entity.model.Player;
import com.williewheeler.battleballoons.common.view.BBViewContext;
import com.williewheeler.battleballoons.common.view.resource.FontFactory;
import com.williewheeler.battleballoons.common.view.resource.SpriteFactory;
import io.halfling.core.Assert;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static com.williewheeler.battleballoons.common.BBConfig.*;
import static com.williewheeler.battleballoons.game.view.GameConfig.ARENA_HEADER_P1_ANCHOR_PX;
import static com.williewheeler.battleballoons.game.view.GameConfig.ARENA_HEADER_SIZE_PX;

/**
 * Created by willie on 6/4/17.
 */
public class GameHeader extends JComponent {
	private BBViewContext context;
	private GameScene scene;
	
	/**
	 * A copy of the player's number of lives. We manage this separately from the scene because we want the display
	 * update to occur only after the new GameScreen appears.
	 */
	private int numLivesCopy;

	public GameHeader(BBViewContext context, GameScene scene) {
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
