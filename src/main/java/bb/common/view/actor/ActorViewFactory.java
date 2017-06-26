package bb.common.view.actor;

import bb.common.model.LexiModel;
import bb.common.model.TextModel;
import bb.common.view.factory.FontFactory;
import bb.common.view.factory.SpriteFactory;
import bb.framework.util.Assert;

/**
 * Created by willie on 6/24/17.
 */
public class ActorViewFactory {
	private SpriteFactory spriteFactory;
	private FontFactory fontFactory;

	public ActorViewFactory(SpriteFactory spriteFactory, FontFactory fontFactory) {
		Assert.notNull(spriteFactory, "spriteFactory can't be null");
		Assert.notNull(fontFactory, "fontFactory can't be null");
		this.spriteFactory = spriteFactory;
		this.fontFactory = fontFactory;
	}

	public TextView createTextView(TextModel model) {
		Assert.notNull(model, "model can't be null");
		return new TextView(model, fontFactory);
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
