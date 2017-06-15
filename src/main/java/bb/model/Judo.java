package bb.model;

import bb.model.event.EntityState;

/**
 * Created by wwheeler on 6/12/17.
 */
public class Judo extends AbstractEntity {
	public static final int ENTERING_DURATION = 20;
	public static final int EXITING_DURATION = 5;
	public static final int SCORE = 100;
	
	private static final int WIDTH = 5;
	private static final int HEIGHT = 11;
	private static final int SPEED = 2;
	private static final int MAX_MOVE_PERIOD = 10;

	private int enteringCountdown = ENTERING_DURATION - 1;
	private int exitingCountdown = EXITING_DURATION - 1;
	private int moveCountdown;
	
	public Judo(GameModel gameModel) {
		super(gameModel, EntityState.ENTERING);
		randomizeLocation();
		initMoveCounter();
	}
	
	@Override
	public int getWidth() {
		return WIDTH;
	}
	
	@Override
	public int getHeight() {
		return HEIGHT;
	}

	public int getEnteringCountdown() {
		return enteringCountdown;
	}

	public int getExitingCountdown() {
		return exitingCountdown;
	}

	@Override
	public void update() {
		switch (getState()) {
			case ENTERING:
				updateEntering();
				break;
			case ACTIVE:
				updateActive();
				break;
			case EXITING:
				updateExiting();
				break;
			case GONE:
				break;
		}
	}

	private void updateEntering() {
		this.enteringCountdown--;

		if (enteringCountdown < 0) {
			setState(EntityState.ACTIVE);
		}
	}

	private void updateActive() {
		if (moveCountdown <= 0) {
			int jx = getX();
			int jy = getY();
			
			Player player = getPlayer();
			int px = player.getX();
			int py = player.getY();
			
			int dx = 0;
			int dy = 0;
			
			if (jx < px) {
				dx += SPEED;
			} else if (jx > px) {
				dx -= SPEED;
			}
			
			if (jy < py) {
				dy += SPEED;
			} else if (jy > py) {
				dy -= SPEED;
			}
			
			// TODO Can we extract this, since Player does the same thing? [WLW]
			updateLocation(dx, dy);
			updateDirection(dx, dy);
			incrementAnimationCounter();
			
			initMoveCounter();
		} else {
			moveCountdown--;
		}
	}

	private void updateExiting() {
		this.exitingCountdown--;

		if (exitingCountdown < 0) {
			setState(EntityState.GONE);
		}
	}

	private void initMoveCounter() {
		this.moveCountdown = RANDOM.nextInt(MAX_MOVE_PERIOD);
	}
}
