package bb.attract;

import bb.common.BBConfig;
import bb.common.BBContext;
import bb.common.model.TextModel;
import bb.common.view.ActorViewFactory;
import bb.common.view.TextView;
import bb.framework.event.GameEvent;
import bb.framework.event.GameListener;
import bb.framework.model.Actor;
import bb.framework.view.TtlScreen;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by willie on 6/30/17.
 */
public class BackstoryScreen extends TtlScreen {
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
		initScene();
	}

	@Override
	public KeyListener buildKeyListener() {
		return new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				fireGameEvent(BackstoryScreen.this, GameEvent.SCREEN_ABORTED);
			}
		};
	}

	private void initScene() {
		ActorViewFactory actorViewFactory = getContext().getActorViewFactory();
		TextModel textModel = new TextModel(BACKSTORY, 20, 40);
		TextView textView = actorViewFactory.createTextView(textModel);
		Actor text = new Actor(textModel, textView);
		addActor(text);
	}
}
