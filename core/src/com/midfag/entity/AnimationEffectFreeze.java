package com.midfag.entity;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Main;


public class AnimationEffectFreeze extends AnimationEffect {
	
	public AnimationEffectFreeze(Vector2 _v, float _ox, float _oy)
	{
		super(_v);
		
		for (int i=0; i<11; i++)
		{tex[i]=Assets.effect_freeze[i];}
		
		base_timer=0.03f;
		timer=0.03f;
		max_frame=10;
		
		alpha=1.0f;
		
		size_x=600;
		size_y=600;
		
		offset_x=-size_x/2f+10;
		offset_y=-size_y/2f+30;
		
		
		

	}
	
	public void do_animation(float _d)
	{
		
		GScreen.batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);
		
		float c=timer/(base_timer*1.0f);
		
		GScreen.batch.setColor(c, c, c, 1);
		GScreen.batch.draw(tex[frame],v.x+offset_x,v.y+offset_y,size_x,size_y);
		
		c=1-c;
		
		if (frame<max_frame)
		{
			GScreen.batch.setColor(c, c, c, 1);
			GScreen.batch.draw(tex[frame+1],v.x+offset_x,v.y+offset_y,size_x,size_y);
		}
		
		GScreen.batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		
		timer-=_d;
		
		if (timer<=0)
		{
			timer=base_timer;
			frame++;
		}
		
		
	}


}
