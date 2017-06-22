package bb.attract.title;

import bb.core.view.FontFactory;
import bb.core.view.GameScreen;
import bb.core.view.ImageFactory;
import bb.core.view.SpriteFactory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import static bb.BBConfig.SPRITE_HEIGHT_PX;
import static bb.BBConfig.SPRITE_WIDTH_PX;

/**
 * Created by willie on 6/17/17.
 */
public class TitleScreen extends GameScreen {
	private TitleModel titleModel;
	private FontFactory fontFactory;
	private ImageFactory imageFactory;
	private SpriteFactory spriteFactory;

	private BufferedImage titleImage;
	private BufferedImage[][] balloonSprites;

	public TitleScreen(
			TitleModel titleModel,
			FontFactory fontFactory,
			ImageFactory imageFactory,
			SpriteFactory spriteFactory) {

		this.titleModel = titleModel;
		this.fontFactory = fontFactory;
		this.imageFactory = imageFactory;
		this.spriteFactory = spriteFactory;

		this.titleImage = imageFactory.loadImage("images/bb-title.png");
		this.balloonSprites = spriteFactory.getBalloons();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintText(g);
		paintBalloons(g);
	}

	private void paintText(Graphics g) {
		g.setFont(fontFactory.getSmallFont());
		g.setColor(Color.GREEN);
		g.drawString("GET READY FOR", 55, 60);

		g.drawImage(titleImage, 50, 80, null);

		g.setFont(fontFactory.getLargeFont());
		g.setColor(Color.YELLOW);
		g.drawString("PRESS [1] PLAYER OR [2] PLAYERS", 55, 190);
	}

	private void paintBalloons(Graphics g) {
		List<BigBalloon> balloons = titleModel.getBalloons();
		balloons.forEach(balloon -> paintBalloon(g, balloon));
	}

	private void paintBalloon(Graphics g, BigBalloon balloon) {
		int xOffset = -SPRITE_WIDTH_PX / 2;
		int yOffset = -SPRITE_HEIGHT_PX / 2;
		int x = balloon.getX();
		int y = balloon.getY();
		g.translate(xOffset, yOffset);
		BufferedImage sprite = getBalloonSprite(balloon);
		g.drawImage(sprite, x, y, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}

	private BufferedImage getBalloonSprite(BigBalloon balloon) {
		int colorIndex = -1;
		switch (balloon.getColor()) {
			case RED:
				colorIndex = 0;
				break;
			case YELLOW:
				colorIndex = 1;
				break;
			case GREEN:
				colorIndex = 2;
				break;
			case CYAN:
				colorIndex = 3;
				break;
			case BLUE:
				colorIndex = 4;
				break;
			case MAGENTA:
				colorIndex = 5;
				break;
			case WHITE:
				colorIndex = 6;
				break;
		}
		int rotIndex = balloon.getRotation();
		return balloonSprites[colorIndex][rotIndex];
	}
}
