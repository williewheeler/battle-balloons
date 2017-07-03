package bb.attract.roster;

import bb.common.actor.model.Lexi;
import bb.framework.actor.AbstractActorBrain;

/**
 * Created by willie on 6/25/17.
 */
public class RosterLexiBrain extends AbstractActorBrain {
	private int counter = 0;

	@Override
	public void update() {
		Lexi lexi = (Lexi) getActor();

		// TODO Instead of this being purely counter-based, it would be good if the brain could "see" the world and
		// define actions in terms of the world. For example, keep walking right til I'm standing in the horizontal
		// center of the screen. [WLW]
		if (counter == 0) {
			lexi.setState(Lexi.State.WAVING);
		} else if (counter == 40) {
			lexi.setState(Lexi.State.WALKING);
			getMoveDirectionIntent().right = true;
		} else if (counter == 78) {
			lexi.setState(Lexi.State.BLINKING);
			getMoveDirectionIntent().right = false;
		}

		this.counter++;
	}
}
