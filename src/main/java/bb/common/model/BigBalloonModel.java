package bb.common.model;

import bb.framework.model.ActorBrain;
import bb.framework.model.BasicActorModel;
import bb.framework.util.MathUtil;

/**
 * Created by willie on 6/30/17.
 */
public class BigBalloonModel extends BasicActorModel {
	private static final int BALLOON_NUM_COLORS = 6;
	private static final int ROT_TTL = 5;

	private int dx;
	private int dy;
	private int rotation;
	private int dRotation;
	private int rotTtl;
	private int color;

	public BigBalloonModel(ActorBrain brain, int x, int y, int dx, int dy, int rot, int dRot) {
		super(brain, x, y, -1);
		this.dx = dx;
		this.dy = dy;
		this.rotation = rot;
		this.dRotation = dRot;
		this.rotTtl = ROT_TTL;
		this.color = MathUtil.nextRandomInt(BALLOON_NUM_COLORS);
	}

	public int getRotation() {
		return rotation;
	}

	public int getColor() {
		return color;
	}

	@Override
	public void doUpdate() {
		changeX(dx);
		changeY(dy);

		if (rotTtl == 0) {
			this.rotation += dRotation;

			if (rotation < 0) {
				rotation = 3;
			} else if (rotation > 3) {
				rotation = 0;
			}

			this.rotTtl = ROT_TTL;
		} else {
			rotTtl--;
		}
	}
}
