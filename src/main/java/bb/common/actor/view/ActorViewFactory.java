package bb.common.actor.view;

import bb.common.actor.model.BigBalloon;
import bb.common.actor.model.Lexi;
import bb.common.actor.model.Obstacle;
import bb.common.actor.model.Text;
import bb.common.factory.FontFactory;
import bb.common.factory.SpriteFactory;
import bb.framework.actor.Actor;
import bb.framework.util.Assert;
import bb.framework.actor.ActorView;

/**
 * Created by willie on 6/24/17.
 */
public class ActorViewFactory {
	private BigBalloonView bigBalloonView;
	private LexiView lexiView;
	private ObstacleView obstacleView;
	private TextView textView;

	public ActorViewFactory(FontFactory fontFactory, SpriteFactory spriteFactory) {
		Assert.notNull(fontFactory, "fontFactory can't be null");
		Assert.notNull(spriteFactory, "spriteFactory can't be null");

		initBigBalloonView(spriteFactory);
		initLexiView(spriteFactory);
		initObstacleView();
		initTextView(fontFactory);
	}

	public ActorView getView(Actor actor) {
		// TODO Refactor to avoid if/else chain. [WLW]
		if (actor instanceof BigBalloon) {
			return bigBalloonView;
		} else if (actor instanceof Lexi) {
			return lexiView;
		} else if (actor instanceof Obstacle) {
			return obstacleView;
		} else if (actor instanceof Text) {
			return textView;
		} else {
			throw new IllegalArgumentException("Illegal actor: " + actor);
		}
	}

	private void initBigBalloonView(SpriteFactory spriteFactory) {
		this.bigBalloonView = new BigBalloonView(spriteFactory.getBigBalloons());
	}

	private void initLexiView(SpriteFactory spriteFactory) {
		this.lexiView = new LexiView();
		lexiView.setBlinkingSprites(spriteFactory.getLexiBlinking());
		lexiView.setWalkingSprites(spriteFactory.getLexiWalking());
		lexiView.setWavingSprites(spriteFactory.getLexiWaving());
	}

	private void initObstacleView() {
		this.obstacleView = new ObstacleView();
	}

	private void initTextView(FontFactory fontFactory) {
		this.textView = new TextView(fontFactory);
	}
}
