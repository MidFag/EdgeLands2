package com.midfag.entity;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.midfag.game.Assets;
import com.midfag.game.Main;


public class AnimationEffectExpl extends AnimationEffect {
	
	public AnimationEffectExpl(Vector2 _v, float _ox, float _oy)
	{
		super(_v);
		
		for (int i=0; i<6; i++)
		{tex[i]=Assets.effect_explosion[i];}
		
		base_timer=0.03f;
		timer=0.03f;
		max_frame=5;
		
		alpha=1f;
		
		size_x=500;
		size_y=500;
		
		offset_x=-size_x/2f+10;
		offset_y=-size_y/2f+30;
		
		
		

	}
	
	public void do_animation(float _d)
	{
		
		Main.batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);
		
		float c=timer/(base_timer*1.0f);
		
		Main.batch.setColor(c, c, c, 1);
		Main.batch.draw(tex[frame],v.x+offset_x,v.y+offset_y,size_x,size_y);
		
		c=1-c;
		
		if (frame<max_frame)
		{
			Main.batch.setColor(c, c, c, 1);
			Main.batch.draw(tex[frame+1],v.x+offset_x,v.y+offset_y,size_x,size_y);
		}
		
		Main.batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		
		timer-=_d;
		
		if (timer<=0)
		{
			timer=base_timer;
			frame++;
		}
		
		
	}


}
