package bb.core.view;

import bb.arena.model.Judo;
import bb.arena.model.Player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static bb.BBConfig.SPRITE_HEIGHT_PX;
import static bb.BBConfig.SPRITE_WIDTH_PX;

/**
 * Created by willie on 6/4/17.
 */
public class SpriteFactory {
	private BufferedImage[] lexi;
	private BufferedImage[] lexiEntering;
	private BufferedImage[] lexiExiting;
	private BufferedImage[] lexiBlinking;

	private BufferedImage[] judo;
	private BufferedImage[] judoEntering;
	private BufferedImage[] judoExiting;

	// row = color
	// col = rotation
	private BufferedImage[][] balloons;

	public SpriteFactory(ImageFactory imageFactory) {
		BufferedImage sheet = imageFactory.loadImage("images/bb-sprites.png");
		
		this.lexi = buildCharacterSprites(sheet, 0);
		this.lexiEntering = spaghettify(lexi[4], Player.ENTERING_DURATION);
		this.lexiExiting = spaghettify(lexi[4], Player.EXITING_DURATION);
		this.lexiBlinking = buildLexiBlinking(sheet);
		
		this.judo = buildCharacterSprites(sheet, 1);
		this.judoEntering = spaghettify(judo[4], Judo.ENTERING_DURATION);
		this.judoExiting = spaghettify(judo[4], Judo.EXITING_DURATION);

		this.balloons = buildBalloonSprites(sheet);
	}

	public BufferedImage[] getLexi() {
		return lexi;
	}
	
	public BufferedImage[] getLexiEntering() {
		return lexiEntering;
	}
	
	public BufferedImage[] getLexiExiting() {
		return lexiExiting;
	}
	
	public BufferedImage getLexiLife() {
		return lexi[4];
	}

	public BufferedImage[] getLexiBlinking() {
		return lexiBlinking;
	}

	public BufferedImage[] getJudo() {
		return judo;
	}

	public BufferedImage[] getJudoEntering() {
		return judoEntering;
	}

	public BufferedImage[] getJudoExiting() {
		return judoExiting;
	}

	public BufferedImage[][] getBalloons() {
		return balloons;
	}

	private BufferedImage[] buildCharacterSprites(BufferedImage sheet, int row) {
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
				lexi[4],
				buildSprite(sheet, 0, 13)
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

	private BufferedImage[][] buildBalloonSprites(BufferedImage sheet) {
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
