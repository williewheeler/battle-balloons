package bb.framework;

import bb.framework.resource.AudioLoader;
import bb.framework.resource.FontLoader;
import bb.framework.resource.ImageLoader;

/**
 * Created by willie on 7/3/17.
 */
public interface GameContext {

	AudioLoader getAudioLoader();

	FontLoader getFontLoader();

	ImageLoader getImageLoader();
}
