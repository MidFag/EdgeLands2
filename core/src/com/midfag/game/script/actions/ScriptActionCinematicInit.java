package com.midfag.game.script.actions;

import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.GUI.GUI;
import com.midfag.game.GUI.GUIDialog;
import com.midfag.game.GUI.cinematic.GUICinematic;
import com.midfag.game.script.ScriptSystem;

public class ScriptActionCinematicInit extends ScriptAction {
	

	public ScriptActionCinematicInit(String[] _data) {
		data=_data;
		// TODO Auto-generated constructor stub
		
//		name=_name;
		

	}
	
	@Override
	public void action()
	{
		ScriptSystem.cinematic_gui=new GUICinematic();
		GScreen.GUI_list.add(ScriptSystem.cinematic_gui);
	}

}
