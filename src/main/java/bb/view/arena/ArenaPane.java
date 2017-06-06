package bb.view.arena;

import bb.model.GameModel;
import bb.model.Player;
import bb.view.GraphicsUtil;
import bb.view.SpriteFactory;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static bb.BBConfig.*;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaPane extends JComponent {
	private GameModel gameModel;
	private SpriteFactory spriteFactory;

	public ArenaPane(GameModel gameModel, SpriteFactory spriteFactory) {
		this.gameModel = gameModel;
		this.spriteFactory = spriteFactory;
	}

	@Override
	public Dimension getPreferredSize() {
		return K_ARENA_PANE_SIZE_PX;
	}

	@Override
	public void paint(Graphics g) {
		g.translate(K_ARENA_MARGIN_LEFT_RIGHT_PX, 0);
		doPaintBorder(g);
		g.translate(K_ARENA_BORDER_SIZE_PX, K_ARENA_BORDER_SIZE_PX);
		paintPlayer(g);
		g.translate(-K_ARENA_BORDER_SIZE_PX, -K_ARENA_BORDER_SIZE_PX);
		g.translate(-K_ARENA_MARGIN_LEFT_RIGHT_PX, 0);
	}

	private void doPaintBorder(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, K_ARENA_OUTER_WIDTH_PX, K_ARENA_OUTER_HEIGHT_PX);
		g.setColor(Color.BLACK);
		g.fillRect(K_ARENA_BORDER_SIZE_PX, K_ARENA_BORDER_SIZE_PX, K_ARENA_INNER_WIDTH_PX, K_ARENA_INNER_HEIGHT_PX);
	}

	private void paintPlayer(Graphics g) {
		BufferedImage sprite = spriteFactory.getLexi();
		Player player = gameModel.getPlayer();
		GraphicsUtil.drawSprite(g, sprite, player.getX(), player.getY());
	}
}
