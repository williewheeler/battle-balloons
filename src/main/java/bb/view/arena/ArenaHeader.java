package bb.view.arena;

import bb.model.GameModel;
import bb.view.FontFactory;
import bb.view.GraphicsUtil;
import bb.view.SpriteFactory;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;

import static bb.BBConfig.ARENA_HEADER_LIVES_OFFSET_PX;
import static bb.BBConfig.K_ARENA_HEADER_SIZE_PX;
import static bb.BBConfig.K_ARENA_HEADER_TEXT_OFFSET_PX;

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
		return K_ARENA_HEADER_SIZE_PX;
	}

	@Override
	public void paint(Graphics g) {
		paintScore(g);
		paintLives(g);
	}

	private void paintScore(Graphics g) {
		g.setFont(fontFactory.getLargeFont());
		FontMetrics fm = g.getFontMetrics();
		String scoreStr = String.valueOf(gameModel.getScore());

		g.setColor(Color.CYAN);
		g.drawString(scoreStr, K_ARENA_HEADER_TEXT_OFFSET_PX, fm.getHeight());
	}

	private void paintLives(Graphics g) {
		int numLives = gameModel.getLives();
		for (int i = 0; i < numLives; i++) {
			int lifeX = ARENA_HEADER_LIVES_OFFSET_PX - i * 8;
			GraphicsUtil.drawSprite(g, spriteFactory.getLexi(), lifeX, 7);
		}
	}
}
