package com.williewheeler.bb.common.actor.view;

import com.williewheeler.bb.common.BBConfig;
import com.williewheeler.bb.common.actor.model.Teacher;
import com.williewheeler.bb.common.resource.SpriteFactory;
import com.williewheeler.retroge.actor.model.Actor;
import com.williewheeler.retroge.actor.model.Direction;
import com.williewheeler.retroge.actor.view.AbstractActorView;
import com.williewheeler.retroge.util.Assert;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class TeacherView extends AbstractActorView {
	private static final int HALF_SPRITE_WIDTH = BBConfig.SPRITE_WIDTH_PX / 2;
	private static final int HALF_SPRITE_HEIGHT = BBConfig.SPRITE_HEIGHT_PX / 2;

	private SpriteFactory spriteFactory;

	public TeacherView(SpriteFactory spriteFactory) {
		Assert.notNull(spriteFactory, "spriteFactory can't be null");
		this.spriteFactory = spriteFactory;
	}

	@Override
	public void paintEntering(Graphics g, Actor actor) {
		final Teacher teacher = (Teacher) actor;
		final int spriteIndex = teacher.getEnterTtl();
		assert(spriteIndex >= 0);
		final BufferedImage sprite = spriteFactory.getTeacherEntering()[spriteIndex];
		final int xOffset = teacher.getX() - sprite.getWidth() / 2;
		final int yOffset = teacher.getY() - sprite.getHeight() / 2;
		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, null);
		g.translate(-xOffset, -yOffset);
	}

	@Override
	public void paintActive(Graphics g, Actor actor) {
		final Teacher teacher = (Teacher) actor;
		final int xOffset = teacher.getX() - HALF_SPRITE_WIDTH;
		final int yOffset = teacher.getY() - HALF_SPRITE_HEIGHT;
		final BufferedImage sprite = getWalkingSprite(teacher);

		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, BBConfig.SPRITE_WIDTH_PX, BBConfig.SPRITE_HEIGHT_PX, null);
		g.translate(-xOffset, -yOffset);
	}

	@Override
	public void paintExiting(Graphics g, Actor actor) {
		final Teacher teacher = (Teacher) actor;
		final int spriteIndex = Teacher.EXIT_TTL - teacher.getExitTtl();
		assert(spriteIndex >= 0);
		final BufferedImage sprite = spriteFactory.getTeacherExiting()[spriteIndex];
		final int xOffset = teacher.getX() - sprite.getWidth() / 2;
		final int yOffset = teacher.getY() - sprite.getHeight() / 2;
		g.translate(xOffset, yOffset);
		g.drawImage(sprite, 0, 0, null);
		g.translate(-xOffset, -yOffset);
	}

	private BufferedImage getWalkingSprite(Teacher teacher) {
		Direction direction = teacher.getDirection();
		int walkCounter = teacher.getWalkCounter();
		int walkIndex = SpriteUtil.getWalkingSpriteIndex(direction, walkCounter);
		return spriteFactory.getTeacherWalking()[walkIndex];
	}
}
