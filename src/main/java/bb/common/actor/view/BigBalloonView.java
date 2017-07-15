package bb.common.actor.view;

import bb.common.actor.model.BigBalloon;
import retroge.actor.Actor;
import retroge.util.Assert;
import retroge.actor.ActorView;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static bb.common.BBConfig.SPRITE_HEIGHT_PX;
import static bb.common.BBConfig.SPRITE_WIDTH_PX;

/**
 * Created by willie on 6/30/17.
 */
public class BigBalloonView implements ActorView {
	private BufferedImage[][] balloonSprites;

	public BigBalloonView(BufferedImage[][] balloonSprites) {
		Assert.notNull(balloonSprites, "balloonSprites can't be null");
		this.balloonSprites = balloonSprites;
	}

	@Override
	public void paint(Graphics g, Actor actor) {
		final BigBalloon bigBalloon = (BigBalloon) actor;
		final int halfWidth = SPRITE_WIDTH_PX / 2;
		final int halfHeight = SPRITE_HEIGHT_PX / 2;
		final int xOffset = bigBalloon.getX() - halfWidth;
		final int yOffset = bigBalloon.getY() - halfHeight;
		final int colorIndex = bigBalloon.getColorIndex();
		final int rotIndex = bigBalloon.getRotationIndex();
		final BufferedImage sprite = balloonSprites[colorIndex][rotIndex];

		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}
}
