package bb.framework;

import bb.framework.resource.AudioLoader;
import bb.framework.resource.FontLoader;
import bb.framework.resource.ImageLoader;

/**
 * Created by willie on 7/3/17.
 */
public class BasicGameContext implements GameContext {
	private AudioLoader audioLoader;
	private FontLoader fontLoader;
	private ImageLoader imageLoader;

	public BasicGameContext() {
		this.audioLoader = new AudioLoader();
		this.fontLoader = new FontLoader();
		this.imageLoader = new ImageLoader();
	}

	@Override
	public AudioLoader getAudioLoader() {
		return audioLoader;
	}

	@Override
	public FontLoader getFontLoader() {
		return fontLoader;
	}

	@Override
	public ImageLoader getImageLoader() {
		return imageLoader;
	}
}
