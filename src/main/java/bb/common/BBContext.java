package bb.common;

import bb.common.actor.view.ActorViewFactory;
import bb.common.factory.AudioFactory;
import bb.common.factory.FontFactory;
import bb.common.factory.SpriteFactory;
import bb.framework.io.FontLoader;
import bb.framework.io.ImageLoader;

/**
 * Created by willie on 6/30/17.
 */
public class BBContext {
	private FontLoader fontLoader;
	private ImageLoader imageLoader;
	private FontFactory fontFactory;
	private SpriteFactory spriteFactory;
	private AudioFactory audioFactory;
	private ActorViewFactory actorViewFactory;

	public BBContext() {
		this.fontLoader = new FontLoader();
		this.imageLoader = new ImageLoader();
		this.fontFactory = new FontFactory(fontLoader);
		this.spriteFactory = new SpriteFactory(imageLoader);
		this.audioFactory = new AudioFactory();
		this.actorViewFactory = new ActorViewFactory(fontFactory, spriteFactory);
	}

	public FontLoader getFontLoader() {
		return fontLoader;
	}

	public ImageLoader getImageLoader() {
		return imageLoader;
	}

	public FontFactory getFontFactory() {
		return fontFactory;
	}

	public SpriteFactory getSpriteFactory() {
		return spriteFactory;
	}

	public AudioFactory getAudioFactory() {
		return audioFactory;
	}

	public ActorViewFactory getActorViewFactory() {
		return actorViewFactory;
	}
}
