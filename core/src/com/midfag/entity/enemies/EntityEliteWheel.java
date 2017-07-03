package com.midfag.entity.enemies;




import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.AnimationEffectExpl;
import com.midfag.entity.Entity;
import com.midfag.entity.Shd;
import com.midfag.entity.ShdFire;
import com.midfag.entity.ShdMove;


import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;

import com.midfag.game.Main;


public class EntityEliteWheel extends Entity {
	
	//Sprite spr=new Sprite(new Texture(Gdx.files.internal("barrel.png")));
	

	private float rotate_cooldown;
	private float body_rotate_cooldown;
	
	private float prepare=(float) (Math.random()*2);
	
	private float boom_cooldown=5f;
	//private int body_rotate;
	
	public EntityEliteWheel(Vector2 _v,boolean _custom)
	{
		super (_v, _custom);
		
		spr.setTexture(Assets.wheel_elite_body[0]);
		pos=_v;
		
		custom_phys=_custom;
		
		id="elite_wheel";
		
		
		armored[0]=null;
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
		
		spr.setOrigin(50, 20);
		offset.y=50;
		can_rotate=false;
		
		
		armored_shield.total_value*=5;
		armored_shield.value=armored_shield.total_value;
		
		
		speed=500;
	}
	
	public void boom_check()
	{
		
		
		
		if (boom_cooldown<=0)
		{
			Assets.expl.play(0.25f);
			
			Effect.add(new AnimationEffectExpl(pos,-spr.getOriginX(),-spr.getOriginY()));
			
			if (pos.dst(GScreen.pl.pos)<100)
			{GScreen.pl.buff_burn+=15;}
			
			
			boom_cooldown=5f;
		}
	}
	
	@Override
	public void draw_action(float _d) {

		//GL20.glBlendFunc(GL_ONE, GL_ONE); 02.07.2017 06:07:36
		if ((rotate_cooldown<=0)&&(prepare>0))
		{
			rotate_cooldown=0.1f;
			
			float a=GScreen.pl.pos.x-pos.x;
	    	float b=GScreen.pl.pos.y-pos.y;
	    	//float c=(float) Math.sqrt((a*a)+(b*b));
	    	float c=(float) Math.toDegrees(Math.atan2(a, b));
	    	//spr.setRotation(180-c);
	    	
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
		
		if (prepare>0)
		{
			speed=500;
		}
		else
		if (prepare>-0.1f)
		{
			if (speed!=500)
			{
				rot+=GScreen.rnd(20)-1;
			}
			speed=500;
		}
		else
		if (prepare>-0.5f)
		{
			
			speed=10;
			float spd=2500*(1f+(prepare+0.25f)*2f);
			
			float sx=spd*GScreen.sinR(360f-rot);
			float sy=spd*GScreen.cosR(360f-rot);
			
			Vector2 v=pos;
			
			
			
			Phys near_object=null;
			near_object=GScreen.get_contact(pos.x,pos.y,pos.x+sx*_d,pos.y+sy*_d,sx/spd,sy/spd,spd*_d,true,false,true);
			
			if (near_object==null)
			{
				move(sx,sy,_d);
				
				Shd shd=new ShdFire(new Vector2(pos.x,pos.y),v);
				shd.lifetime=0.2f;
				GScreen.Shd_list.add(shd);
			}
			else
			{
				prepare=1;
				
			}
			
			
			
			if (pos.dst(GScreen.pl.pos)<50)
			{
				speed=500;
				prepare=1;
				
				GScreen.pl.hit_action(75,false);
				
				Assets.crash.play(0.25f);
			}
			
			boom_check();
		}
		else
		{
			speed=500;
			prepare=1;
			
			boom_check();
		}
		
		boom_cooldown-=_d;
		
		prepare-=_d;
		
		spr.translate(-5,-50);
		spr.setSize(100, 200);
		spr.setTexture(Assets.shadow);
		spr.draw(Main.batch);
		spr.translate(5,30);
		
		spr.setSize(100, 100);
		
		spr.setColor(Color.BLACK);
		spr.setTexture(Assets.wheel_elite_body[bottom_draw]);
		spr.draw(Main.batch);
		
		spr.setColor(Color.WHITE);
		spr.setTexture(Assets.wheel_elite_body[bottom_draw]);
		spr.draw(Main.batch);
		
		
	}
	

	
	
	
	
}
