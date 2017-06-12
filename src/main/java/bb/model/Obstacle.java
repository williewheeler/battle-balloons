package bb.model;

/**
 * Created by wwheeler on 6/11/17.
 */
public class Obstacle extends AbstractEntity {
	private static final int WIDTH = 8;
	private static final int HEIGHT = 8;
	
	public Obstacle(GameModel gameModel) {
		super(gameModel);
		randomizeLocation();
	}
	
	@Override
	public int getWidth() {
		return WIDTH;
	}
	
	@Override
	public int getHeight() {
		return HEIGHT;
	}
	
	@Override
	public void update() {
		// Nothing to update here for now.
		// At some point we will add animations though.
	}
}
