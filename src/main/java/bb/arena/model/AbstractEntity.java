package bb.arena.model;

import bb.arena.event.EntityState;

import java.util.Random;

import static bb.BBConfig.*;

/**
 * Created by wwheeler on 6/11/17.
 */
public abstract class AbstractEntity implements Entity {
	static final Random RANDOM = new Random();
	
	private static final int ANIMATION_PERIOD = 4;
	
	private ArenaModel arenaModel;
	private EntityState state;
	private int x;
	private int y;
	private Direction direction;
	private int animationCounter;
	
	public AbstractEntity(ArenaModel arenaModel, EntityState state) {
		this.arenaModel = arenaModel;
		this.direction = Direction.DOWN;
		this.animationCounter = 0;
		this.state = state;
	}
	
	public ArenaModel getArenaModel() {
		return arenaModel;
	}
	
	public Player getPlayer() {
		return arenaModel.getPlayer();
	}
	
	public EntityState getState() {
		return state;
	}
	
	protected void setState(EntityState state) {
		this.state = state;
	}
	
	@Override
	public int getX() {
		return x;
	}
	
	protected void setX(int x) {
		this.x = x;
	}
	
	@Override
	public int getY() {
		return y;
	}
	
	protected void setY(int y) {
		this.y = y;
	}
	
	protected void randomizeLocation() {
		Player player = arenaModel.getPlayer();
		assert (player != null);
		
		int xRange = ARENA_INNER_WIDTH_PX - getWidth();
		int yRange = ARENA_INNER_HEIGHT_PX - getHeight();
		
		while (true) {
			this.x = RANDOM.nextInt(xRange) + getWidth() / 2;
			this.y = RANDOM.nextInt(yRange) + getHeight() / 2;
			
			int xDiff = x - player.getX();
			int yDiff = y - player.getY();
			double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
			if (dist > CLEARING_RADIUS) {
				break;
			}
		}
	}
	
	@Override
	public Direction getDirection() {
		return direction;
	}
	
	protected void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	@Override
	public int getAnimationCounter() {
		return animationCounter;
	}
	
	protected void incrementAnimationCounter() {
		this.animationCounter = (animationCounter + 1) % ANIMATION_PERIOD;
	}
	
	@Override
	public void update() {
		// No-op by default
	}
	
	protected void updateLocation(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
	
	public boolean isOnScreen() {
		int x = getX();
		int y = getY();
		return (0 <= x && x <= ARENA_INNER_WIDTH_PX && 0 <= y && y <= ARENA_INNER_HEIGHT_PX);
	}
	
	protected void updateDirection(int dx, int dy) {
		Direction direction = null;
		
		if (dx < 0) {
			if (dy < 0) {
				direction = Direction.UP_LEFT;
			} else if (dy == 0) {
				direction = Direction.LEFT;
			} else {
				direction = Direction.DOWN_LEFT;
			}
		} else if (dx == 0) {
			if (dy < 0) {
				direction = Direction.UP;
			} else if (dy == 0) {
				// Do nothing
			} else {
				direction = Direction.DOWN;
			}
		} else {
			if (dy < 0) {
				direction = Direction.UP_RIGHT;
			} else if (dy == 0) {
				direction = Direction.RIGHT;
			} else {
				direction = Direction.DOWN_RIGHT;
			}
		}
		
		if (direction != null) {
			setDirection(direction);
		}
	}
	
	protected boolean collision(Entity that) {
		int thisXLo = getX();
		int thisXHi = thisXLo + getWidth();
		int thisYLo = getY();
		int thisYHi = thisYLo + getHeight();
		
		int thatXLo = that.getX();
		int thatXHi = thatXLo + that.getWidth();
		int thatYLo = that.getY();
		int thatYHi = thatYLo + that.getHeight();
		
		boolean xOverlap = (thisXLo <= thatXHi) && (thisXHi >= thatXLo);
		boolean yOverlap = (thisYLo <= thatYHi) && (thisYHi >= thatYLo);
		
		return xOverlap && yOverlap;
	}
}
