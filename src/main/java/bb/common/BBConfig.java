package bb.common;

import retroge.GameConfig;

import java.awt.Dimension;

/**
 * Created by willie on 6/4/17.
 */
public class BBConfig implements GameConfig {

	// TODO Move all these into member fields.
	// Want to be able to pass a config object around. [WLW]

	// Mode names
	public static final String ATTRACT_MODE = "attractMode";
	public static final String GAME_MODE = "gameMode";

	// Frame rate
	public static final int FRAMES_PER_SECOND = 30;
	public static final int FRAME_PERIOD_MS = 1000 / FRAMES_PER_SECOND;

	// Screen
	public static final int SCREEN_WIDTH_PX = 304;
	public static final int SCREEN_HEIGHT_PX = 256;
	public static final int SCREEN_SCALE_BY = 3;
	public static final int LARGE_FONT_PT = 9;
	public static final int SMALL_FONT_PT = 8;
	public static final Dimension SCREEN_SIZE_PX = new Dimension(SCREEN_WIDTH_PX, SCREEN_HEIGHT_PX);

	// Sprites
	public static final int SPRITE_WIDTH_PX = 16;
	public static final int SPRITE_HEIGHT_PX = 16;

	@Override
	public int getFramePeriodMs() {
		return FRAME_PERIOD_MS;
	}
}
