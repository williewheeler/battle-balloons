package bb.attract.view;

import bb.attract.model.RosterLexiBrain;
import bb.common.BBConfig;
import bb.common.BBContext;
import bb.common.model.LexiModel;
import bb.common.model.ObstacleModel;
import bb.common.model.TextModel;
import bb.common.view.ActorViewFactory;
import bb.common.view.LexiView;
import bb.common.view.ObstacleView;
import bb.common.view.TextView;
import bb.framework.event.ScreenEvent;
import bb.framework.model.Actor;
import bb.framework.view.TtlScreen;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by willie on 6/30/17.
 */
public class RosterScreen extends TtlScreen {
	private static final int TTL = 60 * BBConfig.FRAMES_PER_SECOND;

	public RosterScreen(BBContext context) {
		super(context, TTL);
		initScene();
	}

	@Override
	public KeyListener buildKeyListener() {
		return new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				fireScreenEvent(ScreenEvent.SCREEN_ABORTED);
			}
		};
	}

	private void initScene() {
		ActorViewFactory actorViewFactory = getContext().getActorViewFactory();

		TextModel textModel = new TextModel("Hello, I'm Lexi.", 40, 140);
		TextView textView = actorViewFactory.createTextView(textModel);
		Actor text = new Actor(textModel, textView);

		RosterLexiBrain lexiBrain = new RosterLexiBrain();
		LexiModel lexiModel = new LexiModel(lexiBrain, 50, 180);
		LexiView lexiView = actorViewFactory.createLexiView(lexiModel);
		Actor lexi = new Actor(lexiModel, lexiView);

		ObstacleModel obstacleModel = new ObstacleModel(240, 180);
		ObstacleView obstacleView = actorViewFactory.createObstacleView(obstacleModel);
		Actor obstacle = new Actor(obstacleModel, obstacleView);

		addActor(lexi);
		addActor(text);
		addActor(obstacle);
	}
}
