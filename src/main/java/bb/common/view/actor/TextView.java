package bb.common.view.actor;

import bb.common.model.TextModel;
import bb.common.view.FontFactory;
import bb.framework.model.actor.ActorModel;
import bb.framework.view.actor.ActorView;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Created by willie on 6/25/17.
 */
public class TextView implements ActorView {
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
		g.setFont(fontFactory.getSmallFont());
		g.setColor(Color.YELLOW);
		g.drawString(model.getText(), model.getX(), model.getY());
	}
}
