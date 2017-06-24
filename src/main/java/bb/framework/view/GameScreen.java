package bb.framework.view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import static bb.BBConfig.SCREEN_SIZE_PX;

/**
 * Created by willie on 6/19/17.
 */
public abstract class GameScreen extends JPanel {

	public GameScreen() {
		setBackground(Color.BLACK);
	}

	@Override
	public Dimension getPreferredSize() {
		return SCREEN_SIZE_PX;
	}
}
