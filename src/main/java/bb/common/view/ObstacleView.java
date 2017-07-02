package bb.common.view;

import bb.common.model.ObstacleModel;
import bb.framework.model.ActorModel;
import bb.framework.util.Assert;
import bb.framework.view.ActorView;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Created by willie on 7/1/17.
 */
public class ObstacleView implements ActorView {
	private ObstacleModel model;

	public ObstacleView(ObstacleModel model) {
		Assert.notNull(model, "model can't be null");
		this.model = model;
	}

	@Override
	public ActorModel getModel() {
		return model;
	}

	@Override
	public void paint(Graphics g) {
		final int halfWidth = model.getWidth() / 2;
		final int halfHeight = model.getHeight() / 2;
		final int xOffset = model.getX() - halfWidth;
		final int yOffset = model.getY() - halfHeight;

		g.translate(xOffset, yOffset);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, model.getWidth(), model.getHeight());
		g.translate(-xOffset, -yOffset);
	}
}
