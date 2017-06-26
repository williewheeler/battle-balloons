package bb.common.model;

import bb.framework.model.actor.AbstractActorModel;
import bb.framework.model.actor.NullBrain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willie on 6/25/17.
 */
public class TextModel extends AbstractActorModel {
	private char[] textArr;
	private int counter;

	public TextModel(String text, int x, int y) {
		super(new NullBrain(), x, y, 0);
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
	public void doUpdate() {
		this.counter = Math.min(textArr.length, counter + 1);
	}
}
