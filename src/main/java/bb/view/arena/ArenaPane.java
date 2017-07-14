package bb.view.arena;

import bb.model.GameModel;
import bb.model.Player;
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
		return ARENA_PANE_SIZE_PX;
	}

	@Override
	public void paint(Graphics g) {
		g.translate(ARENA_MARGIN_LEFT_RIGHT_PX, 0);
		doPaintBorder(g);
		g.translate(ARENA_BORDER_SIZE_PX, ARENA_BORDER_SIZE_PX);
		g.setClip(0, 0, ARENA_INNER_WIDTH_PX, ARENA_INNER_HEIGHT_PX);
		paintPlayer(g);
		g.translate(-ARENA_BORDER_SIZE_PX, -ARENA_BORDER_SIZE_PX);
		g.translate(-ARENA_MARGIN_LEFT_RIGHT_PX, 0);
	}

	private void doPaintBorder(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, ARENA_OUTER_WIDTH_PX, ARENA_OUTER_HEIGHT_PX);
		g.setColor(Color.BLACK);
		g.fillRect(ARENA_BORDER_SIZE_PX, ARENA_BORDER_SIZE_PX, ARENA_INNER_WIDTH_PX, ARENA_INNER_HEIGHT_PX);
	}

	private void paintPlayer(Graphics g) {
		Player player = gameModel.getPlayer();
		BufferedImage[] sprites = spriteFactory.getLexi();
		BufferedImage sprite = SpriteUtil.getCurrentSprite(player, sprites);
		int adjX = player.getX() - SPRITE_WIDTH_PX / 2;
		int adjY = player.getY() - SPRITE_HEIGHT_PX / 2;
		g.drawImage(sprite, adjX, adjY, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX, null);
	}
}
