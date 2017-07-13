package com.midfag.equip.module;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.Shd;
import com.midfag.entity.ShdMove;
import com.midfag.equip.module.attr.*;

import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;
import com.midfag.game.Enums.Rarity;

public class ModuleUnitPush extends ModuleUnit {

	/*
	public float base_push_damage=50;
	public float total_push_damage=50;
	*/
	public float base_push_damage;
	public float total_push_damage;



	public ModuleUnitPush()
	{
		name="Модуль 'торпеда'";
		
		base_duration=0.25f;
		base_cooldown=5;
		base_push_damage=50;
		
		Available_attribute_list.add(new ModuleAttributePushDamage());
		Available_attribute_list.add(new ModuleAttributeExplosionFire());
		Available_attribute_list.add(new ModuleAttributeExplosionIce());

		
		tex=new Texture(Gdx.files.internal("icon_push.png"));

		rarity=Rarity.COMMON;
		
		level=5;
		
		generate();
		update_stats();
	}
	
	@Override
	public String get_description()
	{
		return "Совершает рывок в указанном направлении. Наносит "+total_push_damage+" урона при столкновении.";
	}
	
	@Override
	public void use(Entity _e)
	{
		duration=total_duration;
		
		Assets.jet.play();
	}
	
	@Override
	public boolean can_use() {
		// TODO Auto-generated method stub
		return can_use_default();
	}
	


	
	@Override
	public void additional_update_stats()
	{
		total_push_damage=base_push_damage*level;
	}
	
	@Override
	public void update(Entity _e, float _d)
	{
			cooldown-=_d;
			if (cooldown<=0)
			{cooldown=0;}
			
			if (duration>0)
			{
				
				float spd=2000;
			
				float sx=spd*GScreen.sinR(360-_e.rot);
				float sy=spd*GScreen.cosR(360-_e.rot);
				
				Vector2 v=_e.pos;
				
				Phys near_object=null;
				near_object=GScreen.get_contact(_e.pos.x,_e.pos.y,_e.pos.x+sx*_d,_e.pos.y+sy*_d,sx/spd,sy/spd,spd*_d,true,false,true);
				
				if (near_object==null)
				{
					_e.move(sx,sy,_d);
					
					Shd shd=new ShdMove(new Vector2(_e.pos.x,_e.pos.y),v);
					shd.lifetime=0.2f;
					GScreen.Shd_list.add(shd);
				}
				else
				{
					duration=0f;
					use_end_skill(_e, _d);
					cooldown=total_cooldown;
				}
				
				int cx=(int)(_e.pos.x/300.0f);
				int cy=(int)(_e.pos.y/300.0f);
				
				//Main.batch.begin();
				//Main.batch.draw(Assets.point_start, cx*300+150, cy*300+150);
				//Main.batch.end();
				
				//System.out.println ("WTF? "+duration);
				
				for (int i=cx-1; i<=cx+1; i++)
				for (int j=cy-1; j<=cy+1; j++)
				if ((j>0)&&(j<30)&&(i>0)&&(i<30))
				for (int k=0; k<GScreen.cluster[j][i].Entity_list.size(); k++)
				{
						if (_e.pos.dst(GScreen.cluster[j][i].Entity_list.get(k).pos)<80)
						{
							duration=0f;
							use_end_skill(_e, _d);
							cooldown=total_cooldown;
							GScreen.cluster[j][i].Entity_list.get(k).hit_action(total_push_damage, true);

							Assets.crash.play();
							
							j=99999;
							i=99999;
							k=10000;
							break;
						}
				}
				
			

		}
			if (duration>0)
			{
				duration-=_d;
				_e.rotate_block=true;
				
				if (duration<=0)
					{duration=0;
					cooldown=total_cooldown;
					use_end_skill(_e, _d);}
			}
	}
	
	
}
