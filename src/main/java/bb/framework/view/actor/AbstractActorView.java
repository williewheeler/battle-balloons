package bb.framework.view.actor;

import bb.framework.model.actor.ActorModel;
import bb.framework.util.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static bb.BBConfig.SPRITE_HEIGHT_PX;
import static bb.BBConfig.SPRITE_WIDTH_PX;

/**
 * Created by willie on 6/24/17.
 */
public abstract class AbstractActorView implements ActorView {
	private ActorModel model;
	private int scale;

	public AbstractActorView(ActorModel model, int scale) {
		Assert.notNull(model, "model can't be null");
		this.model = model;
		this.scale = scale;
	}

	@Override
	public ActorModel getModel() {
		return model;
	}

	@Override
	public void paint(Graphics g) {
		BufferedImage sprite = getCurrentSprite();
		int adjX = model.getX() - SPRITE_WIDTH_PX;
		int adjY = model.getY() - SPRITE_HEIGHT_PX;
		int width = scale * SPRITE_WIDTH_PX;
		int height = scale * SPRITE_HEIGHT_PX;
		g.drawImage(sprite, adjX, adjY, width, height, null);
	}

	public abstract BufferedImage getCurrentSprite();
}
