package com.midfag.game.script.actions;

import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.GUI.GUIDialog;
import com.midfag.game.script.ScriptSystem;

public class ScriptActionDialogInit extends ScriptAction {
	

	public ScriptActionDialogInit() {
		// TODO Auto-generated constructor stub
//		name=_name;
		

	}
	
	@Override
	public void action()
	{
		//Helper.log("SCRIPT SAY <"+say+">");
		ScriptSystem.dialog_gui=new GUIDialog();
		GScreen.GUI_list.add(ScriptSystem.dialog_gui);
		GScreen.show_dialog=true;
		GScreen.main_control=false;
		GScreen.pl.active=false;
	}

}
