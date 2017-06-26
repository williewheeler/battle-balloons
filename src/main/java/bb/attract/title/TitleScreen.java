package bb.attract.title;

import bb.common.view.FontFactory;
import bb.common.view.SpriteFactory;
import bb.framework.view.AttractScreen;
import bb.framework.view.ImageLoader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import static bb.BBConfig.*;

/**
 * Created by willie on 6/17/17.
 */
public class TitleScreen extends AttractScreen {
	private static final int TTL = 15 * FRAMES_PER_SECOND;

	private TitleModel titleModel;
	private FontFactory fontFactory;
	private ImageLoader imageLoader;
	private SpriteFactory spriteFactory;

	private BufferedImage titleImage;
	private BufferedImage[][] balloonSprites;

	public TitleScreen(
			FontFactory fontFactory,
			ImageLoader imageLoader,
			SpriteFactory spriteFactory) {

		super(TTL);
		this.titleModel = new TitleModel();
		this.fontFactory = fontFactory;
		this.imageLoader = imageLoader;
		this.spriteFactory = spriteFactory;

		this.titleImage = imageLoader.loadImage("images/bb-title.png");
		this.balloonSprites = spriteFactory.getBalloons();
	}

	public TitleModel getModel() {
		return titleModel;
	}

	// TODO Get rid of this once TitleModel is gone.
	@Deprecated
	@Override
	public void update() {
		super.update();
		titleModel.update();
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
