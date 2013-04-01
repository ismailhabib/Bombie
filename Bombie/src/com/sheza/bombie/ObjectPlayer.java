package com.sheza.bombie;

import org.andengine.entity.sprite.AnimatedSprite;

public class ObjectPlayer extends ObjectEntity {

	private Direction direction;

	public ObjectPlayer() {
		super();
	}

	public enum Direction {
		UP, DOWN, RIGHT, LEFT, NONE
	}

	public void setVelocity(float velocityX, float velocityY) {
		getBody().setLinearVelocity(velocityX, velocityY);

		Direction tDir = null;
		if (velocityX == 0f && velocityY == 0f) {
			tDir = Direction.NONE;
		} else if (Math.abs(velocityX) > Math.abs(velocityY)) {
			if (velocityX > 0) {
				tDir = Direction.RIGHT;
			} else {
				tDir = Direction.LEFT;
			}
		} else {
			if (velocityY > 0) {
				tDir = Direction.DOWN;
			} else {
				tDir = Direction.UP;
			}
		}
		changeDirection(tDir);
	}

	public AnimatedSprite getAnimatedSprite() {
		return (AnimatedSprite) getSprite();
	}

	public void changeDirection(Direction direction) {
		boolean isNewDirection = (this.direction != direction);
		if (isNewDirection) {
			this.direction = direction;
			if (direction == Direction.RIGHT)
				getAnimatedSprite().animate(new long[] { 100, 100, 100 }, 6, 8,
						true);
			else if (direction == Direction.LEFT)
				getAnimatedSprite().animate(new long[] { 100, 100, 100 }, 3, 5,
						true);
			else if (direction == Direction.UP)
				getAnimatedSprite().animate(new long[] { 100, 100, 100 }, 9,
						11, true);
			else if (direction == Direction.DOWN)
				getAnimatedSprite().animate(new long[] { 100, 100, 100 }, 0, 2,
						true);
			else {
				getAnimatedSprite().stopAnimation();
			}
		}
	}
}
