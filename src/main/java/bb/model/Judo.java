package bb.model;

/**
 * Created by wwheeler on 6/12/17.
 */
public class Judo extends AbstractEntity {
	private static final int WIDTH = 5;
	private static final int HEIGHT = 11;
	
	public Judo(GameModel gameModel) {
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
}
