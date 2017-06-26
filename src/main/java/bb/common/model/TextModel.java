package bb.common.model;

import bb.framework.model.actor.AbstractActorModel;
import bb.framework.model.actor.NullBrain;

/**
 * Created by willie on 6/25/17.
 */
public class TextModel extends AbstractActorModel {
	private String text;

	public TextModel(String text, int x, int y) {
		super(new NullBrain(), x, y, 0);
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public void doUpdate() {
	}
}
