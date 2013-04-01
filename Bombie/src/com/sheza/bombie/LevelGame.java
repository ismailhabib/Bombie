package com.sheza.bombie;

import org.andengine.extension.tmx.TMXTiledMap;

public class LevelGame {

	private TMXTiledMap tiledMap;
	
	private ObjectEntity[] objects;

	public TMXTiledMap getTiledMap() {
		return tiledMap;
	}

	public void setTiledMap(TMXTiledMap tiledMap) {
		this.tiledMap = tiledMap;
	}

	public ObjectEntity[] getObjects() {
		return objects;
	}

	public void setObjects(ObjectEntity[] objects) {
		this.objects = objects;
	}
	
	
}
