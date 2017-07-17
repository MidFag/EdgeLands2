package com.midfag.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.midfag.equip.module.ModuleUnitTimeSlow;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;

public class EntityHumanNPC extends EntityHuman {
	


	public EntityHumanNPC(Vector2 _v) {
		super(_v);
		
		id=this.getClass().getName();
		
		is_player=false;
		//foot.setSize(30, 6);
		head_color=Color.WHITE;
		body_color=new Color(0xffbbaaff);
		leg_color=new Color(0x7799aaff);
		
	}

}
