package bb.title;

/**
 * Created by willie on 6/18/17.
 */
public class BigBalloon {
	public enum Color {
		RED,
		YELLOW,
		GREEN,
		CYAN,
		BLUE,
		MAGENTA,
		WHITE
	}

	private static final int MAX_ROT_COUNTDOWN = 5;

	private int x;
	private int y;
	private int dx;
	private int dy;
	private int rotation;
	private int dRotation;
	private Color color;
	private int rotCountdown;

	public BigBalloon(int x, int y, int dx, int dy, int rotation, int dRotation, Color color) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.rotation = rotation;
		this.dRotation = dRotation;
		this.color = color;
		this.rotCountdown = MAX_ROT_COUNTDOWN;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRotation() {
		return rotation;
	}

	public Color getColor() {
		return color;
	}

	public void update() {
		this.x += dx;
		this.y += dy;

		if (rotCountdown == 0) {
			this.rotation += dRotation;

			if (rotation < 0) {
				rotation = 3;
			} else if (rotation > 3) {
				rotation = 0;
			}

			this.rotCountdown = MAX_ROT_COUNTDOWN;
		} else {
			rotCountdown--;
		}
	}
}
