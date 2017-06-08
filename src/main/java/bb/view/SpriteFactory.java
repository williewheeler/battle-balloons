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
	private BufferedImage lexi;

	public SpriteFactory() {
		BufferedImage sheet = loadSheet();
		this.lexi = getSpriteAt(sheet, 0, 3);
	}

	public BufferedImage getLexi() {
		return lexi;
	}

	private BufferedImage loadSheet() {
		try {
			InputStream in = ClassLoader.getSystemResourceAsStream(SPRITE_SHEET_FILENAME);
			return ImageIO.read(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private BufferedImage getSpriteAt(BufferedImage sheet, int row, int col) {
		int x = col * SPRITE_WIDTH_PX;
		int y = row * SPRITE_HEIGHT_PX;
		return sheet.getSubimage(x, y, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX);
	}
}
