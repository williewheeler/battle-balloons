package bb.common.view.actor;

import bb.common.model.BigBalloonModel;
import bb.framework.model.ActorModel;
import bb.framework.util.Assert;
import bb.framework.view.actor.ActorView;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static bb.BBConfig.SPRITE_HEIGHT_PX;
import static bb.BBConfig.SPRITE_WIDTH_PX;

/**
 * Created by willie on 6/30/17.
 */
public class BigBalloonView implements ActorView {
	private BigBalloonModel model;
	private BufferedImage[] balloonSprites;

	public BigBalloonView(BigBalloonModel model, BufferedImage[] balloonSprites) {
		Assert.notNull(model, "model can't be null");
		Assert.notNull(balloonSprites, "balloonSprites can't be null");
		this.model = model;
		this.balloonSprites = balloonSprites;
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
		final int rot = model.getRotation();
		final BufferedImage sprite = balloonSprites[rot];

		g.translate(-halfWidth, -halfHeight);
		g.drawImage(sprite, x, y, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX, null);
		g.translate(halfWidth, halfHeight);
	}
}
