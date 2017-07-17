package com.midfag.game.script.actions;

import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.GUI.GUIDialog;
import com.midfag.game.GUI.cinematic.CinematicFilm;
import com.midfag.game.GUI.cinematic.GUICinematic;
import com.midfag.game.script.ScriptSystem;

public class ScriptActionCinematicAddFilm extends ScriptAction {
	

	public ScriptActionCinematicAddFilm(String[] _data) {
		data=_data;
		// TODO Auto-generated constructor stub
//		name=_name;
		

	}
	
	@Override
	public void action()
	{
		//Helper.log("SCRIPT SAY <"+say+">");
			ScriptSystem.cinematic_gui.add_film(new CinematicFilm(data[1]));
			
	}

}
