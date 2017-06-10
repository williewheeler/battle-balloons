package bb.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static bb.BBConfig.*;

/**
 * Created by willie on 6/4/17.
 */
public class SpriteFactory {
	private BufferedImage[] lexi;

	public SpriteFactory() {
		BufferedImage sheet = loadSheet();
		this.lexi = buildCharacterSprites(sheet, 0);
	}

	public BufferedImage[] getLexi() {
		return lexi;
	}

	public BufferedImage getLexiLife() {
		return lexi[4];
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
}
