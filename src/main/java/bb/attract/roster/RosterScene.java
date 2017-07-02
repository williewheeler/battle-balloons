package bb.attract.roster;

import bb.common.BBConfig;
import bb.common.actor.model.Lexi;
import bb.common.actor.model.Obstacle;
import bb.common.actor.model.Text;
import bb.framework.scene.TtlScene;

/**
 * Created by willie on 7/2/17.
 */
public class RosterScene extends TtlScene {
	private static final int TTL = 60 * BBConfig.FRAMES_PER_SECOND;

	public RosterScene() {
		super(TTL);
		initScene();
	}

	@Override
	public void update() {
		super.update();
		// TODO Introduce new actors according to the frame
	}

	private void initScene() {
		addActor(new Text("Hello, I'm Lexi.", 40, 140));
		addActor(new Lexi(new RosterLexiBrain(), 50, 180));
		addActor(new Obstacle(240, 180));
	}
}
