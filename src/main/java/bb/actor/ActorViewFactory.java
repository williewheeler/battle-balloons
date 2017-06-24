package bb.actor;

import bb.actor.lexi.LexiActorView;
import bb.core.util.Assert;
import bb.core.view.SpriteFactory;

/**
 * Created by willie on 6/24/17.
 */
public class ActorViewFactory {
	private SpriteFactory spriteFactory;

	public ActorViewFactory(SpriteFactory spriteFactory) {
		Assert.notNull(spriteFactory, "spriteFactory can't be null");
		this.spriteFactory = spriteFactory;
	}

	public ActorView createLexiView(ActorModel model) {
		Assert.notNull(model, "model can't be null");
		LexiActorView view = new LexiActorView(model);
		view.setBlinkingSprites(spriteFactory.getLexiBlinking());
		view.setWavingSprites(spriteFactory.getLexiWaving());
		return view;
	}
}
