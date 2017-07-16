package com.midfag.game.script.actions;

import com.midfag.entity.Entity;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.Localisation;
import com.midfag.game.GUI.ButtonDialogNext;
import com.midfag.game.GUI.DialogPool;
import com.midfag.game.GUI.GUIDialog;
import com.midfag.game.script.ScriptSystem;
import com.midfag.game.script.ScriptTimer;

public class ScriptActionAddTimer extends ScriptAction {


	
	public ScriptActionAddTimer(String[] _data) {
		// TODO Auto-generated constructor stub
		data=_data;
	}

	public void action()
	{
		//Helper.log("SCRIPT SAY <"+say+">");
		ScriptSystem.Timer_pool.add(new ScriptTimer(data[1], data[2],Float.parseFloat(data[3])));
		
	}

}
