package com.sheza.bombie;

import org.andengine.entity.sprite.Sprite;

import com.badlogic.gdx.physics.box2d.Body;

public class ObjectEntity {

	//TODO: unused?
	private int x;
	private int y;
	//--end
	
	private Sprite sprite;
	private Body body;
	private boolean rotateable;
	
	public ObjectEntity() {
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public boolean isRotateable() {
		return rotateable;
	}

	public void setRotateable(boolean rotateable) {
		this.rotateable = rotateable;
	}
	
	
}
