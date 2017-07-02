package bb.framework.view;

import bb.framework.model.ActorModel;
import bb.framework.model.BasicActorModel;
import bb.framework.util.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static bb.common.BBConfig.SPRITE_HEIGHT_PX;
import static bb.common.BBConfig.SPRITE_WIDTH_PX;

/**
 * Created by willie on 7/1/17.
 */
public class ImageView implements ActorView {
	private BasicActorModel model;
	private BufferedImage image;

	public ImageView(BasicActorModel model, BufferedImage image) {
		Assert.notNull(model, "model can't be null");
		Assert.notNull(image, "image can't be null");
		this.model = model;
		this.image = image;
	}

	@Override
	public ActorModel getModel() {
		return model;
	}

	@Override
	public void paint(Graphics g) {
		final int halfWidth = SPRITE_WIDTH_PX / 2;
		final int halfHeight = SPRITE_HEIGHT_PX / 2;
		final int x = model.getX();
		final int y = model.getY();
		final int width = model.getWidth();
		final int height = model.getHeight();

		g.translate(-halfWidth, -halfHeight);
		g.drawImage(image, x, y, width, height, null);
		g.translate(halfWidth, halfHeight);
	}
}
