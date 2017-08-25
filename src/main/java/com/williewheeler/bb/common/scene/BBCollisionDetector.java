package com.williewheeler.bb.common.scene;

import com.williewheeler.bb.common.actor.model.Animal;
import com.williewheeler.bb.common.actor.model.Balloon;
import com.williewheeler.bb.common.actor.model.Beat;
import com.williewheeler.bb.common.actor.model.Bengy;
import com.williewheeler.bb.common.actor.model.Bully;
import com.williewheeler.bb.common.actor.model.Judo;
import com.williewheeler.bb.common.actor.model.Obstacle;
import com.williewheeler.bb.common.actor.model.Teacher;
import com.williewheeler.bb.common.actor.model.Turntables;
import com.williewheeler.bb.common.actor.model.YardDuty;
import com.williewheeler.retroge.actor.model.Actor;
import com.williewheeler.retroge.scene.AbstractCollisionDetector;
import com.williewheeler.retroge.scene.CollisionCallback;
import com.williewheeler.retroge.scene.Scene;
import com.williewheeler.retroge.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by willie on 7/3/17.
 */
public final class BBCollisionDetector extends AbstractCollisionDetector {
	private static final Logger log = LoggerFactory.getLogger(BBCollisionDetector.class);
	
	private final CollisionCallback defaultCB = new DefaultCollisionCallback();
	private final CollisionCallback balloonBullyCB = new BalloonBullyCollisionCallback();
	private final CollisionCallback bullyAnimalCB = new BullyAnimalCollisionCallback();
	private final CollisionCallback defaultPlayerCB = new DefaultPlayerCollisionCallback();
	private final CollisionCallback playerAnimalCB = new PlayerAnimalCollisionCallback();
	
	@Override
	public void checkCollisions(Scene scene) {
		Assert.notNull(scene, "scene can't be null");
		
		BBScene bbScene = (BBScene) scene;

		final List<Balloon> balloons = bbScene.getBalloons();
		final List<Obstacle> obstacles = bbScene.getObstacles();
		final List<Judo> judos = bbScene.getJudos();
		final List<Bully> bullies = bbScene.getBullies();
		final List<YardDuty> yardDuties = bbScene.getYardDuties();
		final List<Teacher> teachers = bbScene.getTeachers();
		final List<Bengy> bengies = bbScene.getBengies();
		final List<Turntables> turntables = bbScene.getTurntables();
		final List<Beat> beats = bbScene.getBeats();
		final List<Animal> animals = bbScene.getAnimals();

		checkCollisions(bbScene, judos, obstacles, defaultCB);
		checkCollisions(bbScene, balloons, obstacles, defaultCB);
		checkCollisions(bbScene, balloons, judos, defaultCB);
		checkCollisions(bbScene, balloons, bullies, balloonBullyCB);
		checkCollisions(bbScene, balloons, yardDuties, defaultCB);
		checkCollisions(bbScene, balloons, teachers, defaultCB);
		checkCollisions(bbScene, balloons, bengies, defaultCB);
		checkCollisions(bbScene, balloons, turntables, defaultCB);
		checkCollisions(bbScene, balloons, beats, defaultCB);
		checkCollisions(bbScene, bullies, animals, bullyAnimalCB);

		checkPlayerCollision(bbScene, obstacles, defaultPlayerCB);
		checkPlayerCollision(bbScene, judos, defaultPlayerCB);
		checkPlayerCollision(bbScene, bullies, defaultPlayerCB);
		checkPlayerCollision(bbScene, yardDuties, defaultPlayerCB);
		checkPlayerCollision(bbScene, bengies, defaultPlayerCB);
		checkPlayerCollision(bbScene, turntables, defaultPlayerCB);
		checkPlayerCollision(bbScene, beats, defaultPlayerCB);
		checkPlayerCollision(bbScene, animals, playerAnimalCB);
	}
	
	private static class DefaultCollisionCallback implements CollisionCallback<Actor, Actor> {
		
		@Override
		public void handleCollision(Scene scene, Actor thisActor, Actor thatActor) {
			thisActor.die();
			thatActor.die();
		}
	}
	
	private static class BalloonBullyCollisionCallback implements CollisionCallback<Balloon, Bully> {
		
		@Override
		public void handleCollision(Scene scene, Balloon balloon, Bully bully) {
			balloon.die();
			bully.changeX(balloon.getDx());
			bully.changeY(balloon.getDy());
		}
	}
	
	private static class BullyAnimalCollisionCallback implements CollisionCallback<Bully, Actor> {
		
		@Override
		public void handleCollision(Scene scene, Bully bully, Actor animal) {
			animal.die();
		}
	}
	
	private static class DefaultPlayerCollisionCallback implements CollisionCallback<Actor, Actor> {
		
		@Override
		public void handleCollision(Scene scene, Actor playerActor, Actor actor) {
			log.trace("Player collided with {}", actor.getClass().getSimpleName());
			// Don't make the actor die. Just the player.
//			actor.die();
			scene.getPlayer().die();
		}
	}

	private static class PlayerAnimalCollisionCallback implements CollisionCallback<Actor, Animal> {
		
		@Override
		public void handleCollision(Scene scene, Actor playerActor, Animal animal) {
			log.trace("Player rescued {}", animal.getClass().getSimpleName());
			animal.rescue();
		}
	}
}
