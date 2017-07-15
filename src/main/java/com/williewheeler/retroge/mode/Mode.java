package com.williewheeler.retroge.mode;

import com.williewheeler.retroge.event.ModeListener;

/**
 * Created by willie on 7/1/17.
 */
public interface Mode {

	String getName();
	
	void start();
	
	void stop();
	
	void addModeListener(ModeListener listener);
}
