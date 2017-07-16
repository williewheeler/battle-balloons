package com.williewheeler.retroge.actor;

/**
 * Created by willie on 6/9/17.
 */
public class DirectionIntent {
	public boolean up, down, left, right;

	public void reset() {
		this.up = false;
		this.down = false;
		this.left = false;
		this.right = false;
	}
}
