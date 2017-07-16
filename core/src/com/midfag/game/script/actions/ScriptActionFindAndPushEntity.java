package com.midfag.game.script.actions;

import java.lang.reflect.Field;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.Localisation;
import com.midfag.game.GUI.ButtonDialogNext;
import com.midfag.game.GUI.DialogPool;
import com.midfag.game.GUI.GUIDialog;
import com.midfag.game.script.ScriptSystem;

public class ScriptActionFindAndPushEntity extends ScriptAction {


	
	public ScriptActionFindAndPushEntity(String[] _data) {
		// TODO Auto-generated constructor stub
		data=_data;
	}

	public void action()
	{
		//Helper.log("SCRIPT SAY <"+say+">");
		Entity wtf=null;
		//for (int i=0; i<)
		
		
		
		
		for (Entity entity:ScriptSystem.Entity_with_id_list)
		{
			if (entity.id_for_script.equals(data[1]))
			{
				wtf=entity;
				break;
			}
		}
		
		if (data[1].equals("player")){wtf=GScreen.pl;}
		if (data[1].equals("player_human")){wtf=GScreen.pl_human;}
		if (data[1].equals("player_mech")){wtf=GScreen.pl_mech;}
		
		if (wtf!=null)
		{
			wtf.constant_move_x=Integer.parseInt(data[2]);
			wtf.constant_move_y=Integer.parseInt(data[3]);
			
			wtf.constant_speed_x=Integer.parseInt(data[4]);
			wtf.constant_speed_y=Integer.parseInt(data[5]);
			
			//wtf.dead_action(true);
			
			Helper.log(">>>>>>>>>>>"+wtf.id);
			//GScreen.pl=wtf;
			
			//wtf.pos.x+=200;
			
		}
		else
		{
			Helper.log("ERROR: ENTITY WITH ID <"+data[1]+"> NOT REGISTERED");
		}
	}

}
