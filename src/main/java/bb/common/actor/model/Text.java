package bb.common.actor.model;

import java.util.ArrayList;
import java.util.List;

// TODO The scene's counter should drive the behavior here, rather than an
// internal counter. That gives us a lot more control. [WLW]

/**
 * Created by willie on 6/25/17.
 */
public class Text extends AbstractActor {
	private char[] textArr;
	private int counter;

	public Text(String text, int x, int y) {
		super(null, x, y, -1, -1);
		this.textArr = text.toCharArray();
		this.counter = 1;
	}

	// TODO Wouldn't it make more sense to precompute the lines, rather than doing this
	// dynamically at request time? [WLW]
	public List<String> toLines() {
		List<String> lines = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < counter; i++) {
			char c = textArr[i];
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

	@Override
	public void updateBody() {
		this.counter = Math.min(textArr.length, counter + 1);
	}
}
