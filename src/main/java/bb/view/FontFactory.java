package bb.view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

import static bb.BBConfig.K_LARGE_FONT_PT;
import static bb.BBConfig.K_SMALL_FONT_PT;

/**
 * Created by willie on 6/4/17.
 */
public class FontFactory {
	private static final String FONT_NAME = "Robotron";

	private Font largeFont;
	private Font smallFont;

	public FontFactory() {
		registerFont();
		this.largeFont = new Font(FONT_NAME, Font.PLAIN, K_LARGE_FONT_PT);
		this.smallFont = new Font(FONT_NAME, Font.PLAIN, K_SMALL_FONT_PT);
	}

	public Font getLargeFont() {
		return largeFont;
	}

	public Font getSmallFont() {
		return smallFont;
	}

	private void registerFont() {
		InputStream fontIS = ClassLoader.getSystemResourceAsStream("Robotron.ttf");
		try {
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			env.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontIS));
		} catch (FontFormatException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}
