package bb.arena.model;

import bb.arena.event.EntityState;

/**
 * Created by wwheeler on 6/13/17.
 */
public class Balloon extends AbstractEntity {
	public static final int SPEED = 5;
	
	private static final int WIDTH = 3;
	private static final int HEIGHT = 3;
	
	private int dx;
	private int dy;
	
	public Balloon(ArenaModel arenaModel, int x, int y, int dx, int dy) {
		super(arenaModel, EntityState.ACTIVE);
		
		if (dx == 0 && dy == 0) {
			throw new IllegalArgumentException("dx and dy can't both be 0");
		}
		
		setX(x);
		setY(y);
		
		this.dx = dx;
		this.dy = dy;
	}
	
	@Override
	public int getWidth() {
		return WIDTH;
	}
	
	@Override
	public int getHeight() {
		return HEIGHT;
	}
	
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}
	
	@Override
	public void update() {
		updateLocation(dx, dy);
	}
}
