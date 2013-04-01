package com.sheza.bombie;

import java.util.HashMap;

public class LevelDefinitions extends HashMap<LevelRegistry, LevelDefinition> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static LevelDefinitions instance = null;

	public static LevelDefinitions instance() {
		if (LevelDefinitions.instance == null) {
			LevelDefinitions.instance = new LevelDefinitions();
		}
		return LevelDefinitions.instance;
	}

	public LevelDefinitions() {
		this.put(LevelRegistry.DESERT,
				new LevelDefinition.Builder().filename("tmx/desert.tmx").build());

	}
}
