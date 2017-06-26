package bb.common.view.actor;

import bb.common.model.TextModel;
import bb.common.view.FontFactory;
import bb.framework.model.actor.ActorModel;
import bb.framework.view.actor.ActorView;

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

	private TextModel model;
	private FontFactory fontFactory;

	public TextView(TextModel model, FontFactory fontFactory) {
		this.model = model;
		this.fontFactory = fontFactory;
	}

	@Override
	public ActorModel getModel() {
		return model;
	}

	@Override
	public void paint(Graphics g) {

		// TODO Wouldn't it make more sense to precompute the lines? [WLW]
		List<String> lines = model.toLines();

		g.translate(model.getX(), model.getY());
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

		g.translate(-model.getX(), -model.getY());
	}
}
