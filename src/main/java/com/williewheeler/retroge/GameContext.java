package com.williewheeler.retroge;

import com.williewheeler.retroge.resource.AudioLoader;
import com.williewheeler.retroge.resource.FontLoader;
import com.williewheeler.retroge.resource.ImageLoader;

/**
 * Created by willie on 7/3/17.
 */
public interface GameContext {

	AudioLoader getAudioLoader();

	FontLoader getFontLoader();

	ImageLoader getImageLoader();
}
