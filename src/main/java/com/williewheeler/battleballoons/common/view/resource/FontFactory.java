package com.williewheeler.battleballoons.common.view.resource;

import io.halfling.view.resource.FontLoader;

import java.awt.Font;

import static com.williewheeler.battleballoons.common.BBConfig.LARGE_FONT_PT;
import static com.williewheeler.battleballoons.common.BBConfig.SMALL_FONT_PT;

/**
 * Created by willie on 6/4/17.
 */
public class FontFactory {
	private static final String FONT_NAME = "Robotron";

	private Font largeFont;
	private Font smallFont;

	public FontFactory(FontLoader fontLoader) {
		fontLoader.loadFont("fonts/" + FONT_NAME + ".ttf");
		this.largeFont = new Font(FONT_NAME, Font.PLAIN, LARGE_FONT_PT);
		this.smallFont = new Font(FONT_NAME, Font.PLAIN, SMALL_FONT_PT);
	}

	public Font getLargeFont() {
		return largeFont;
	}

	public Font getSmallFont() {
		return smallFont;
	}
}
