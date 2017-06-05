package bb;

import java.awt.Dimension;

/**
 * Created by willie on 6/4/17.
 */
public class BBConfig {

	// Unscaled
	public static final int SCREEN_WIDTH_PX = 304;
	public static final int SCREEN_HEIGHT_PX = 256;
	public static final int ARENA_HEADER_HEIGHT_PX = 12;
	public static final int ARENA_HEADER_TEXT_OFFSET_PX = 50;
	public static final int ARENA_HEADER_LIVES_OFFSET_PX = 260;
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

	// Sprites
	public static final String SPRITE_SHEET_FILENAME = "bb-sprites.png";
	public static final Dimension SPRITE_SIZE_PX = new Dimension(16, 16);

	// Scaled
	public static final int K = 4;
	public static final int K_SCREEN_WIDTH_PX = K * SCREEN_WIDTH_PX;
	public static final int K_SCREEN_HEIGHT_PX = K * SCREEN_HEIGHT_PX;
	public static final int K_ARENA_HEADER_HEIGHT_PX = K * ARENA_HEADER_HEIGHT_PX;
	public static final int K_ARENA_HEADER_TEXT_OFFSET_PX = K * ARENA_HEADER_TEXT_OFFSET_PX;
	public static final int K_ARENA_FOOTER_HEIGHT_PX = K * ARENA_FOOTER_HEIGHT_PX;
	public static final int K_ARENA_FOOTER_LEVEL_LABEL_OFFSET_PX = K * ARENA_FOOTER_LEVEL_LABEL_OFFSET_PX;
	public static final int K_ARENA_FOOTER_LEVEL_VALUE_OFFSET_PX = K * ARENA_FOOTER_LEVEL_VALUE_OFFSET_PX;
	public static final int K_ARENA_PANE_HEIGHT_PX = K * ARENA_PANE_HEIGHT_PX;
	public static final int K_ARENA_MARGIN_LEFT_RIGHT_PX = K * ARENA_MARGIN_LEFT_RIGHT_PX;
	public static final int K_ARENA_BORDER_SIZE_PX = K * ARENA_BORDER_SIZE_PX;
	public static final int K_ARENA_OUTER_WIDTH_PX = K * ARENA_OUTER_WIDTH_PX;
	public static final int K_ARENA_OUTER_HEIGHT_PX = K * ARENA_OUTER_HEIGHT_PX;
	public static final int K_ARENA_INNER_WIDTH_PX = K * ARENA_INNER_WIDTH_PX;
	public static final int K_ARENA_INNER_HEIGHT_PX = K * ARENA_INNER_HEIGHT_PX;
	public static final int K_LARGE_FONT_PT = K * LARGE_FONT_PT;
	public static final int K_SMALL_FONT_PT = K * SMALL_FONT_PT;
	public static final int K_SPRITE_WIDTH_PX = K * SPRITE_SIZE_PX.width;
	public static final int K_SPRITE_HEIGHT_PX = K * SPRITE_SIZE_PX.height;

	public static final Dimension K_ARENA_HEADER_SIZE_PX = new Dimension(K_SCREEN_WIDTH_PX, K_ARENA_HEADER_HEIGHT_PX);
	public static final Dimension K_ARENA_FOOTER_SIZE_PX = new Dimension(K_SCREEN_WIDTH_PX, K_ARENA_FOOTER_HEIGHT_PX);
	public static final Dimension K_ARENA_PANE_SIZE_PX = new Dimension(K_SCREEN_WIDTH_PX, K_ARENA_PANE_HEIGHT_PX);
}
