package com.williewheeler.bb.game.level;

/**
 * Created by willie on 7/4/17.
 */
public class Level {
	private int obstacles;
	private int judos;
	private int bullies;
	private int dogs;
	private int cats;

	public Level(int obstacles, int judos, int bullies, int dogs, int cats) {
		this.obstacles = obstacles;
		this.judos = judos;
		this.bullies = bullies;
		this.dogs = dogs;
		this.cats = cats;
	}

	public int getObstacles() {
		return obstacles;
	}

	public int getJudos() {
		return judos;
	}

	public int getBullies() {
		return bullies;
	}

	public int getDogs() {
		return dogs;
	}

	public int getCats() {
		return cats;
	}
}
