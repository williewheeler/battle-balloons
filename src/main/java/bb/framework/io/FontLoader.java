package bb.framework.io;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by willie on 6/24/17.
 */
public class FontLoader {

	public void loadFont(String path) {
		InputStream fontIS = ClassLoader.getSystemResourceAsStream(path);
		try {
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			env.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontIS));
		} catch (FontFormatException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}
