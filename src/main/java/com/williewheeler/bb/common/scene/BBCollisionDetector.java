package com.williewheeler.bb.common.scene;

import com.williewheeler.bb.common.actor.model.Animal;
import com.williewheeler.bb.common.actor.model.Balloon;
import com.williewheeler.bb.common.actor.model.Bully;
import com.williewheeler.bb.common.actor.model.Judo;
import com.williewheeler.bb.common.actor.model.Obstacle;
import com.williewheeler.retroge.actor.Actor;
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
	
	private final CollisionCallback defaultDB = new DefaultCollisionCallback();
	private final CollisionCallback balloonBullyCB = new BalloonBullyCollisionCallback();
	private final CollisionCallback bullyAnimalCB = new BullyAnimalCollisionCallback();
	private final CollisionCallback defaultPlayerCB = new DefaultPlayerCollisionCallback();
	private final CollisionCallback playerBullyCB = new PlayerBullyCollisionCallback();
	private final CollisionCallback playerAnimalCB = new PlayerAnimalCollisionCallback();
	
	@Override
	public void checkCollisions(Scene scene) {
		Assert.notNull(scene, "scene can't be null");
		
		BBScene bbScene = (BBScene) scene;

		final List<Balloon> balloons = bbScene.getBalloons();
		final List<Animal> animals = bbScene.getAnimals();
		final List<Obstacle> obstacles = bbScene.getObstacles();
		final List<Judo> judos = bbScene.getJudos();
		final List<Bully> bullies = bbScene.getBullies();

		checkCollisions(bbScene, judos, obstacles, defaultDB);
		checkCollisions(bbScene, balloons, obstacles, defaultDB);
		checkCollisions(bbScene, balloons, judos, defaultDB);
		checkCollisions(bbScene, balloons, bullies, balloonBullyCB);
		checkCollisions(bbScene, bullies, animals, bullyAnimalCB);
		
		checkPlayerCollision(bbScene, obstacles, defaultPlayerCB);
		checkPlayerCollision(bbScene, judos, defaultPlayerCB);
		checkPlayerCollision(bbScene, bullies, playerBullyCB);
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
			actor.die();
			scene.getPlayer().die();
		}
	}
	
	private static class PlayerBullyCollisionCallback implements CollisionCallback<Actor, Bully> {
		
		@Override
		public void handleCollision(Scene scene, Actor playerActor, Bully bully) {
			log.trace("Player collided with {}", bully.getClass().getSimpleName());
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
