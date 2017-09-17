package com.williewheeler.battleballoons.game.level;

/**
 * Created by willie on 7/4/17.
 */
public class Level {
	private int judos;
	private int obstacles;
	private int dogs;
	private int cats;
	private int parrots;
	private int bullies;
	private int teachers;
	private int yardDuties;
	private int bengies;

	public Level(
			int judos,
			int obstacles,
			int dogs,
			int cats,
			int parrots,
			int bullies,
			int teachers,
			int yardDuties,
			int bengies) {

		this.judos = judos;
		this.obstacles = obstacles;
		this.dogs = dogs;
		this.cats = cats;
		this.parrots = parrots;
		this.bullies = bullies;
		this.teachers = teachers;
		this.yardDuties = yardDuties;
		this.bengies = bengies;
	}

	public int getJudos() {
		return judos;
	}

	public int getObstacles() {
		return obstacles;
	}

	public int getDogs() {
		return dogs;
	}

	public int getCats() {
		return cats;
	}

	public int getParrots() {
		return parrots;
	}

	public int getBullies() {
		return bullies;
	}

	public int getTeachers() {
		return teachers;
	}

	public int getYardDuties() {
		return yardDuties;
	}

	public int getBengies() {
		return bengies;
	}
}
