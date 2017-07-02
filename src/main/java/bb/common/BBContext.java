package bb.common;

import bb.common.view.ActorViewFactory;
import bb.common.view.AudioFactory;
import bb.common.view.FontFactory;
import bb.common.view.SpriteFactory;
import bb.framework.view.FontLoader;
import bb.framework.view.ImageLoader;

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
		this.actorViewFactory = new ActorViewFactory(imageLoader, spriteFactory, fontFactory);
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
