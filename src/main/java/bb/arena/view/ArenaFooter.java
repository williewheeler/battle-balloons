package bb.arena.view;

import bb.arena.model.ArenaModel;
import bb.arena.model.Player;
import bb.common.view.factory.FontFactory;

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
	private ArenaModel arenaModel;
	private FontFactory fontFactory;

	public ArenaFooter(ArenaModel arenaModel, FontFactory fontFactory) {
		this.arenaModel = arenaModel;
		this.fontFactory = fontFactory;
	}

	@Override
	public Dimension getPreferredSize() {
		return ARENA_FOOTER_SIZE_PX;
	}

	@Override
	public void paint(Graphics g) {
		Player player = arenaModel.getPlayer();
		g.setFont(fontFactory.getSmallFont());
		FontMetrics fm = g.getFontMetrics();
		g.setColor(Color.RED);
		g.drawString("LEVEL", ARENA_FOOTER_LEVEL_LABEL_OFFSET_PX, fm.getHeight());
		g.setColor(Color.CYAN);
		g.drawString(String.valueOf(player.getLevel()), ARENA_FOOTER_LEVEL_VALUE_OFFSET_PX, fm.getHeight());
	}
}
