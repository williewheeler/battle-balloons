package bb.common.model;

import bb.framework.model.BasicActorModel;

/**
 * Created by willie on 7/1/17.
 */
public class ObstacleModel extends BasicActorModel {
	private static final int WIDTH = 8;
	private static final int HEIGHT = 8;

	public ObstacleModel(int x, int y) {
		super(null, x, y, -1);
		setWidth(WIDTH);
		setHeight(HEIGHT);
	}
}
