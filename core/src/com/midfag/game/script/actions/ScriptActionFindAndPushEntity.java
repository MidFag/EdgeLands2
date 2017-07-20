package com.midfag.game.script.actions;

import java.lang.reflect.Field;
import java.util.List;

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
		
		
		
		
		List<Entity> l=ScriptSystem.find_entity(data[1]);
		if (l!=null)
		{
			for (Entity e:l)
			{
				if (data[2].equals("x"))
				{
					e.constant_move_x=Integer.parseInt(data[3]);
					e.constant_speed_x=Integer.parseInt(data[4]);
				}
				else
				if (data[2].equals("y"))
				{
						e.constant_move_y=Integer.parseInt(data[3]);
						e.constant_speed_y=Integer.parseInt(data[4]);
				}
				else
				if (data[2].equals("z"))
				{
						e.constant_move_z=Integer.parseInt(data[3]);
						e.constant_speed_z=Integer.parseInt(data[4]);
				}
			}
		}
		else
		{
			Helper.log("ERROR: ENTITY WITH ID <"+data[1]+"> NOT REGISTERED");
		}
		


	}

}
