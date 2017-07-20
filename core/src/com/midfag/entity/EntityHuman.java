package com.midfag.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.midfag.equip.module.ModuleUnitTimeSlow;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;

public class EntityHuman extends Entity {
	
	int head_dir=0;
	//int body_dir=0;
	int dir;
	float anim_timer;
	int anim_state;
	int mov;
	
	Color head_color=Color.WHITE;
	Color body_color=new Color(0xddffeeff);
	Color leg_color=new Color(0xffffffff);

	public EntityHuman(Vector2 _v) {
		super(_v);
		
		id=this.getClass().getName();
		
		is_AI=false;
		is_player=true;
		is_enemy=false;
		
		spr.setSize(50, 50);
		spr.setOrigin(25, 5);
		spr.setTexture(Assets.human);
		
		//foot.setSize(30, 6);
		
		icon=Assets.human;
		
		armored[0]=null;
		
		//is_player
		
		speed=150;
		friction=0.01f;

		have_ability=false;
	}
	
	@Override
	public void draw_action(float _d, float _siz) {
		// TODO Auto-generated method stub
		
		if (!GScreen.show_edit)
		{
			float cold_rating=1-buff_cold/(buff_cold+100);
			spr.setColor(cold_rating, cold_rating, 1, 1);
		}
		
		
		float spd=Math.abs(impulse.x+Math.abs(constant_speed_x))+Math.abs(impulse.y+Math.abs(constant_speed_y));
		if (spd>5)
		{
			float c=(float) Math.toDegrees(Math.atan2(impulse.x+constant_speed_x, impulse.y+constant_speed_y));
			if (c<0){c=360+c;}
	    	
	    	if (c>360){c=c-360;}
	    	//pl.spr.setRotation(360-c);
	    	if (c>300)
			{dir=0;}
	    	else
	    	{dir=(int) Math.round(c/90f);}
		}
		else
		{
			anim_state=0;	
		}
		
		anim_timer+=spd*_d;
		
		if (anim_timer>12){anim_state+=1; anim_timer=0; if (anim_state>3){anim_state=0;}}
		
		int as=0;
		
		mov=0;
		if (anim_state==1){as=1;}
		if (anim_state==3){as=2;}
    	
		//if (dir==0){}
		if (selected){GScreen.batch.setColor(Color.GREEN);}
		if (!selected){GScreen.batch.setColor(leg_color);}
		GScreen.batch.draw(Assets.human_pants[dir+as*4], pos.x-25+mov, pos.y-2);
		
		if (!selected)GScreen.batch.setColor(body_color);
		GScreen.batch.draw(Assets.human_body[dir+as*4], pos.x-25+mov, pos.y);
		
		if (!selected)GScreen.batch.setColor(head_color);
		GScreen.batch.draw(Assets.human_head[dir], pos.x-25+mov, pos.y);
		
		GScreen.batch.setColor(Color.WHITE);
	}

}
