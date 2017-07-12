package com.midfag.entity;

import com.badlogic.gdx.math.Vector2;
import com.midfag.game.Assets;


public class AnimationEffectShield extends AnimationEffect {
	
	public AnimationEffectShield(Vector2 _v, float _ox, float _oy)
	{
		super(_v);
		
		for (int i=0; i<13; i++)
		{tex[i]=Assets.effect_shield[i];}
		
		base_timer=0.03f;
		timer=0.03f;
		max_frame=12;
		
		alpha=0.20f;
		
		size_x=300;
		size_y=300;
		
		offset_x=-size_x/2f+10;
		offset_y=-size_y/2f+30;
		
		
		

	}


}
