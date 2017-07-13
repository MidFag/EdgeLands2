package com.midfag.entity.friends;

import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.equip.weapon.WeaponSimpleShotgun;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Main;

public class EntityTurret extends Entity {

	private float rotate_cooldown;
	private float body_rotate_cooldown;


	public EntityTurret(Vector2 _v)
	{
		super (_v);
		
		spr.setTexture(Assets.pyra_body[0]);
		pos=_v;
		

		
		id="pyra";
		
		armored[0]=new WeaponSimpleShotgun();
		armored[0].generate();
		armored[1]=null;
		
		if (armored[0]!=null)
		{
			armored[0].cd=(float) (Math.random()*1);
			armored[0].ammo=(int) armored[0].total_ammo_size;
		}
		
		if (armored[1]!=null)
		{
			armored[1].cd=(float) (Math.random()*1);
			armored[1].ammo=(int) armored[1].total_ammo_size;
		}	
		
		spr.setOrigin(50, 0);
		offset.y=50;
		can_rotate=false;
		is_enemy=false;
		
		friction=0.1f;
		speed=0.0f;
	}
	

	@Override
	public void draw_action(float _d) {

		float cold_rating=1.0f-buff_cold/(buff_cold+100.0f);
		spr.setColor(cold_rating, 1, 1, 1);
		

		
		if (rotate_cooldown<=0)
		{
			rotate_cooldown=0.1f;
			
			if ((is_see)&&(target!=null))
			{
				float a=target.pos.x-pos.x;
		    	float b=target.pos.y-pos.y-offset.y;
		    	//float c=(float) Math.sqrt((a*a)+(b*b));
		    	float c=(float) Math.toDegrees(Math.atan2(a, b));
		    	rot=180-c+180;
			
				//if ((impulse.x>0)&&(bottom_draw>=0)&&(bottom_draw<=3)){bottom_draw++;}
				if (c<0){c=360+c;}
		    	
		    	if (c>360){c=c-360;}
		    	//pl.spr.setRotation(360-c);
		    	if (c>347)
				{bottom_draw=0;}
		    	else
		    	{bottom_draw=(int) Math.round(c/22.5);}
			}
			
			
		}
		else
		{
			rotate_cooldown-=_d;
		}
		
		if (body_rotate_cooldown<=0)
		{
			draw_sprite+=1;
			if (draw_sprite>15) {draw_sprite=0;}
			
			body_rotate_cooldown=0.2f;
		}
		else
		{
			body_rotate_cooldown-=_d;
		}
		spr.translate(-25,-80);
		spr.setSize(100, 200);
		spr.setTexture(Assets.shadow);
		spr.draw(GScreen.batch);
		spr.translate(25,80);
		
		spr.setSize(100, 100);
		
		spr.translate(-5,-30);
		
		spr.setTexture(Assets.turret_body[bottom_draw]);
		spr.draw(GScreen.batch);
		spr.translate(5,30);
		draw_hp();
		
		

	}
}
