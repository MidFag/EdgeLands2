package com.midfag.game.script.actions;

import java.util.List;

import com.midfag.entity.Entity;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.Localisation;
import com.midfag.game.GUI.ButtonDialogNext;
import com.midfag.game.GUI.DialogPool;
import com.midfag.game.GUI.GUIDialog;
import com.midfag.game.script.ScriptSystem;

public class ScriptActionDialogAddText extends ScriptAction {


	
	public ScriptActionDialogAddText(String[] _data) {
		// TODO Auto-generated constructor stub
		data=_data;
	}

	public void action()
	{
		//Helper.log("SCRIPT SAY <"+say+">");
		ScriptSystem.pool=new DialogPool();
		ScriptSystem.pool.text=Localisation.get_value_from_id(data[1]);
		ScriptSystem.last_dialog_gui.dialog_pool.add(ScriptSystem.pool);
		
		if (data.length>=4)
		{
			List<Entity> l=ScriptSystem.find_entity(data[3]);
			if (l!=null)
			{
				for (Entity e:l)
				ScriptSystem.pool.entity=e;
			}
			else
			{
				Helper.log("ERROR: ENTITY WITH ID <"+data[3]+"> NOT REGISTERED");
			}
		}

		
	}

}
