package bb.game.screen;

import java.awt.Dimension;

import static bb.common.BBConfig.SCREEN_HEIGHT_PX;
import static bb.common.BBConfig.SCREEN_WIDTH_PX;

/**
 * Created by wwheeler on 7/15/17.
 */
public final class ArenaConfig {
	public static final int ARENA_HEADER_HEIGHT_PX = 12;
	public static final int ARENA_HEADER_P1_ANCHOR_PX = 80;
	public static final int ARENA_FOOTER_HEIGHT_PX = 10;
	public static final int ARENA_FOOTER_LEVEL_LABEL_OFFSET_PX = 125;
	public static final int ARENA_FOOTER_LEVEL_VALUE_OFFSET_PX = 165;
	public static final int ARENA_PANE_HEIGHT_PX = SCREEN_HEIGHT_PX - (ARENA_HEADER_HEIGHT_PX + ARENA_FOOTER_HEIGHT_PX);
	public static final int ARENA_MARGIN_LEFT_RIGHT_PX = 4;
	public static final int ARENA_OUTER_WIDTH_PX = SCREEN_WIDTH_PX - 2 * ARENA_MARGIN_LEFT_RIGHT_PX;
	public static final int ARENA_OUTER_HEIGHT_PX = ARENA_PANE_HEIGHT_PX;
	
	public static final Dimension ARENA_HEADER_SIZE_PX = new Dimension(SCREEN_WIDTH_PX, ARENA_HEADER_HEIGHT_PX);
	public static final Dimension ARENA_PANE_SIZE_PX = new Dimension(ARENA_OUTER_WIDTH_PX, ARENA_OUTER_HEIGHT_PX);
	public static final Dimension ARENA_FOOTER_SIZE_PX = new Dimension(SCREEN_WIDTH_PX, ARENA_FOOTER_HEIGHT_PX);
	
	// Clearing around the player when enemies are initially placed
	public static final int CLEARING_RADIUS = 50;
}
