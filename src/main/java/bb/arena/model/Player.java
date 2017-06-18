package bb.arena.model;

import bb.arena.event.EntityState;
import bb.arena.event.ArenaEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static bb.BBConfig.ARENA_INNER_HEIGHT_PX;
import static bb.BBConfig.ARENA_INNER_WIDTH_PX;

/**
 * Created by willie on 6/5/17.
 */
public class Player extends AbstractEntity {
	public static final int ENTERING_DURATION = 20;
	public static final int EXITING_DURATION = 40;

	private static final int WIDTH = 5;
	private static final int HEIGHT = 11;
	private static final int SPEED = 3;
	private static final int RECHARGE_PERIOD = 3;
	private static final int WALK_EVENT_PERIOD = 4;
	
	private static final Logger log = LoggerFactory.getLogger(Player.class);
	
	private int score = 0;
	private int level = 1;
	private int lives = 3;
	private final DirectionIntent moveIntent = new DirectionIntent();
	private final DirectionIntent fireIntent = new DirectionIntent();
	
	private int enteringCountdown = ENTERING_DURATION - 1;
	private int exitingCountdown = EXITING_DURATION - 1;
	private int rechargeCountdown = RECHARGE_PERIOD;
	private int walkEventCountdown = 0;
	
	public Player(ArenaModel arenaModel) {
		super(arenaModel, EntityState.ENTERING);
		center();
	}
	
	public int getScore() {
		return score;
	}
	
	public void incrementScore(int value) {
		this.score += value;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getLives() {
		return lives;
	}
	
	@Override
	public int getWidth() {
		return WIDTH;
	}
	
	@Override
	public int getHeight() {
		return HEIGHT;
	}
	
	public DirectionIntent getMoveIntent() {
		return moveIntent;
	}
	
	public DirectionIntent getFireIntent() {
		return fireIntent;
	}
	
	public int getEnteringCountdown() {
		return enteringCountdown;
	}
	
	public int getExitingCountdown() {
		return exitingCountdown;
	}
	
	public void center() {
		setX(ARENA_INNER_WIDTH_PX / 2);
		setY(ARENA_INNER_HEIGHT_PX / 2);
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
		updateLocation();
		fire();
	}
	
	private void updateLocation() {
		int dx = 0;
		int dy = 0;
		
		if (moveIntent.up) {
			dy -= SPEED;
		}
		if (moveIntent.down) {
			dy += SPEED;
		}
		if (moveIntent.left) {
			dx -= SPEED;
		}
		if (moveIntent.right) {
			dx += SPEED;
		}
		
		if (dx != 0 || dy != 0) {
			updateLocation(dx, dy);
			enforceBounds();
			updateDirection(dx, dy);
			incrementAnimationCounter();
			
			if (walkEventCountdown <= 0) {
				getArenaModel().fireEvent(ArenaEvents.PLAYER_WALKS);
				this.walkEventCountdown = WALK_EVENT_PERIOD;
			} else {
				this.walkEventCountdown--;
			}
		}
	}
	
	private void enforceBounds() {
		int playerHalfWidth = getWidth() / 2;
		int playerHalfHeight = getHeight() / 2;
		
		// All bounds below are inclusive
		int playerXLo = getX() - playerHalfWidth;
		int playerXHi = playerXLo + getWidth() - 1;
		int playerYLo = getY() - playerHalfHeight;
		int playerYHi = playerYLo + getHeight() - 1;
		
		int arenaXLo = 0;
		int arenaXHi = ARENA_INNER_WIDTH_PX - 1;
		int arenaYLo = 0;
		int arenaYHi = ARENA_INNER_HEIGHT_PX - 1;
		
		if (playerXLo < arenaXLo) {
			setX(playerHalfWidth);
		}
		if (playerXHi > arenaXHi) {
			setX(arenaXHi - playerHalfWidth);
		}
		if (playerYLo < arenaYLo) {
			setY(playerHalfHeight);
		}
		if (playerYHi > arenaYHi) {
			setY(arenaYHi - playerHalfHeight);
		}
	}
	
	private void fire() {
		if (rechargeCountdown > 0) {
			this.rechargeCountdown--;
		} else {
			int dx = 0;
			int dy = 0;
			
			if (fireIntent.up) {
				dy -= Balloon.SPEED;
			}
			if (fireIntent.down) {
				dy += Balloon.SPEED;
			}
			if (fireIntent.left) {
				dx -= Balloon.SPEED;
			}
			if (fireIntent.right) {
				dx += Balloon.SPEED;
			}
			
			if (dx != 0 || dy != 0) {
				ArenaModel arenaModel = getArenaModel();
				Player player = getPlayer();
				int x = player.getX();
				int y = player.getY();
				Balloon balloon = new Balloon(getArenaModel(), x, y, dx, dy);
				arenaModel.getPlayerBalloons().add(balloon);
				arenaModel.fireEvent(ArenaEvents.PLAYER_THROWS_BALLOON);
				this.rechargeCountdown = RECHARGE_PERIOD;
			}
		}
	}
	
	private void updateExiting() {
		this.exitingCountdown--;
		
		if (exitingCountdown < 0) {
			setState(EntityState.GONE);
		}
	}
}
