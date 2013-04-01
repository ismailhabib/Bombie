package com.sheza.bombie;

import java.util.HashMap;

public class ObjectDefinitions extends
		HashMap<ObjectRegistry, ObjectDefinition> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ObjectDefinitions instance = null;

	public static ObjectDefinitions instance() {
		if (ObjectDefinitions.instance == null) {
			ObjectDefinitions.instance = new ObjectDefinitions();
		}
		return ObjectDefinitions.instance;
	}

	public ObjectDefinitions() {
		this.put(ObjectRegistry.PLAYER_HABSQ, new ObjectDefinition.Builder()
				.filename("habsq_tiled.png").type(ObjectType.PLAYER).width(128)
				.height(128).isTiled(true).animated(true).moveable(true)
				.rotateable(false).nbOfColumns(3).nbOfRows(4).build());
		this.put(
				ObjectRegistry.BOX,
				new ObjectDefinition.Builder().filename("box.png")
						.type(ObjectType.MOVEABLE_OBSTACLE).width(32)
						.height(32).animated(false).moveable(true)
						.rotateable(true).build());
		this.put(
				ObjectRegistry.IMMOVABLE_BOX,
				new ObjectDefinition.Builder().filename("box.png")
						.type(ObjectType.IMMOVABLE_OBSTACLE).width(32)
						.height(32).animated(false).moveable(false)
						.rotateable(false).build());
	}
}
