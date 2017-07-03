package com.midfag.entity;


import com.badlogic.gdx.math.Vector2;

import com.midfag.game.Main;

public class ShdMove extends Shd {

	
	public ShdMove(Vector2 _s, Vector2 _e)
	{
		super (_s,_e);
		
		start=_s;
		end=_e;
		
		
		
	}
	
	@Override
	public void draw()
	{
		Main.shapeRenderer.setColor(0.8f,0.4f,0.2f,lifetime);
		Main.shapeRenderer.rectLine(start, end, 30);
	}
	
	
	
}
