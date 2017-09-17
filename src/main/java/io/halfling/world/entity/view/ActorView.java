package io.halfling.world.entity.view;

import io.halfling.world.entity.model.Actor;

import java.awt.Graphics;

/**
 * Created by willie on 6/24/17.
 */
public interface ActorView {

	void paint(Graphics g, Actor actor);
}
