package bb.backstory;

import bb.core.view.FontFactory;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static bb.BBConfig.SCREEN_SIZE_PX;

/**
 * Created by willie on 6/18/17.
 */
public class BackstoryScreen extends JComponent {
	private static final int X_OFFSET = 20;
	private static final int Y_OFFSET = 40;

	private static final Color[] COLORS = {
//			Color.RED,
			Color.YELLOW,
			Color.GREEN,
			Color.CYAN,
//			Color.BLUE,
			Color.MAGENTA,
			Color.WHITE
	};

	private BackstoryModel backstoryModel;
	private FontFactory fontFactory;

	public BackstoryScreen(BackstoryModel backstoryModel, FontFactory fontFactory) {
		this.backstoryModel = backstoryModel;
		this.fontFactory = fontFactory;
	}

	@Override
	public Dimension getPreferredSize() {
		return SCREEN_SIZE_PX;
	}

	@Override
	public void paint(Graphics g) {
		paintBackground(g);
		paintBackstory(g);
	}

	private void paintBackground(Graphics g) {
		Dimension screenSize = getSize();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenSize.width, screenSize.height);
	}

	private void paintBackstory(Graphics g) {
		String backstory = backstoryModel.getBackstory();
		char[] backstoryArr = backstory.toCharArray();
		int numChars = Math.min(backstoryModel.getCounter(), backstoryArr.length);
		int colorIndex = (backstoryModel.getCounter() / 5) % COLORS.length;
		List<String> lines = toLines(backstoryArr, numChars);

		g.translate(X_OFFSET, Y_OFFSET);
		g.setFont(fontFactory.getSmallFont());
		g.setColor(COLORS[colorIndex]);

		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			g.drawString(line, 0, i * 12);
		}

		g.translate(-X_OFFSET, -Y_OFFSET);
	}

	private List<String> toLines(char[] backstoryArr, int numChars) {
		List<String> lines = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < numChars; i++) {
			char c = backstoryArr[i];
			if (c == '\n') {
				lines.add(builder.toString());
				builder = new StringBuilder();
			} else {
				builder.append(c);
			}
		}
		lines.add(builder.toString());
		return lines;
	}
}
