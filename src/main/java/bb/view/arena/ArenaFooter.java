package bb.view.arena;

import bb.model.GameModel;
import bb.model.Player;
import bb.view.FontFactory;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;

import static bb.BBConfig.*;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaFooter extends JComponent {
	private GameModel gameModel;
	private FontFactory fontFactory;

	public ArenaFooter(GameModel gameModel, FontFactory fontFactory) {
		this.gameModel = gameModel;
		this.fontFactory = fontFactory;
	}

	@Override
	public Dimension getPreferredSize() {
		return K_ARENA_FOOTER_SIZE_PX;
	}

	@Override
	public void paint(Graphics g) {
		Player player = gameModel.getPlayer();
		g.setFont(fontFactory.getSmallFont());
		FontMetrics fm = g.getFontMetrics();
		g.setColor(Color.RED);
		g.drawString("LEVEL", K_ARENA_FOOTER_LEVEL_LABEL_OFFSET_PX, fm.getHeight());
		g.setColor(Color.CYAN);
		g.drawString(String.valueOf(player.getLevel()), K_ARENA_FOOTER_LEVEL_VALUE_OFFSET_PX, fm.getHeight());
	}
}
