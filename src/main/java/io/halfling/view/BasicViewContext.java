package io.halfling.view;

import io.halfling.view.resource.AudioLoader;
import io.halfling.view.resource.FontLoader;
import io.halfling.view.resource.ImageLoader;

/**
 * Created by willie on 7/3/17.
 */
public class BasicViewContext implements ViewContext {
	private AudioLoader audioLoader;
	private FontLoader fontLoader;
	private ImageLoader imageLoader;

	public BasicViewContext() {
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
