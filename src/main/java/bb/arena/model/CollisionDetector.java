package bb.arena.model;

import bb.arena.event.EntityState;
import bb.arena.event.ArenaEvents;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by willie on 6/15/17.
 */
public class CollisionDetector {
	private ArenaModel arenaModel;

	public CollisionDetector(ArenaModel arenaModel) {
		this.arenaModel = arenaModel;
	}

	/**
	 * @return boolean indicating whether the player died
	 */
	public boolean checkCollisions() {
		checkPlayerBalloonCollisions();
		checkJudoObstacleCollisions();

		if (checkPlayerObstacleCollision()) {
			return true;
		}
		if (checkPlayerJudoCollision()) {
			return true;
		}

		return false;
	}

	private void checkPlayerBalloonCollisions() {
		List<Balloon> playerBalloons = arenaModel.getPlayerBalloons();
		List<Obstacle> obstacles = arenaModel.getObstacles();
		List<Judo> judos = arenaModel.getJudos();
		Player player = arenaModel.getPlayer();

		checkBalloons:
		for (ListIterator<Balloon> bit = playerBalloons.listIterator(); bit.hasNext();) {
			Balloon balloon = bit.next();
			for (ListIterator<Obstacle> oit = obstacles.listIterator(); oit.hasNext();) {
				Obstacle obstacle = oit.next();
				if (balloon.collision(obstacle)) {
					player.incrementScore(Obstacle.SCORE);
					bit.remove();
					oit.remove();
					continue checkBalloons;
				}
			}
			for (ListIterator<Judo> jit = judos.listIterator(); jit.hasNext();) {
				Judo judo = jit.next();
				if (judo.getState() == EntityState.ACTIVE && balloon.collision(judo)) {
					player.incrementScore(Judo.SCORE);
					judo.setState(EntityState.EXITING);
					bit.remove();
					arenaModel.fireEvent(ArenaEvents.JUDO_HIT);
					continue checkBalloons;
				}
			}
		}
	}

	private void checkJudoObstacleCollisions() {
		List<Obstacle> obstacles = arenaModel.getObstacles();
		List<Judo> judos = arenaModel.getJudos();
		Player player = arenaModel.getPlayer();

		checkJudos:
		for (ListIterator<Judo> jit = judos.listIterator(); jit.hasNext();) {
			Judo judo = jit.next();
			for (ListIterator<Obstacle> oit = obstacles.listIterator(); oit.hasNext();) {
				Obstacle obstacle = oit.next();
				if (judo.getState() == EntityState.ACTIVE && judo.collision(obstacle)) {
					player.incrementScore(Judo.SCORE);
					player.incrementScore(Obstacle.SCORE);
					judo.setState(EntityState.EXITING);
					oit.remove();
					arenaModel.fireEvent(ArenaEvents.JUDO_HIT);
					continue checkJudos;
				}
			}
		}
	}

	private boolean checkPlayerObstacleCollision() {
		List<Obstacle> obstacles = arenaModel.getObstacles();
		Player player = arenaModel.getPlayer();

		if (player.getState() == EntityState.ACTIVE) {
			for (ListIterator<Obstacle> it = obstacles.listIterator(); it.hasNext(); ) {
				Obstacle obstacle = it.next();
				if (player.collision(obstacle)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkPlayerJudoCollision() {
		List<Judo> judos = arenaModel.getJudos();
		Player player = arenaModel.getPlayer();

		if (player.getState() == EntityState.ACTIVE) {
			for (ListIterator<Judo> it = judos.listIterator(); it.hasNext(); ) {
				Judo judo = it.next();
				if (judo.getState() == EntityState.ACTIVE && player.collision(judo)) {
					return true;
				}
			}
		}
		return false;
	}

}
