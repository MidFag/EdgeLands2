package com.midfag.game.script.actions;

import com.midfag.game.Helper;
import com.midfag.game.script.ScriptSystem;

public class ScriptActionDialogExitPoint extends ScriptAction {

	public ScriptActionDialogExitPoint(String[] _data) {
		// TODO Auto-generated constructor stub
		data=_data;
	}
	
	@Override
	public void action()
	{
		if (ScriptSystem.dialog_gui!=null)
		{
			ScriptSystem.dialog_gui.exit_point=data[1];
			ScriptSystem.dialog_gui.current_pool=0;
		}
		else
		{
			Helper.log("ERROR: DIALOG GUI IS EMPTY");
		}
	}

}
