package com.midfag.game.script.actions;

import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.GUI.GUIDialog;
import com.midfag.game.GUI.cinematic.GUICinematic;
import com.midfag.game.script.ScriptSystem;

public class ScriptActionCinematicClose extends ScriptAction {
	

	public ScriptActionCinematicClose(String[] _data) {
		data=_data;
		// TODO Auto-generated constructor stub
//		name=_name;
		

	}
	
	@Override
	public void action()
	{
		//Helper.log("SCRIPT SAY <"+say+">");
		for (int i=0; i<GScreen.GUI_list.size(); i++)
		{
			if (GScreen.GUI_list.get(i) instanceof GUICinematic)
			{
				GScreen.GUI_list.remove(i);
				i--;
			}
		}
	}

}
