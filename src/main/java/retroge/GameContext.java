package retroge;

import retroge.resource.AudioLoader;
import retroge.resource.FontLoader;
import retroge.resource.ImageLoader;

/**
 * Created by willie on 7/3/17.
 */
public interface GameContext {

	AudioLoader getAudioLoader();

	FontLoader getFontLoader();

	ImageLoader getImageLoader();
}
