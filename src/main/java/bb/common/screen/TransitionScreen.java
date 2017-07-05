package bb.common.screen;

import bb.common.BBConfig;
import bb.common.BBContext;
import bb.framework.event.ScreenEvent;
import bb.framework.screen.AbstractScreen;
import bb.framework.util.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Created by willie on 7/3/17.
 */
public class TransitionScreen extends AbstractScreen {
	private static final Logger log = LoggerFactory.getLogger(TransitionScreen.class);

	private static final Color[][] COLOR_SCHEMES = new Color[][] {
			new Color[] {
					Color.YELLOW,
					Color.DARK_GRAY,
					Color.GRAY,
					Color.LIGHT_GRAY,
					Color.WHITE,
					Color.BLACK
			},
			new Color[] {
					Color.RED,
					Color.ORANGE,
					Color.YELLOW,
					Color.GREEN,
					Color.BLUE
			},
			new Color[] {
					Color.RED,
					Color.PINK,
					Color.MAGENTA,
					Color.WHITE,
					Color.BLACK
			},
			new Color[] {
					Color.ORANGE,
					Color.WHITE,
					Color.BLACK
			},
			new Color[] {
					Color.YELLOW,
					Color.WHITE,
					Color.BLACK
			},
			new Color[] {
					Color.GREEN,
					Color.GRAY,
					Color.WHITE,
					Color.BLACK
			},
			new Color[] {
					Color.BLUE,
					Color.CYAN,
					Color.GREEN,
					Color.WHITE,
					Color.BLACK
			}
	};

	private static final int TRANSITION_LENGTH = 50;

	private Color[] colorScheme;
	private int counter = 0;

	public static TransitionScreen create(String name, BBConfig config, BBContext context) {
		TransitionScreen screen = new TransitionScreen(name, config, context);
		screen.postConstruct();
		return screen;
	}

	private TransitionScreen(String name, BBConfig config, BBContext context) {
		super(name, config, context);
		int schemeIndex = MathUtil.nextRandomInt(COLOR_SCHEMES.length);
		this.colorScheme = COLOR_SCHEMES[schemeIndex];
	}

	@Override
	public void start() {
		this.counter = 0;
		super.start();
	}

	@Override
	public JComponent buildJComponent() {
		return new MyJComponent();
	}

	@Override
	public KeyListener buildKeyHandler() {
		// This screen doesn't support user input.
		return null;
	}

	@Override
	public ActionListener buildTimerHandler() {
		return new TimerHandler();
	}

	private class MyJComponent extends JPanel {

		public MyJComponent() {
			setBackground(Color.BLACK);
		}

		@Override
		public Dimension getPreferredSize() {
			return BBConfig.SCREEN_SIZE_PX;
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Dimension scrSize = getSize();
			double progress = (double) counter / TRANSITION_LENGTH;

			// Draw outer colored box
			double outerProgress = Math.min(1.0, 2.0 * progress);
			double outerRegress = outerProgress;
			int colorIndex = 0;
			while (outerRegress > 0.0) {
				int outerWidth = (int) (scrSize.width * outerRegress);
				int outerHeight = (int) (scrSize.height * outerRegress);
				int outerX = (scrSize.width - outerWidth) / 2;
				int outerY = (scrSize.height - outerHeight) / 2;
				g.setColor(colorScheme[colorIndex++ % colorScheme.length]);
				g.fillRect(outerX, outerY, outerWidth, outerHeight);
				g.setColor(Color.BLACK);
				g.fillRect(outerX + 5, outerY + 5, Math.max(0, outerWidth - 10), Math.max(0, outerHeight - 10));
				outerRegress -= 0.02;
			}

			// Draw inner black box
			// TODO Initiate inner progress quickly or else the screen stays black
			double innerProgress = Math.max(0.0, 2.0 * progress - 0.5);
			int innerWidth = (int) (scrSize.width * innerProgress);
			int innerHeight = (int) (scrSize.height * innerProgress);
			int innerX = (scrSize.width - innerWidth) / 2;
			int innerY = (scrSize.height - innerHeight) / 2;
			g.setColor(Color.BLACK);
			g.fillRect(innerX, innerY, innerWidth, innerHeight);
		}
	}

	private class TimerHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (counter < TRANSITION_LENGTH) {
				counter++;
				repaint();
			} else {
				fireScreenEvent(ScreenEvent.SCREEN_EXPIRED);
			}
		}
	}
}
