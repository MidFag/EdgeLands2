package com.midfag.entity.decorations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;

public class DecorBuildingRoof extends DecorBuilding {

	public DecorBuildingRoof(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();

		
		spr.setTexture(Assets.building_roof);
		icon=Assets.building_roof_icon;
		
		
		spr.setSize(100, 11);
		spr.setOrigin(50f, 0);
		
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorBuildingRoof(new Vector2());
	}

}
