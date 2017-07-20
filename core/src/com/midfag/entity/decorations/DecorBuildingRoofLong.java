package com.midfag.entity.decorations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;

public class DecorBuildingRoofLong extends DecorBuilding {

	public DecorBuildingRoofLong(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();

		
		spr.setTexture(Assets.building_roof_long);
		icon=Assets.building_roof_long_icon;
		
		
		spr.setSize(100, 58);
		spr.setOrigin(50f, 0);
		
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorBuildingRoofLong(new Vector2());
	}

}
