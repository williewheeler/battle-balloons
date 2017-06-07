package bb.view;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static bb.BBConfig.*;

/**
 * See https://stackoverflow.com/questions/44363464/scale-tiny-low-resolution-app-to-larger-screen-size
 * 
 * Created by willie on 6/7/17.
 */
public class Resizer extends JPanel {
	private static final int K = 3;
	private static final Dimension K_SCREEN_SIZE_PX = new Dimension(K * SCREEN_WIDTH_PX, K * SCREEN_HEIGHT_PX);
	private static final AffineTransform SCALE_TRANSFORM;

	static {
		SCALE_TRANSFORM = new AffineTransform();
		SCALE_TRANSFORM.scale(K, K);
	}

	public Resizer(JComponent component) {

		// This basically shrinkwraps the child component.
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		add(component);
	}

	@Override
	public Dimension getPreferredSize() {
		return K_SCREEN_SIZE_PX;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		BufferedImage image = getImage();
		System.out.println(image);
		g2.setTransform(SCALE_TRANSFORM);
		g2.drawImage(image, 0, 0, SCREEN_WIDTH_PX, SCREEN_HEIGHT_PX, null);
	}

	private BufferedImage getImage() {
		BufferedImage image = new BufferedImage(SCREEN_WIDTH_PX, SCREEN_HEIGHT_PX, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		super.paintComponent(g2);
		g2.dispose();
		return image;
	}
}
