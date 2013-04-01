package com.sheza.bombie;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class GameActivity extends SimpleBaseGameActivity {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	public GameActivity() {
		logger.log(Level.INFO, "Build GameActivity");
		Game.instance().setActivity(this);
	}

	@Override
	public EngineOptions onCreateEngineOptions() {
		logger.log(Level.INFO, "Create Engine Options");
		Game.instance().initCamera();
		Game.instance().initEngineOptions();
		return Game.instance().getEngineOptions();
	}

	@Override
	protected void onCreateResources() {
		logger.log(Level.INFO, "Create Resources");
		Resources.instance().init();
		Game.instance().initControllerResource();
		logger.log(Level.INFO, "Resources created");
	}

	@Override
	protected Scene onCreateScene() {
		logger.log(Level.INFO, "Create Scene");
		Game.instance().initPhysicsWorld();
		Game.instance().initScene();
		Game.instance().initController();
		logger.log(Level.INFO, "Scene created");

		// TODO: move this code to Level
		LevelGame level = null;
		try {
			level = Levels.instance().loadLevel(LevelRegistry.DESERT);
		} catch (TMXLoadException e) {
			e.printStackTrace();
		}
		Game.instance().setLevel(level);

		ObjectPlayer obj = (ObjectPlayer) ObjectFactory.createObject(
				ObjectRegistry.PLAYER_HABSQ, 100, 100);
		Game.instance().setPlayer(obj);

		 ObjectEntity box = ObjectFactory.createObject(ObjectRegistry.BOX,
		 200,
		 200);
		 Game.instance().addObject(box);
		//
		// ObjectEntity box2 = ObjectFactory.createObject(ObjectRegistry.BOX,
		// 200,
		// 232);
		// Game.instance().addObject(box2);

		//TODO: experimental code, putting several boxes
		for (int i = 0; i < 10; i += 2) {
			for (int j = 0; j < 10; j += 2) {
				ObjectEntity ibox = ObjectFactory.createObject(
						ObjectRegistry.IMMOVABLE_BOX, i * 33, j * 33);
				Game.instance().addObject(ibox);
			}
		}
		return Game.instance().getScene();
	}

}
