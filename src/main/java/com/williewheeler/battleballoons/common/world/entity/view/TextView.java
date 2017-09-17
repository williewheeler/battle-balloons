package com.williewheeler.battleballoons.common.world.entity.view;

import com.williewheeler.battleballoons.common.world.entity.model.Text;
import com.williewheeler.battleballoons.common.view.resource.FontFactory;
import io.halfling.world.entity.model.Actor;
import io.halfling.core.Assert;
import io.halfling.world.entity.view.ActorView;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 * Created by willie on 6/25/17.
 */
public class TextView implements ActorView {
	private static final Color[] COLORS = {
//			Color.RED,
			Color.YELLOW,
			Color.GREEN,
			Color.CYAN,
//			Color.BLUE,
			Color.MAGENTA,
			Color.WHITE
	};

	private FontFactory fontFactory;

	public TextView(FontFactory fontFactory) {
		Assert.notNull(fontFactory, "fontFactory can't be null");
		this.fontFactory = fontFactory;
	}

	@Override
	public void paint(Graphics g, Actor actor) {
		final Text text = (Text) actor;

		// TODO Wouldn't it make more sense to precompute the lines? [WLW]
		List<String> lines = text.toLines();

		g.translate(text.getX(), text.getY());
		g.setFont(fontFactory.getSmallFont());
		g.setColor(Color.YELLOW);

//		if (counter < backstoryArr.length + 5 * FRAMES_PER_SECOND) {
//			g.setColor(Color.YELLOW);
//		} else {
//			int colorIndex = (counter / 5) % COLORS.length;
//			g.setColor(COLORS[colorIndex]);
//		}

		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			g.drawString(line, 0, i * 12);
		}

		g.translate(-text.getX(), -text.getY());
	}
}
