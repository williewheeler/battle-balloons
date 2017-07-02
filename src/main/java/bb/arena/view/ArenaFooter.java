package bb.arena.view;

import bb.common.BBContext;
import bb.arena.model.ArenaModel;
import bb.arena.model.Player;
import bb.common.view.FontFactory;
import bb.framework.util.Assert;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;

import static bb.common.BBConfig.*;

/**
 * Created by willie on 6/4/17.
 */
public class ArenaFooter extends JComponent {
	private BBContext context;
	private ArenaModel model;

	public ArenaFooter(BBContext context, ArenaModel model) {
		Assert.notNull(context, "context can't be null");
		Assert.notNull(model, "model can't be null");
		this.context = context;
		this.model = model;
	}

	@Override
	public Dimension getPreferredSize() {
		return ARENA_FOOTER_SIZE_PX;
	}

	@Override
	public void paint(Graphics g) {
		FontFactory fontFactory = context.getFontFactory();

		Player player = model.getPlayer();
		g.setFont(fontFactory.getSmallFont());
		FontMetrics fm = g.getFontMetrics();
		g.setColor(Color.RED);
		g.drawString("LEVEL", ARENA_FOOTER_LEVEL_LABEL_OFFSET_PX, fm.getHeight());
		g.setColor(Color.CYAN);
		g.drawString(String.valueOf(player.getLevel()), ARENA_FOOTER_LEVEL_VALUE_OFFSET_PX, fm.getHeight());
	}
}
