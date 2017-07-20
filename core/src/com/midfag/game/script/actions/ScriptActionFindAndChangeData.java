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

public class ScriptActionFindAndChangeData extends ScriptAction {


	
	public ScriptActionFindAndChangeData(String[] _data) {
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
				if (data[2].equals("id"))
				{
					e.id_for_script=data[3];
					if (data[3].equals("remove"))
					{e.id_for_script="";}
				}
				else
				if (data[2].equals("interact"))
				{
						e.interact_entry_point=data[3];
						e.is_interact=true;
						if (data[3].equals("remove"))
						{e.interact_entry_point=""; e.is_interact=false;}
				}
				
			}
		}
		else
		{
			Helper.log("ERROR: ENTITY WITH ID <"+data[1]+"> NOT REGISTERED");
		}
		


	}

}
