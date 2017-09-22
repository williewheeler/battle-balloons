package bb.model;

/**
 * Created by wwheeler on 6/12/17.
 */
public class Judo extends AbstractEntity {
	private static final int WIDTH = 5;
	private static final int HEIGHT = 11;
	private static final int SPEED = 2;
	private static final int MAX_MOVE_PERIOD = 10;

	private int moveCounter;

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

	@Override
	public void update() {
		if (moveCounter <= 0) {
			int jx = getX();
			int jy = getY();

			Player player = getPlayer();
			int px = player.getX();
			int py = player.getY();

			int dx = 0;
			int dy = 0;

			if (jx < px) {

			} else if (jx > px) {

			}

			if (jy < py) {

			} else if (jy > py) {

			}

			updateLocation(dx, dy);
			updateDirection(dx, dy);

			initMoveCounter();
		} else {
			moveCounter--;
		}
	}

	private void initMoveCounter() {
		this.moveCounter = RANDOM.nextInt(MAX_MOVE_PERIOD);
	}

	private int getRandomSpeed() { return RANDOM.nextInt(SPEED * 2) - SPEED; }
}
