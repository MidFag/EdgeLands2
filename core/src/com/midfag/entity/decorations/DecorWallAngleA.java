package com.midfag.entity.decorations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;

public class DecorWallAngleA extends DecorStoneWall {

	public DecorWallAngleA(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();

		
		spr.setTexture(Assets.wall_angle_A);
		icon=Assets.wall_angle_A;
		
		
		spr.setSize(9, 43);
		spr.setOrigin(4.5f, 0);
		
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorWallAngleA(new Vector2());
	}

}
