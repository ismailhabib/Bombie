package com.sheza.bombie;

import java.util.HashMap;

import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXProperties;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.extension.tmx.TMXTileProperty;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.TMXLoader.ITMXTilePropertiesListener;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.opengl.texture.TextureOptions;

public class Levels {

	private static Levels instance;

	private TMXLoader tmxLoader;

	public static Levels instance() {
		if (Levels.instance == null) {
			Levels.instance = new Levels();
		}
		return Levels.instance;
	}

	private Levels() {
		this.tmxLoader = new TMXLoader(Game.instance().getActivity()
				.getAssets(), Game.instance().getActivity().getEngine()
				.getTextureManager(), TextureOptions.BILINEAR_PREMULTIPLYALPHA,
				Game.instance().getActivity().getVertexBufferObjectManager(),
				new ITMXTilePropertiesListener() {

					@Override
					public void onTMXTileWithPropertiesCreated(
							TMXTiledMap pTMXTiledMap, TMXLayer pTMXLayer,
							TMXTile pTMXTile,
							TMXProperties<TMXTileProperty> pTMXTileProperties) {

					}

				});
	}

	public LevelGame loadLevel(LevelRegistry levelRegistry) throws TMXLoadException {
		LevelDefinition levelDefinition = LevelDefinitions.instance().get(
				levelRegistry);
		LevelGame level = new LevelGame();
		TMXTiledMap tiledMap = this.tmxLoader.loadFromAsset(levelDefinition
				.getFilename());
		level.setTiledMap(tiledMap);
		return level;
	}
}
