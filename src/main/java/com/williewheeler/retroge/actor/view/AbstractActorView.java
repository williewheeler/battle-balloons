package com.williewheeler.retroge.actor.view;

import com.williewheeler.retroge.actor.model.Actor;

import java.awt.Graphics;

/**
 * Created by willie on 7/3/17.
 */
public abstract class AbstractActorView implements ActorView {

	@Override
	public void paint(Graphics g, Actor actor) {
		switch (actor.getState()) {
			case ENTERING:
				paintEntering(g, actor);
				break;
			case ACTIVE:
				paintActive(g, actor);
				break;
			case EXITING:
				paintExiting(g, actor);
				break;
		}
	}

	/**
	 * Override as desired.
	 *
	 * @param g
	 * @param actor
	 */
	public void paintEntering(Graphics g, Actor actor) {
	}

	public abstract void paintActive(Graphics g, Actor actor);

	/**
	 * Override as desired.
	 *
	 * @param g
	 * @param actor
	 */
	public void paintExiting(Graphics g, Actor actor) {
	}
}
