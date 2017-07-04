package bb.common.scene;

import bb.common.actor.view.ActorViewFactory;
import bb.framework.util.Assert;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Created by willie on 7/2/17.
 */
public class BBScenePane extends JPanel {
	private ActorViewFactory actorViewFactory;
	private BBScene scene;

	public BBScenePane(ActorViewFactory actorViewFactory, BBScene scene) {
		Assert.notNull(actorViewFactory, "actorViewFactory can't be null");
		Assert.notNull(scene, "scene can't be null");
		this.actorViewFactory = actorViewFactory;
		this.scene = scene;
		setBackground(Color.BLACK);
	}

	public BBScene getScene() {
		return scene;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintActors(g);
	}

	private void paintActors(Graphics g) {
		scene.getAllActors().forEach(actors -> {
			actors.forEach(actor -> actorViewFactory.getView(actor).paint(g, actor));
		});
	}
}
