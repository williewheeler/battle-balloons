package bb.model;

import java.util.Random;

import static bb.BBConfig.ARENA_INNER_HEIGHT_PX;
import static bb.BBConfig.ARENA_INNER_WIDTH_PX;
import static bb.BBConfig.CLEARING_RADIUS;

/**
 * Created by wwheeler on 6/11/17.
 */
public abstract class AbstractEntity implements Entity {
	private static final Random RANDOM = new Random();
	
	private GameModel gameModel;
	private int x;
	private int y;
	private Direction direction;
	private int animationCounter;
	
	public AbstractEntity(GameModel gameModel) {
		this.gameModel = gameModel;
		this.direction = Direction.DOWN;
	}
	
	@Override
	public int getX() {
		return x;
	}
	
	protected void setX(int x) {
		this.x = x;
	}
	
	protected void changeX(int dx) {
		this.x += dx;
	}
	
	@Override
	public int getY() {
		return y;
	}
	
	protected void setY(int y) {
		this.y = y;
	}
	
	protected void changeY(int dy) {
		this.y += dy;
	}
	
	protected void randomizeLocation() {
		Player player = gameModel.getPlayer();
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
		this.animationCounter = (animationCounter + 1) % 4;
	}
	
	@Override
	public void update() {
		// No-op by default
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
