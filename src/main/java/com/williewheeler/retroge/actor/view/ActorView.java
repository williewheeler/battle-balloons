package com.williewheeler.retroge.actor.view;

import com.williewheeler.retroge.actor.model.Actor;

import java.awt.Graphics;

/**
 * Created by willie on 6/24/17.
 */
public interface ActorView {

	void paint(Graphics g, Actor actor);
}
