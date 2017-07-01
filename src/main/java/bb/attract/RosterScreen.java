package bb.attract;

import bb.BBConfig;
import bb.BBContext;
import bb.common.model.LexiBrain;
import bb.common.model.LexiModel;
import bb.common.model.TextModel;
import bb.common.view.actor.ActorViewFactory;
import bb.common.view.actor.LexiView;
import bb.common.view.actor.TextView;
import bb.framework.event.GameListener;
import bb.framework.model.actor.Actor;
import bb.framework.view.AttractScreen;

/**
 * Created by willie on 6/30/17.
 */
public class RosterScreen extends AttractScreen {
	private static final int TTL = 60 * BBConfig.FRAMES_PER_SECOND;

	private BBContext context;
	private GameListener gameListener;
	private int ttl;

	public RosterScreen(BBContext context, GameListener gameListener) {
		super(context, gameListener, TTL);

		ActorViewFactory actorViewFactory = context.getActorViewFactory();

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
