package com.sheza.bombie;

import java.util.HashMap;
import java.util.Map;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class Resources {

	private static Resources instance;

	// private SimpleBaseGameActivity activity;

	private Map<ObjectRegistry, ITextureRegion> textures = new HashMap<ObjectRegistry, ITextureRegion>();

	public static Resources instance() {
		if (Resources.instance == null) {
			Resources.instance = new Resources();
		}
		return Resources.instance;
	}

	public Resources() {
	}

	// public void setActivity(SimpleBaseGameActivity activity) {
	// this.activity = activity;
	// }

	public void init() {
		initResources();
	}

	public void initResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		ObjectDefinitions objDefs = ObjectDefinitions.instance();

		for (ObjectRegistry registry : objDefs.keySet()) {
			ObjectDefinition resourceDef = objDefs.get(registry);
			ITextureRegion texture = Resources.loadResource(resourceDef);
			textures.put(registry, texture);
		}
	}

	public static ITextureRegion loadResource(ObjectDefinition resourceDef) {
		BitmapTextureAtlas textureAtlas = new BitmapTextureAtlas(Game
				.instance().getActivity().getTextureManager(),
				resourceDef.getWidth(), resourceDef.getHeight(),
				TextureOptions.BILINEAR);
		ITextureRegion texture = null;
		if (resourceDef.isTiled()) {
			texture = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(textureAtlas, Game.instance()
							.getActivity(), resourceDef.getFilename(), 0, 0,
							resourceDef.getNbOfColumns(), resourceDef
									.getNbOfRows());
		} else {
			texture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
					textureAtlas, Game.instance().getActivity(),
					resourceDef.getFilename(), 0, 0);
		}
		textureAtlas.load();
		return texture;
	}

	public ITextureRegion getTexture(ObjectRegistry type) {
		return textures.get(type);
	}

}
