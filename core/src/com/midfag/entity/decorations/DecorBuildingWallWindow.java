package com.midfag.entity.decorations;


import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;


public class DecorBuildingWallWindow extends DecorBuilding {
	
	
	//private float alpha=1;

	public DecorBuildingWallWindow(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();

		
		spr.setTexture(Assets.beton_wall_window);
		icon=Assets.beton_wall_window_icon;
		spr.setSize(spr.getTexture().getWidth(), spr.getTexture().getHeight());
		spr.setOrigin(47.0f, 0f);

		
		diagonal=true;
		//spr.setOrigin(10.0f, 65);
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorBuildingWallWindow(new Vector2());
	}
	
	@Override
	public void some_draw()
	{
	
	}
	

	

}
