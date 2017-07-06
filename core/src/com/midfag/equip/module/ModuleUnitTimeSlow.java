package com.midfag.equip.module;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.Shd;
import com.midfag.entity.ShdMove;
import com.midfag.equip.module.attr.ModuleAttributeDuration;
import com.midfag.equip.module.attr.ModuleAttributePushDamage;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Main;
import com.midfag.game.Phys;
import com.midfag.game.Enums.Rarity;

public class ModuleUnitTimeSlow extends ModuleUnit {

	/*
	public float base_push_damage=50;
	public float total_push_damage=50;
	*/
	public float base_time_slow;
	public float total_time_slow;



	public ModuleUnitTimeSlow()
	{
		name="Модуль 'конденсатор времени'";
		
		base_duration=5.0f;
		base_cooldown=5;
		base_time_slow=0.5f;
		
		Available_attribute_list.add(new ModuleAttributeDuration());
		
		tex=new Texture(Gdx.files.internal("icon_time_control.png"));

		rarity=Rarity.COMMON;
		
		
		generate();
		update_stats();
	}
	
	@Override
	public String get_description()
	{
		return "Замедляет время на 50%.";
	}
	
	@Override
	public void use(Entity _e)
	{
		duration=total_duration;
	}
	
	@Override
	public boolean can_use() {
		// TODO Auto-generated method stub
		return can_use_default();
	}
	


	
	@Override
	public void additional_update_stats()
	{
		total_time_slow=base_time_slow;
	}
	
	@Override
	public void update(Entity _e, float _d)
	{

			
			cooldown-=_d;
			if (cooldown<=0)
			{cooldown=0;}
			
			if (duration>0)
			{
				GScreen.time_speed_value*=0.5f;
			}
				
			

		
			if (duration>0)
			{
				duration-=GScreen.real_delta;
				//_e.rotate_block=true;
				
				if (duration<=0)
				{duration=0;
				cooldown=total_cooldown;}
			}
	}
	
	
}
