package com.sheza.bombie;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class ObjectFactory {

	// TODO: need some improvement
	public static ObjectEntity createObject(ObjectRegistry registry, int x,
			int y) {
		ObjectDefinition objDef = ObjectDefinitions.instance().get(registry);
		ObjectEntity object = null;
		Sprite sprite = null;
		Body body = null;
		if (objDef.getType() == ObjectType.PLAYER) {
			object = new ObjectPlayer();
		} else {
			object = new ObjectEntity();
		}
		if (objDef.isAnimated()) {
			sprite = new AnimatedSprite(x, y, (ITiledTextureRegion) Resources
					.instance().getTexture(registry), Game.instance()
					.getActivity().getVertexBufferObjectManager());
		} else {
			sprite = new Sprite(x, y,
					Resources.instance().getTexture(registry), Game.instance()
							.getActivity().getVertexBufferObjectManager());
		}

		FixtureDef fixtureDef = PhysicsFactory.createFixtureDef(0.8f, 0.0f, 0.0f);

		if (objDef.isMoveable()) {
			body = PhysicsFactory.createCircleBody(Game.instance().getWorld(),
					sprite, BodyType.DynamicBody, fixtureDef);
		} else {
			body = PhysicsFactory.createBoxBody(Game.instance().getWorld(),
					sprite, BodyType.StaticBody, fixtureDef);
		}

		body.setLinearDamping(10);
		body.setAngularDamping(10);

		// if (objDef.isMoveable()) {
		//
		// } else {
		// FixtureDef fixtureDef = PhysicsFactory.createFixtureDef(1f, 0.5f,
		// 0.5f);
		// body = PhysicsFactory.createBoxBody(Game.instance().getWorld(),
		// sprite, BodyType.DynamicBody, fixtureDef);
		// // body.setLinearDamping(10);
		// // body.setAngularDamping(10);
		// }

		object.setSprite(sprite);
		object.setBody(body);
		// object.setRotateable(ObjectDefinitions.instance().get(registry)
		// .isRotateable());
		Game.instance()
				.getWorld()
				.registerPhysicsConnector(
						new PhysicsConnector(sprite, body, objDef.isMoveable(),
								objDef.isRotateable()));
		return object;
	}
	// public static ObjectEntity createObject(ObjectRegistry registry, int x,
	// int y) {
	// ObjectEntity object = null;
	// Sprite sprite = null;
	// Body body = null;
	// if (registry == ObjectRegistry.PLAYER_HABSQ) {
	// object = new ObjectPlayer();
	// sprite = new AnimatedSprite(x, y, (ITiledTextureRegion) Resources
	// .instance().getTexture(registry), Game.instance()
	// .getActivity().getVertexBufferObjectManager());
	//
	// FixtureDef fixtureDef = PhysicsFactory.createFixtureDef(1, 0f, 0f);
	// body = PhysicsFactory.createBoxBody(Game.instance().getWorld(),
	// sprite, BodyType.DynamicBody, fixtureDef);
	//
	// } else if (registry == ObjectRegistry.BOX) {
	// object = new ObjectEntity();
	// sprite = new Sprite(x, y,
	// Resources.instance().getTexture(registry), Game.instance()
	// .getActivity().getVertexBufferObjectManager());
	//
	// FixtureDef fixtureDef = PhysicsFactory.createFixtureDef(1f, 0.5f,
	// 0.5f);
	// body = PhysicsFactory.createBoxBody(Game.instance().getWorld(),
	// sprite, BodyType.DynamicBody, fixtureDef);
	// body.setLinearDamping(10);
	// body.setAngularDamping(10);
	// } else {
	// //TODO: throw something
	// }
	// object.setSprite(sprite);
	// object.setBody(body);
	// object.setRotateable(ObjectDefinitions.instance().get(registry)
	// .isRotateable());
	// return object;
	// }
}
