package bb.common.resource;

import bb.framework.resource.ImageLoader;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static bb.common.BBConfig.SPRITE_HEIGHT_PX;
import static bb.common.BBConfig.SPRITE_WIDTH_PX;

/**
 * Created by willie on 6/4/17.
 */
public class SpriteFactory {
	private static final int PLAYER_ENTERING_DURATION = 20;
	private static final int PLAYER_EXITING_DURATION = 40;

	private static final int JUDO_ENTERING_DURATION = 20;
	private static final int JUDO_EXITING_DURATION = 5;

	// row = color
	// col = rotation
	private BufferedImage[][] bigBalloons;

	private BufferedImage[] lexiWalking;
	private BufferedImage[] lexiEntering;
	private BufferedImage[] lexiExiting;
	private BufferedImage[] lexiBlinking;
	private BufferedImage[] lexiWaving;

	private BufferedImage[] judoWalking;
	private BufferedImage[] judoEntering;
	private BufferedImage[] judoExiting;

	private BufferedImage[] bullyWalking;

	private BufferedImage[] dogWalking;

	public SpriteFactory(ImageLoader imageLoader) {
		BufferedImage sheet = imageLoader.loadImage("images/bb-sprites.png");

		this.bigBalloons = buildBigBalloonSprites(sheet);

		this.lexiWalking = buildWalkingSprites(sheet, 0);
		this.lexiEntering = spaghettify(lexiWalking[4], PLAYER_ENTERING_DURATION);
		this.lexiExiting = spaghettify(lexiWalking[4], PLAYER_EXITING_DURATION);
		this.lexiBlinking = buildLexiBlinking(sheet);
		this.lexiWaving = buildLexiWaving(sheet);
		
		this.judoWalking = buildWalkingSprites(sheet, 1);
		this.judoEntering = spaghettify(judoWalking[4], JUDO_ENTERING_DURATION);
		this.judoExiting = spaghettify(judoWalking[4], JUDO_EXITING_DURATION);

		this.bullyWalking = buildWalkingSprites(sheet, 2);

		this.dogWalking = buildWalkingSprites(sheet, 3);
	}

	public BufferedImage[][] getBigBalloons() {
		return bigBalloons;
	}

	public BufferedImage[] getLexiWalking() {
		return lexiWalking;
	}
	
	public BufferedImage[] getLexiEntering() {
		return lexiEntering;
	}
	
	public BufferedImage[] getLexiExiting() {
		return lexiExiting;
	}
	
	public BufferedImage getLexiLife() {
		return lexiWalking[4];
	}

	public BufferedImage[] getLexiBlinking() {
		return lexiBlinking;
	}

	public BufferedImage[] getLexiWaving() {
		return lexiWaving;
	}

	public BufferedImage[] getJudoWalking() {
		return judoWalking;
	}

	public BufferedImage[] getJudoEntering() {
		return judoEntering;
	}

	public BufferedImage[] getJudoExiting() {
		return judoExiting;
	}

	public BufferedImage[] getBullyWalking() {
		return bullyWalking;
	}

	public BufferedImage[] getDogWalking() {
		return dogWalking;
	}

	private BufferedImage[] buildWalkingSprites(BufferedImage sheet, int row) {
		BufferedImage[] sprites = new BufferedImage[16];
		for (int i = 0, col = 0; i < 16; i++) {
			if (i % 4 == 2) {
				sprites[i] = sprites[i - 2];
			} else {
				sprites[i] = buildSprite(sheet, row, col);
				col++;
			}
		}
		return sprites;
	}

	private BufferedImage[] buildLexiBlinking(BufferedImage sheet) {
		return new BufferedImage[] {
				lexiWalking[4],
				buildSprite(sheet, 0, 13)
		};
	}

	private BufferedImage[] buildLexiWaving(BufferedImage sheet) {
		return new BufferedImage[] {
				buildSprite(sheet, 0, 14),
				buildSprite(sheet, 0, 15)
		};
	}

	private BufferedImage[] spaghettify(BufferedImage sprite, int numFrames) {
		BufferedImage[] result = new BufferedImage[numFrames];
		int width = sprite.getWidth();
		int height = sprite.getHeight();
		
		// i is the size of the gap between slices
		for (int i = 0; i < numFrames; i++) {
			int adjHeight = height + i * (height - 1);
			result[i] = new BufferedImage(width, adjHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = result[i].createGraphics();
			for (int j = 0; j < height; j++) {
				BufferedImage slice = sprite.getSubimage(0, j, width, 1);
				g2.drawImage(slice, 0, (i + 1) * j, null);
			}
		}
		
		return result;
	}

	private BufferedImage[][] buildBigBalloonSprites(BufferedImage sheet) {
		final int numColors = 7;
		final int numRotations = 4;

		BufferedImage[][] sprites = new BufferedImage[numColors][numRotations];
		for (int i = 0; i < numColors; i++) {
			for (int j = 0; j < numRotations; j++) {
				// Balloons start at column 20
				int col = 20 + j;
				sprites[i][j] = buildSprite(sheet, i, col);
			}
		}
		return sprites;
	}

	private BufferedImage buildSprite(BufferedImage sheet, int row, int col) {
		int x = col * SPRITE_WIDTH_PX;
		int y = row * SPRITE_HEIGHT_PX;
		return sheet.getSubimage(x, y, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX);
	}
}
