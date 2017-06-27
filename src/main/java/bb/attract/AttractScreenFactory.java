package bb.attract;

import bb.BBConfig;
import bb.attract.roster.RosterLexiBrain;
import bb.attract.title.TitleScreen;
import bb.common.model.LexiBrain;
import bb.common.model.LexiModel;
import bb.common.model.TextModel;
import bb.common.view.factory.FontFactory;
import bb.common.view.factory.SpriteFactory;
import bb.common.view.actor.ActorViewFactory;
import bb.common.view.actor.LexiView;
import bb.common.view.actor.TextView;
import bb.framework.model.actor.Actor;
import bb.framework.view.AttractScreen;
import bb.framework.view.loader.ImageLoader;

/**
 * Created by willie on 6/25/17.
 */
public class AttractScreenFactory {
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

	private FontFactory fontFactory;
	private ImageLoader imageLoader;
	private SpriteFactory spriteFactory;
	private ActorViewFactory actorViewFactory;

	public AttractScreenFactory(
			FontFactory fontFactory,
			ImageLoader imageLoader,
			SpriteFactory spriteFactory,
			ActorViewFactory actorViewFactory) {

		this.fontFactory = fontFactory;
		this.imageLoader = imageLoader;
		this.spriteFactory = spriteFactory;
		this.actorViewFactory = actorViewFactory;
	}

	public TitleScreen createTitleScreen() {
		return new TitleScreen(fontFactory, imageLoader, spriteFactory);
	}

	public AttractScreen createBackstoryScreen() {
		TextModel textModel = new TextModel(BACKSTORY, 20, 40);
		TextView textView = actorViewFactory.createTextView(textModel);
		Actor text = new Actor(textModel, textView);

		AttractScreen screen = new AttractScreen(15 * BBConfig.FRAMES_PER_SECOND);
		screen.addActor(text);

		return screen;
	}

	public AttractScreen createRosterScreen() {
		TextModel textModel = new TextModel("Hello, I'm Lexi.", 40, 140);
		TextView textView = actorViewFactory.createTextView(textModel);
		Actor text = new Actor(textModel, textView);

		LexiBrain lexiBrain = new RosterLexiBrain();
		LexiModel lexiModel = new LexiModel(lexiBrain, 50, 180);
		LexiView lexiView = actorViewFactory.createLexiView(lexiModel);
		Actor lexi = new Actor(lexiModel, lexiView);

		AttractScreen screen = new AttractScreen(60 * BBConfig.FRAMES_PER_SECOND);
		screen.addActor(lexi);
		screen.addActor(text);

		return screen;
	}
}
