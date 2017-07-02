package bb.common.view;

import bb.common.model.BigBalloonModel;
import bb.common.model.LexiModel;
import bb.common.model.ObstacleModel;
import bb.common.model.TextModel;
import bb.framework.model.BasicActorModel;
import bb.framework.util.Assert;
import bb.framework.view.ImageLoader;
import bb.framework.view.ImageView;

import java.awt.image.BufferedImage;

/**
 * Created by willie on 6/24/17.
 */
public class ActorViewFactory {
	private ImageLoader imageLoader;
	private SpriteFactory spriteFactory;
	private FontFactory fontFactory;

	public ActorViewFactory(ImageLoader imageLoader, SpriteFactory spriteFactory, FontFactory fontFactory) {
		Assert.notNull(imageLoader, "imageLoader can't be null");
		Assert.notNull(spriteFactory, "spriteFactory can't be null");
		Assert.notNull(fontFactory, "fontFactory can't be null");
		this.imageLoader = imageLoader;
		this.spriteFactory = spriteFactory;
		this.fontFactory = fontFactory;
	}

	public TextView createTextView(TextModel model) {
		Assert.notNull(model, "model can't be null");
		return new TextView(model, fontFactory);
	}

	public ImageView createImageView(BasicActorModel model, String path) {
		Assert.notNull(model, "model can't be null");
		Assert.notNull(path, "path can't be null");
		return new ImageView(model, imageLoader.loadImage(path));
	}

	public BigBalloonView createBigBalloonView(BigBalloonModel model) {
		Assert.notNull(model, "model can't be null");
		int color = model.getColor();
		BufferedImage[] sprites = spriteFactory.getBalloons()[color];
		return new BigBalloonView(model, sprites);
	}

	public LexiView createLexiView(LexiModel model) {
		Assert.notNull(model, "model can't be null");
		LexiView view = new LexiView(model);
		view.setWalkingSprites(spriteFactory.getLexi());
		view.setBlinkingSprites(spriteFactory.getLexiBlinking());
		view.setWavingSprites(spriteFactory.getLexiWaving());
		return view;
	}

	public ObstacleView createObstacleView(ObstacleModel model) {
		Assert.notNull(model, "model can't be null");
		return new ObstacleView(model);
	}
}
