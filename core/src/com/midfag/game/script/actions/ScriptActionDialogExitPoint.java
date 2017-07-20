package com.midfag.game.script.actions;

import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.GUI.ButtonDialogNext;
import com.midfag.game.GUI.buttons.Button;
import com.midfag.game.script.ScriptSystem;

public class ScriptActionDialogExitPoint extends ScriptAction {

	public ScriptActionDialogExitPoint(String[] _data) {
		// TODO Auto-generated constructor stub
		data=_data;
	}
	
	@Override
	public void action()
	{
		if (ScriptSystem.last_dialog_gui!=null)
		{
			ScriptSystem.last_dialog_gui.exit_point=data[1];
			ScriptSystem.last_dialog_gui.current_pool=0;
			ScriptSystem.last_dialog_gui.remove_if_end=false;
			
			if (data.length>=3)
			{
				if (data[2].equals("remove"))
					{
					/*
							for (Button b:GScreen.Button_list)
							{
								if (b instanceof ButtonDialogNext)
								{b.need_remove=true;}
							}
					*/
					ScriptSystem.last_dialog_gui.remove_if_end=true;
					
					}
				
				
			}
		}
		else
		{
			Helper.log("ERROR: DIALOG GUI IS EMPTY");
		}
	}

}
