package bb.attract.backstory;

import bb.common.view.FontFactory;
import bb.framework.view.AttractScreen;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import static bb.BBConfig.FRAMES_PER_SECOND;

/**
 * Created by willie on 6/18/17.
 */
public class BackstoryScreen extends AttractScreen {
	private static final int TTL = 20 * FRAMES_PER_SECOND;
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
		super(TTL);
		this.backstoryModel = backstoryModel;
		this.fontFactory = fontFactory;
	}

	// TODO Get rid of this once BackstoryModel is gone.
	@Deprecated
	@Override
	public void update() {
		super.update();
		backstoryModel.update();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintBackstory(g);
	}

	private void paintBackstory(Graphics g) {
		String backstory = backstoryModel.getBackstory();
		char[] backstoryArr = backstory.toCharArray();
		int counter = backstoryModel.getCounter();
		int numChars = Math.min(counter, backstoryArr.length);
		List<String> lines = toLines(backstoryArr, numChars);

		g.translate(X_OFFSET, Y_OFFSET);
		g.setFont(fontFactory.getSmallFont());

		if (counter < backstoryArr.length + 5 * FRAMES_PER_SECOND) {
			g.setColor(Color.YELLOW);
		} else {
			int colorIndex = (counter / 5) % COLORS.length;
			g.setColor(COLORS[colorIndex]);
		}

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
