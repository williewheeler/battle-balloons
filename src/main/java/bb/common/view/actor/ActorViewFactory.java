package bb.common.view.actor;

import bb.common.model.LexiModel;
import bb.common.view.SpriteFactory;
import bb.framework.util.Assert;

/**
 * Created by willie on 6/24/17.
 */
public class ActorViewFactory {
	private SpriteFactory spriteFactory;

	public ActorViewFactory(SpriteFactory spriteFactory) {
		Assert.notNull(spriteFactory, "spriteFactory can't be null");
		this.spriteFactory = spriteFactory;
	}

	public LexiView createLexiView(LexiModel model) {
		Assert.notNull(model, "model can't be null");
		LexiView view = new LexiView(model);
		view.setWalkingSprites(spriteFactory.getLexi());
		view.setBlinkingSprites(spriteFactory.getLexiBlinking());
		view.setWavingSprites(spriteFactory.getLexiWaving());
		return view;
	}
}
