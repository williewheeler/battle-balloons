package bb.view.arena;

import bb.model.GameModel;
import bb.model.Player;
import bb.view.FontFactory;
import bb.view.SpriteFactory;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;

import static bb.BBConfig.*;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaHeader extends JComponent {
	private GameModel gameModel;
	private FontFactory fontFactory;
	private SpriteFactory spriteFactory;

	public ArenaHeader(GameModel gameModel, FontFactory fontFactory, SpriteFactory spriteFactory) {
		this.gameModel = gameModel;
		this.fontFactory = fontFactory;
		this.spriteFactory = spriteFactory;
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
		Player player = gameModel.getPlayer();

		g.setFont(fontFactory.getLargeFont());
		FontMetrics fm = g.getFontMetrics();
		String scoreStr = String.valueOf(player.getScore());
		int offset = fm.stringWidth(scoreStr) + 1;

		g.setColor(Color.CYAN);
		g.drawString(scoreStr, ARENA_HEADER_P1_ANCHOR_PX - offset, fm.getHeight());
	}

	private void paintLives(Graphics g) {
		Player player = gameModel.getPlayer();
		int numLives = player.getLives();
		for (int i = 0; i < numLives; i++) {
			int lifeX = ARENA_HEADER_P1_ANCHOR_PX + i * 8;
			g.drawImage(spriteFactory.getLexi(), lifeX, -2, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX, null);
		}
	}
}
