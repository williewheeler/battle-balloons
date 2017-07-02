package bb.attract;

import bb.common.BBContext;
import bb.framework.util.Assert;

/**
 * Created by willie on 7/1/17.
 */
public class AttractScreenFactory {
	private BBContext context;

	public AttractScreenFactory(BBContext context) {
		Assert.notNull(context, "context can't be null");
		this.context = context;
	}

	public TitleScreen createTitleScreen() {
		return new TitleScreen(context);
	}

	public BackstoryScreen createBackstoryScreen() {
		return new BackstoryScreen(context);
	}

	public RosterScreen createRosterScreen() {
		return new RosterScreen(context);
	}
}
