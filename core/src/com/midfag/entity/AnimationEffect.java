package com.midfag.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.game.GScreen;
import com.midfag.game.Main;


public class AnimationEffect {
	
	public Texture tex[]=new Texture[20];
	
	public int frame;
	public int max_frame;
	
	public float base_timer=1;
	public float timer=base_timer;
	
	public float alpha=1;
	
	public Vector2 v;
	public float offset_x;
	public float offset_y;
	
	public float size_x=200;
	public float size_y=200;
	
	
	//public 
	
	public AnimationEffect(Vector2 _v)
	{
		v=_v;
		
		offset_x+=-size_x/2f;
		offset_y+=-size_y/2f;
	}
	
	public void do_animation(float _d)
	{
		

		
		GScreen.batch.setColor(1, 1, 1, alpha*timer/base_timer);
		GScreen.batch.draw(tex[frame],v.x+offset_x,v.y+offset_y,size_x,size_y);
		
		Main.font_big.draw(GScreen.batch, ""+alpha, v.x, v.y);
		
		if (frame<max_frame)
		{
			GScreen.batch.setColor(1, 1, 1, alpha*(1-timer/base_timer));
			GScreen.batch.draw(tex[frame+1],v.x+offset_x,v.y+offset_y,size_x,size_y);
		}
		
		
		timer-=_d;
		
		if (timer<=0)
		{
			timer=base_timer;
			frame++;
		}
		
		
	}
	
	/*
	public void set_color()
	{
		GScreen.batch.setColor(1, 1, 1, alpha);
	}*/

}
