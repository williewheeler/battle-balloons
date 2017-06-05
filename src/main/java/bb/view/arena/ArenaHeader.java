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
		g.setFont(fontFactory.getLargeFont());
		FontMetrics fm = g.getFontMetrics();
		g.setColor(Color.CYAN);
		g.drawString(String.valueOf(gameModel.getScore()), K_ARENA_HEADER_TEXT_OFFSET_PX, fm.getHeight());

		GraphicsUtil.drawSprite(g, spriteFactory.getLexi(), 65, 7);
		GraphicsUtil.drawSprite(g, spriteFactory.getLexi(), 75, 7);
		GraphicsUtil.drawSprite(g, spriteFactory.getLexi(), 85, 7);
	}
}
