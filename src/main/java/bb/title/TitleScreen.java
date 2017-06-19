package bb.title;

import bb.core.view.FontFactory;
import bb.core.view.ImageFactory;
import bb.core.view.SpriteFactory;
import bb.title.model.BigBalloon;
import bb.title.model.TitleModel;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import static bb.BBConfig.*;

/**
 * Created by willie on 6/17/17.
 */
public class TitleScreen extends JComponent {
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
	public Dimension getPreferredSize() {
		return SCREEN_SIZE_PX;
	}

	@Override
	public void paint(Graphics g) {
		paintBackground(g);
		paintText(g);
		paintBalloons(g);
	}

	private void paintBackground(Graphics g) {
		Dimension screenSize = getSize();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenSize.width, screenSize.height);
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
			case PURPLE:
				colorIndex = 4;
				break;
		}
		int rotIndex = balloon.getRotation();
		return balloonSprites[colorIndex][rotIndex];
	}
}
