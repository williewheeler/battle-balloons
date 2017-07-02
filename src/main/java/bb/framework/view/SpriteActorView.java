package bb.framework.view;

import bb.framework.model.ActorModel;
import bb.framework.util.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static bb.common.BBConfig.SPRITE_HEIGHT_PX;
import static bb.common.BBConfig.SPRITE_WIDTH_PX;

/**
 * Created by willie on 6/24/17.
 */
public abstract class SpriteActorView implements ActorView {
	private ActorModel model;

	public SpriteActorView(ActorModel model) {
		Assert.notNull(model, "model can't be null");
		this.model = model;
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
		final BufferedImage sprite = getCurrentSprite();

		g.translate(-halfWidth, -halfHeight);
		g.drawImage(sprite, x, y, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX, null);
		g.translate(halfWidth, halfHeight);
	}

	public abstract BufferedImage getCurrentSprite();
}
