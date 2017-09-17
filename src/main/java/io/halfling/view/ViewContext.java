package io.halfling.view;

import io.halfling.view.resource.AudioLoader;
import io.halfling.view.resource.FontLoader;
import io.halfling.view.resource.ImageLoader;

/**
 * Created by willie on 7/3/17.
 */
public interface ViewContext {

	AudioLoader getAudioLoader();

	FontLoader getFontLoader();

	ImageLoader getImageLoader();
}
