package com.sheza.bombie;

import java.util.logging.Logger;

import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl.IOnScreenControlListener;
import org.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.opengl.GLES20;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Game {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	private static Game instance;

	private static int CAMERA_WIDTH = 480;
	private static int CAMERA_HEIGHT = 320;
	private static int PLAYER_VELOCITY = 3;

	private EngineOptions engineOptions;
	private Scene scene;
	private BoundCamera camera;
	private PhysicsWorld world;
	private BaseOnScreenControl baseOnScreenControl;
	private SimpleBaseGameActivity activity;
	private LevelGame level;

	private ObjectPlayer player;

	private ITextureRegion controllerBaseTexture;
	private ITextureRegion controllerKnobTexture;

	public static Game instance() {
		if (Game.instance == null) {
			Game.instance = new Game();
		}
		return Game.instance;
	}

	public void initEngineOptions() {
		this.engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(
						CAMERA_WIDTH, CAMERA_HEIGHT), this.camera);
	}

	public void initCamera() {
		this.camera = new BoundCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
	}

	public void initPhysicsWorld() {
		this.world = new FixedStepPhysicsWorld(50, new Vector2(0, 0), false);
	}

	public void initScene() {
		this.world = new FixedStepPhysicsWorld(50, new Vector2(0, 0), false);
		this.scene = new Scene();
		scene.registerUpdateHandler(world);
	}

	public void initControllerResource() {
		ObjectDefinition controllerBaseDef = new ObjectDefinition.Builder()
				.type(ObjectType.CONTROLLER)
				.filename("onscreen_control_base.png").width(128).height(128)
				.build();

		ObjectDefinition controllerKnobDef = new ObjectDefinition.Builder()
				.type(ObjectType.CONTROLLER)
				.filename("onscreen_control_knob.png").width(128).height(128)
				.build();

		this.controllerBaseTexture = Resources.loadResource(controllerBaseDef);

		this.controllerKnobTexture = Resources.loadResource(controllerKnobDef);

	}

	public void initController() {

		this.baseOnScreenControl = new DigitalOnScreenControl(0, CAMERA_HEIGHT
				- controllerBaseTexture.getHeight(), getCamera(),
				controllerBaseTexture, controllerKnobTexture, 0.1f,
				getActivity().getVertexBufferObjectManager(),
				new IOnScreenControlListener() {

					@Override
					public void onControlChange(
							BaseOnScreenControl pBaseOnScreenControl,
							float pValueX, float pValueY) {
						Game.instance()
								.getPlayer()
								.setVelocity(pValueX * PLAYER_VELOCITY,
										pValueY * PLAYER_VELOCITY);
					}
				});

		baseOnScreenControl.getControlBase().setBlendFunction(
				GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		baseOnScreenControl.getControlBase().setAlpha(0.5f);
		baseOnScreenControl.getControlBase().setScaleCenter(0, 128);
		baseOnScreenControl.getControlBase().setScale(1.0f);
		baseOnScreenControl.getControlKnob().setScale(1.0f);
		baseOnScreenControl.refreshControlKnobPosition();
		getScene().setChildScene(baseOnScreenControl);
	}

	public void setActivity(SimpleBaseGameActivity activity) {
		this.activity = activity;
	}

	public void setPlayer(ObjectPlayer player) {
		this.player = player;
		addObject(player);
		getCamera().setChaseEntity(player.getSprite());
	}

	public SimpleBaseGameActivity getActivity() {
		return activity;
	}

	public BoundCamera getCamera() {
		return camera;
	}

	public PhysicsWorld getWorld() {
		return world;
	}

	public EngineOptions getEngineOptions() {
		return engineOptions;
	}

	public Scene getScene() {
		return scene;
	}

	public BaseOnScreenControl getController() {
		return this.baseOnScreenControl;
	}

	public ObjectPlayer getPlayer() {
		return this.player;
	}

	public void initWorldBoundary(int width, int height) {
		final VertexBufferObjectManager vertexBufferObjectManager = this
				.getActivity().getVertexBufferObjectManager();

		final Rectangle bottomOuter = new Rectangle(0, height - 2, width, 2,
				vertexBufferObjectManager);
		final Rectangle topOuter = new Rectangle(0, 0, width, 2,
				vertexBufferObjectManager);
		final Rectangle leftOuter = new Rectangle(0, 0, 2, height,
				vertexBufferObjectManager);
		final Rectangle rightOuter = new Rectangle(width - 2, 0, 2, height,
				vertexBufferObjectManager);

		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0,
				0.5f, 0.5f);
		PhysicsFactory.createBoxBody(getWorld(), bottomOuter,
				BodyType.StaticBody, wallFixtureDef);
		PhysicsFactory.createBoxBody(getWorld(), topOuter, BodyType.StaticBody,
				wallFixtureDef);
		PhysicsFactory.createBoxBody(getWorld(), leftOuter,
				BodyType.StaticBody, wallFixtureDef);
		PhysicsFactory.createBoxBody(getWorld(), rightOuter,
				BodyType.StaticBody, wallFixtureDef);

		getScene().attachChild(bottomOuter);
		getScene().attachChild(topOuter);
		getScene().attachChild(leftOuter);
		getScene().attachChild(rightOuter);

	}

	public void setLevel(LevelGame level) {
		this.level = level;
		int width = level.getTiledMap().getTileWidth()
				* level.getTiledMap().getTileColumns();
		int height = level.getTiledMap().getTileHeight()
				* level.getTiledMap().getTileRows();
		getScene().attachChild(level.getTiledMap().getTMXLayers().get(0));
		getCamera().setBounds(0, 0, width, height);
		getCamera().setBoundsEnabled(true);
		initWorldBoundary(width, height);
	}

	public void addObject(ObjectEntity obj) {
		getScene().attachChild(obj.getSprite());

	}
}
