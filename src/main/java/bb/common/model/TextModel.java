package bb.common.model;

import bb.framework.model.actor.AbstractActorModel;
import bb.framework.model.actor.NullBrain;

/**
 * Created by willie on 6/25/17.
 */
public class TextModel extends AbstractActorModel {
	private String text;
	private int counter;

	public TextModel(String text, int x, int y) {
		super(new NullBrain(), x, y, 0);
		this.text = text;
		this.counter = 1;
	}

	public String getText() {
		return text.substring(0, counter);
	}

	public int getCounter() {
		return counter;
	}

	@Override
	public void doUpdate() {
		this.counter = Math.min(text.length(), counter + 1);
	}
}
