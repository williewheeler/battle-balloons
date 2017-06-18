package bb.core.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by willie on 6/17/17.
 */
public class ImageFactory {

	public BufferedImage loadImage(String path) {
		try {
			InputStream in = ClassLoader.getSystemResourceAsStream(path);
			return ImageIO.read(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
