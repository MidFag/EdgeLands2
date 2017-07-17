package com.midfag.game.script.actions;

import com.midfag.game.GScreen;
import com.midfag.game.GUI.cinematic.CinematicFilm;
import com.midfag.game.script.ScriptSystem;

public class ScriptActionCameraZoom extends ScriptAction {

	public ScriptActionCameraZoom(String[] _data) {
		data=_data;
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void action()
	{
		//Helper.log("SCRIPT SAY <"+say+">");
			GScreen.camera.zoom=Integer.parseInt(data[1]);
			
	}

}
