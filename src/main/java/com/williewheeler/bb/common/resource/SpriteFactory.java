package com.williewheeler.bb.common.resource;

import com.williewheeler.bb.common.actor.model.Bengy;
import com.williewheeler.bb.common.actor.model.Judo;
import com.williewheeler.bb.common.actor.model.Lexi;
import com.williewheeler.bb.common.actor.model.Teacher;
import com.williewheeler.bb.common.actor.model.YardDuty;
import com.williewheeler.retroge.resource.ImageLoader;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static com.williewheeler.bb.common.BBConfig.SPRITE_HEIGHT_PX;
import static com.williewheeler.bb.common.BBConfig.SPRITE_WIDTH_PX;

/**
 * Created by willie on 6/4/17.
 */
public class SpriteFactory {
	private BufferedImage[][] bigBalloons;

	private BufferedImage[] lexiWalking;
	private BufferedImage[] lexiEntering;
	private BufferedImage[] lexiExiting;
	private BufferedImage[] lexiBlinking;
	private BufferedImage[] lexiWaving;

	private BufferedImage[] judoWalking;
	private BufferedImage[] judoEntering;
	private BufferedImage[] judoExiting;

	private BufferedImage[] yardDutyWalking;
	private BufferedImage[] yardDutyEntering;
	private BufferedImage[] yardDutyExiting;

	private BufferedImage[] bengyWalking;
	private BufferedImage[] bengyEntering;
	private BufferedImage[] bengyExiting;

	private BufferedImage[] turntables;
	private BufferedImage[] beat;

	private BufferedImage[] bullyWalking;

	private BufferedImage[] teacherWalking;
	private BufferedImage[] teacherEntering;
	private BufferedImage[] teacherExiting;

	private BufferedImage[] dogWalking;
	private BufferedImage[] catWalking;
	private BufferedImage[] parrotWalking;

	public SpriteFactory(ImageLoader imageLoader) {
		BufferedImage sheet = imageLoader.loadImage("images/bb-sprites.png");

		this.bigBalloons = buildBigBalloonSprites(sheet);

		this.lexiWalking = buildWalkingSprites(sheet, 0);
		this.lexiEntering = spaghettify(lexiWalking[4], Lexi.ENTER_TTL);
		this.lexiExiting = spaghettify(lexiWalking[4], Lexi.EXIT_TTL);
		this.lexiBlinking = buildLexiBlinking(sheet);
		this.lexiWaving = buildLexiWaving(sheet);
		
		this.judoWalking = buildWalkingSprites(sheet, 1);
		this.judoEntering = spaghettify(judoWalking[4], Judo.ENTER_TTL);
		this.judoExiting = spaghettify(judoWalking[4], Judo.EXIT_TTL);

		this.yardDutyWalking = buildWalkingSprites(sheet, 7);
		this.yardDutyEntering = spaghettify(yardDutyWalking[4], YardDuty.ENTER_TTL);
		this.yardDutyExiting = spaghettify(yardDutyWalking[4], YardDuty.EXIT_TTL);

		this.bengyWalking = buildWalkingSprites(sheet, 6);
		this.bengyEntering = spaghettify(bengyWalking[4], Bengy.ENTER_TTL);
		this.bengyExiting = spaghettify(bengyWalking[4], Bengy.EXIT_TTL);

		this.turntables = buildSprites(sheet, 6, 13, 2);
		this.beat = buildBeatSprites(sheet);

		this.bullyWalking = buildWalkingSprites(sheet, 2);

		this.teacherWalking = buildWalkingSprites(sheet, 8);
		this.teacherEntering = spaghettify(teacherWalking[4], Teacher.ENTER_TTL);
		this.teacherExiting = spaghettify(teacherWalking[4], Teacher.EXIT_TTL);

		this.dogWalking = buildWalkingSprites(sheet, 3);
		this.catWalking = buildWalkingSprites(sheet, 4);
		this.parrotWalking = buildWalkingSprites(sheet, 5);
	}

	public BufferedImage[][] getBigBalloons() {
		return bigBalloons;
	}

	public BufferedImage[] getLexiWalking() {
		return lexiWalking;
	}
	
	public BufferedImage[] getLexiEntering() {
		return lexiEntering;
	}
	
	public BufferedImage[] getLexiExiting() {
		return lexiExiting;
	}
	
	public BufferedImage getLexiLife() {
		return lexiWalking[4];
	}

	public BufferedImage[] getLexiBlinking() {
		return lexiBlinking;
	}

	public BufferedImage[] getLexiWaving() {
		return lexiWaving;
	}

	public BufferedImage[] getJudoWalking() {
		return judoWalking;
	}

	public BufferedImage[] getJudoEntering() {
		return judoEntering;
	}

	public BufferedImage[] getJudoExiting() {
		return judoExiting;
	}

	public BufferedImage[] getYardDutyWalking() {
		return yardDutyWalking;
	}

	public BufferedImage[] getYardDutyEntering() {
		return yardDutyEntering;
	}

	public BufferedImage[] getYardDutyExiting() {
		return yardDutyExiting;
	}

	public BufferedImage[] getTeacherWalking() {
		return teacherWalking;
	}

	public BufferedImage[] getTeacherEntering() {
		return teacherEntering;
	}

	public BufferedImage[] getTeacherExiting() {
		return teacherExiting;
	}

	public BufferedImage[] getBullyWalking() {
		return bullyWalking;
	}

	public BufferedImage[] getBengyWalking() {
		return bengyWalking;
	}

	public BufferedImage[] getBengyEntering() {
		return bengyEntering;
	}

	public BufferedImage[] getBengyExiting() {
		return bengyExiting;
	}

	public BufferedImage[] getTurntables() {
		return turntables;
	}

	public BufferedImage[] getBeat() {
		return beat;
	}

	public BufferedImage[] getDogWalking() {
		return dogWalking;
	}

	public BufferedImage[] getCatWalking() {
		return catWalking;
	}

	public BufferedImage[] getParrotWalking() {
		return parrotWalking;
	}

	private BufferedImage[] buildWalkingSprites(BufferedImage sheet, int row) {
		BufferedImage[] sprites = new BufferedImage[16];
		for (int i = 0, col = 0; i < 16; i++) {
			if (i % 4 == 2) {
				sprites[i] = sprites[i - 2];
			} else {
				sprites[i] = buildSprite(sheet, row, col);
				col++;
			}
		}
		return sprites;
	}

	private BufferedImage[] buildBeatSprites(BufferedImage sheet) {
		BufferedImage[] sprites = new BufferedImage[4];
		sprites[0] = buildSprite(sheet, 6, 16);
		sprites[1] = buildSprite(sheet, 6, 17);
		sprites[2] = buildSprite(sheet, 6, 16);
		sprites[3] = buildSprite(sheet, 6, 18);
		return sprites;
	}

	private BufferedImage[] buildLexiBlinking(BufferedImage sheet) {
		return new BufferedImage[] {
				lexiWalking[4],
				buildSprite(sheet, 0, 13)
		};
	}

	private BufferedImage[] buildLexiWaving(BufferedImage sheet) {
		return new BufferedImage[] {
				buildSprite(sheet, 0, 14),
				buildSprite(sheet, 0, 15)
		};
	}

	private BufferedImage[] spaghettify(BufferedImage sprite, int ttl) {
		final int numFrames = ttl + 1;
		BufferedImage[] result = new BufferedImage[numFrames];
		int width = sprite.getWidth();
		int height = sprite.getHeight();
		
		// i is the size of the gap between slices
		for (int i = 0; i < numFrames; i++) {
			int adjHeight = height + i * (height - 1);
			result[i] = new BufferedImage(width, adjHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = result[i].createGraphics();
			for (int j = 0; j < height; j++) {
				BufferedImage slice = sprite.getSubimage(0, j, width, 1);
				g2.drawImage(slice, 0, (i + 1) * j, null);
			}
		}
		
		return result;
	}

	private BufferedImage[][] buildBigBalloonSprites(BufferedImage sheet) {
		final int numColors = 7;
		final int numRotations = 4;

		BufferedImage[][] sprites = new BufferedImage[numColors][numRotations];
		for (int i = 0; i < numColors; i++) {
			for (int j = 0; j < numRotations; j++) {
				// Balloons start at column 20
				int col = 20 + j;
				sprites[i][j] = buildSprite(sheet, i, col);
			}
		}
		return sprites;
	}

	private BufferedImage[] buildSprites(BufferedImage sheet, int row, int startCol, int numSprites) {
		BufferedImage[] sprites = new BufferedImage[numSprites];
		for (int i = 0; i < numSprites; i++) {
			sprites[i] = buildSprite(sheet, row, startCol + i);
		}
		return sprites;
	}

	private BufferedImage buildSprite(BufferedImage sheet, int row, int col) {
		int x = col * SPRITE_WIDTH_PX;
		int y = row * SPRITE_HEIGHT_PX;
		return sheet.getSubimage(x, y, SPRITE_WIDTH_PX, SPRITE_HEIGHT_PX);
	}
}
