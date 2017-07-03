package bb.common.scene;

import bb.common.BBContext;
import bb.common.actor.view.ActorViewFactory;
import bb.framework.util.Assert;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Created by willie on 7/2/17.
 */
public class ScenePane extends JPanel {
	private BBContext context;
	private ActorViewFactory actorViewFactory;
	private Scene scene;

	public ScenePane(BBContext context, Scene scene) {
		Assert.notNull(context, "context can't be null");
		Assert.notNull(scene, "scene can't be null");
		this.context = context;
		this.actorViewFactory = context.getActorViewFactory();
		this.scene = scene;
		setBackground(Color.BLACK);
	}

	public Scene getScene() {
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
