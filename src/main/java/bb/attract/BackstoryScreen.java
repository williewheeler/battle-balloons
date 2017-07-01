package bb.attract;

import bb.BBConfig;
import bb.BBContext;
import bb.common.model.TextModel;
import bb.common.view.actor.ActorViewFactory;
import bb.common.view.actor.TextView;
import bb.framework.event.GameListener;
import bb.framework.model.actor.Actor;
import bb.framework.view.AttractScreen;

/**
 * Created by willie on 6/30/17.
 */
public class BackstoryScreen extends AttractScreen {
	private static final int TTL = 15 * BBConfig.FRAMES_PER_SECOND;

	private static final String BACKSTORY =
			"BATTLE BALLOONS\n\n" +
			"IN THE YEAR 2112 THERE LIVES A GROUP OF FRIENDS:\n" +
			"THE BEAST SQUAD.\n\n" +
			"AT SCHOOL,\n" +
			"A WATER BALLOON FIGHT GETS OUT OF HAND.\n" +
			"THE BEAST SQUAD BREAKS TABLES AND GETS SOAKED.\n" +
			"THEY CAN'T GET TOWELS.\n" +
			"THE ONLY DRY PERSON IS A GIRL NAMED LEXI.\n\n" +
			"CAN YOU SAVE HER FROM GETTING...\n" +
			"GROUNDED?";


	public BackstoryScreen(BBContext context, GameListener gameListener) {
		super(context, gameListener, TTL);
		ActorViewFactory actorViewFactory = context.getActorViewFactory();
		TextModel textModel = new TextModel(BACKSTORY, 20, 40);
		TextView textView = actorViewFactory.createTextView(textModel);
		Actor text = new Actor(textModel, textView);
		addActor(text);
	}
}
