package bb.attract;

import bb.BBConfig;
import bb.BBContext;
import bb.common.model.RosterLexiBrain;
import bb.common.model.LexiBrain;
import bb.common.model.LexiModel;
import bb.common.model.TextModel;
import bb.common.view.ActorViewFactory;
import bb.common.view.LexiView;
import bb.common.view.TextView;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.model.Actor;
import bb.framework.view.AttractScreen;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by willie on 6/30/17.
 */
public class RosterScreen extends AttractScreen {
	private static final int TTL = 60 * BBConfig.FRAMES_PER_SECOND;

	private KeyListener keyListener;

	public RosterScreen(BBContext context, GameListener gameListener) {
		super(context, gameListener, TTL);
		initScene();
	}

	@Override
	public KeyListener buildKeyListener() {
		return new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				fireGameEvent(RosterScreen.this, GameEvent.SCREEN_ABORTED);
			}
		};
	}

	private void initScene() {
		ActorViewFactory actorViewFactory = getContext().getActorViewFactory();

		TextModel textModel = new TextModel("Hello, I'm Lexi.", 40, 140);
		TextView textView = actorViewFactory.createTextView(textModel);
		Actor text = new Actor(textModel, textView);

		LexiBrain lexiBrain = new RosterLexiBrain();
		LexiModel lexiModel = new LexiModel(lexiBrain, 50, 180);
		LexiView lexiView = actorViewFactory.createLexiView(lexiModel);
		Actor lexi = new Actor(lexiModel, lexiView);

		addActor(lexi);
		addActor(text);
	}
}
