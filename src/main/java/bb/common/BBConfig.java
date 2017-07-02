package bb.common;

import java.awt.Dimension;

/**
 * Created by willie on 6/4/17.
 */
public class BBConfig {

	// Mode names
	public static final String ATTRACT_MODE = "attractMode";
	public static final String GAME_MODE = "gameMode";
	public static final String ENTER_HIGH_SCORE_MODE = "enterHighScoreMode";

	// Frame rate
	public static final int FRAMES_PER_SECOND = 30;
	public static final int FRAME_PERIOD_MS = 1000 / FRAMES_PER_SECOND;

	// Sizes
	public static final int SCREEN_WIDTH_PX = 304;
	public static final int SCREEN_HEIGHT_PX = 256;
	public static final int ARENA_HEADER_HEIGHT_PX = 12;
	public static final int ARENA_HEADER_P1_ANCHOR_PX = 80;
	public static final int ARENA_FOOTER_HEIGHT_PX = 10;
	public static final int ARENA_FOOTER_LEVEL_LABEL_OFFSET_PX = 125;
	public static final int ARENA_FOOTER_LEVEL_VALUE_OFFSET_PX = 165;
	public static final int ARENA_PANE_HEIGHT_PX = SCREEN_HEIGHT_PX - (ARENA_HEADER_HEIGHT_PX + ARENA_FOOTER_HEIGHT_PX);
	public static final int ARENA_MARGIN_LEFT_RIGHT_PX = 4;
	public static final int ARENA_BORDER_SIZE_PX = 2;
	public static final int ARENA_OUTER_WIDTH_PX = SCREEN_WIDTH_PX - 2 * ARENA_MARGIN_LEFT_RIGHT_PX;
	public static final int ARENA_OUTER_HEIGHT_PX = ARENA_PANE_HEIGHT_PX;
	public static final int ARENA_INNER_WIDTH_PX = ARENA_OUTER_WIDTH_PX - 2 * ARENA_BORDER_SIZE_PX;
	public static final int ARENA_INNER_HEIGHT_PX = ARENA_OUTER_HEIGHT_PX - 2 * ARENA_BORDER_SIZE_PX;
	public static final int LARGE_FONT_PT = 9;
	public static final int SMALL_FONT_PT = 8;

	public static final Dimension SCREEN_SIZE_PX = new Dimension(SCREEN_WIDTH_PX, SCREEN_HEIGHT_PX);
	public static final Dimension ARENA_HEADER_SIZE_PX = new Dimension(SCREEN_WIDTH_PX, ARENA_HEADER_HEIGHT_PX);
	public static final Dimension ARENA_FOOTER_SIZE_PX = new Dimension(SCREEN_WIDTH_PX, ARENA_FOOTER_HEIGHT_PX);
	public static final Dimension ARENA_PANE_SIZE_PX = new Dimension(SCREEN_WIDTH_PX, ARENA_PANE_HEIGHT_PX);

	// Sprites
	public static final int SPRITE_WIDTH_PX = 16;
	public static final int SPRITE_HEIGHT_PX = 16;
	
	// Clearing around the player when enemies are initially placed
	public static final int CLEARING_RADIUS = 50;
}
