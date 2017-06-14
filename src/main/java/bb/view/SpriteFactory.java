package bb.view;

import bb.model.Player;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static bb.BBConfig.*;

/**
 * Created by willie on 6/4/17.
 */
public class SpriteFactory {
	private BufferedImage[] lexi;
	private BufferedImage[] lexiEntering;
	private BufferedImage[] lexiExiting;
	private BufferedImage[] judo;

	public SpriteFactory() {
		BufferedImage sheet = loadSheet();
		
		this.lexi = buildCharacterSprites(sheet, 0);
		this.lexiEntering = spaghettify(lexi[4], Player.ENTERING_DURATION);
		this.lexiExiting = spaghettify(lexi[4], Player.EXITING_DURATION);
		
		this.judo = buildCharacterSprites(sheet, 1);
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
	
	public BufferedImage[] getJudo() {
		return judo;
	}

	private BufferedImage loadSheet() {
		try {
			InputStream in = ClassLoader.getSystemResourceAsStream(SPRITE_SHEET_FILENAME);
			return ImageIO.read(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private BufferedImage[] buildCharacterSprites(BufferedImage sheet, int row) {
		BufferedImage[] sprites = new BufferedImage[16];
		for (int i = 0, col = 0; i < 16; i++) {
			if (i % 4 == 2) {
				sprites[i] = sprites[i - 2];
			} else {
				int x = col * SPRITE_WIDTH_PX;
				int y = row * SPRITE_HEIGHT_PX;
				sprites[i] = sheet.getSubimage(x, y, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX);
				col++;
			}
		}
		return sprites;
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
				g2.drawImage(slice, 0, i * j, null);
			}
		}
		
		return result;
	}
}
