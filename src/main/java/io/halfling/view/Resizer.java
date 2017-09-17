package io.halfling.view;

import io.halfling.core.Assert;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * See https://stackoverflow.com/questions/44363464/scale-tiny-low-resolution-app-to-larger-screen-size
 *
 * Created by willie on 6/7/17.
 */
public class Resizer extends JPanel {
	private final Dimension prefSize;
	private final AffineTransform scaleXform;

	public Resizer(Dimension origScrSize, int scaleBy) {
		Assert.notNull(origScrSize, "origScrSize can't be null");
		Assert.isStrictlyPositive(scaleBy, "scaleBy must be strictly positive");

		int prefWidth = scaleBy * origScrSize.width;
		int prefHeight = scaleBy * origScrSize.height;
		this.prefSize = new Dimension(prefWidth, prefHeight);
		this.scaleXform = AffineTransform.getScaleInstance(scaleBy, scaleBy);

		setBackground(Color.BLACK);
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
	}

	@Override
	public Dimension getPreferredSize() {
		return prefSize;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setTransform(scaleXform);

		// TODO Hm, why doesn't paint(g2) work here? [WLW]
//		super.paint(g2);
		super.paintChildren(g2);
	}
}
