package com.williewheeler.retroge;

import java.awt.Dimension;

/**
 * Created by willie on 7/3/17.
 */
public interface GameConfig {
	
	// World
	// TODO Generalize. This is currently hardcoded to what BB expects. [WLW]
//	public static final Dimension WORLD_SIZE = new Dimension(292, 234);
	public static final Dimension WORLD_SIZE = new Dimension(292, 230);
	
	int getFramePeriodMs();
}
