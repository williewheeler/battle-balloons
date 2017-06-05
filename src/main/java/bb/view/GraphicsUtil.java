package bb.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static bb.BBConfig.*;

/**
 * Created by willie on 6/5/17.
 */
public class GraphicsUtil {
	private static final int K_HALF_SPRITE_WIDTH_PX = K_SPRITE_WIDTH_PX / 2;
	private static final int K_HALF_SPRITE_HEIGHT_PX = K_SPRITE_HEIGHT_PX / 2;

	public static void drawSprite(Graphics g, BufferedImage sprite, int x, int y) {
		int adjX = K * x - K_HALF_SPRITE_WIDTH_PX;
		int adjY = K * y - K_HALF_SPRITE_HEIGHT_PX;
		g.drawImage(sprite, adjX, adjY, K_SPRITE_WIDTH_PX, K_SPRITE_HEIGHT_PX, null);
	}
}
