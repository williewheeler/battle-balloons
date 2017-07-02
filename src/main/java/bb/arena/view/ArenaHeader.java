package bb.arena.view;

import bb.common.BBContext;
import bb.arena.model.ArenaModel;
import bb.arena.model.Player;
import bb.common.view.FontFactory;
import bb.common.view.SpriteFactory;
import bb.framework.util.Assert;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static bb.common.BBConfig.*;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaHeader extends JComponent {
	private BBContext context;
	private ArenaModel model;

	public ArenaHeader(BBContext context, ArenaModel model) {
		Assert.notNull(context, "context can't be null");
		Assert.notNull(model, "model can't be null");
		this.context = context;
		this.model = model;
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

		Player player = model.getPlayer();

		g.setFont(fontFactory.getLargeFont());
		FontMetrics fm = g.getFontMetrics();
		String scoreStr = String.valueOf(player.getScore());
		int offset = fm.stringWidth(scoreStr) + 1;

		g.setColor(Color.CYAN);
		g.drawString(scoreStr, ARENA_HEADER_P1_ANCHOR_PX - offset, fm.getHeight());
	}

	private void paintLives(Graphics g) {
		SpriteFactory spriteFactory = context.getSpriteFactory();

		Player player = model.getPlayer();
		BufferedImage sprite = spriteFactory.getLexiLife();
		int numLives = player.getLives();
		for (int i = 0; i < numLives; i++) {
			int lifeX = ARENA_HEADER_P1_ANCHOR_PX + i * 8;
			g.drawImage(sprite, lifeX, -2, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX, null);
		}
	}
}
