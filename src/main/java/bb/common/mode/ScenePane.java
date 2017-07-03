package bb.common.mode;

import bb.common.BBContext;
import bb.common.actor.view.ActorViewFactory;
import bb.framework.actor.Actor;
import bb.framework.scene.Scene;
import bb.framework.util.Assert;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 * Created by willie on 7/2/17.
 */
public class ScenePane extends JPanel {
	private BBContext context;
	private Scene scene;

	public ScenePane(BBContext context, Scene scene) {
		Assert.notNull(context, "context can't be null");
		Assert.notNull(scene, "scene can't be null");
		this.context = context;
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
		ActorViewFactory avf = context.getActorViewFactory();
		List<Actor> actors = scene.getActors();
		for (Actor actor : actors) {
			avf.getView(actor).paint(g, actor);
		}
	}
}
