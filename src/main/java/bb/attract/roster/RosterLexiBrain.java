package bb.attract.roster;

import bb.common.model.LexiBrain;

/**
 * Created by willie on 6/25/17.
 */
public class RosterLexiBrain extends LexiBrain {
	private boolean moveUp = false;
	private boolean moveDown = false;
	private boolean moveLeft = false;
	private boolean moveRight = false;
	private int counter = 0;

	@Override
	public boolean moveUp() {
		return moveUp;
	}

	@Override
	public boolean moveDown() {
		return moveDown;
	}

	@Override
	public boolean moveLeft() {
		return moveLeft;
	}

	@Override
	public boolean moveRight() {
		return moveRight;
	}

	@Override
	public void update() {

		// TODO Instead of this being purely counter-based, it would be good if the brain could "see" the world and
		// define actions in terms of the world. For example, keep walking right til I'm standing in the horizontal
		// center of the screen. [WLW]
		if (counter == 0) {
			setState(State.WAVING);
		} else if (counter == 40) {
			setState(State.WALKING);
			this.moveRight = true;
		} else if (counter == 78){
			setState(State.BLINKING);
		}
		this.counter++;
	}
}
