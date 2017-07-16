package com.midfag.game.script.actions;

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
		ScriptSystem.dialog_gui.dialog_pool.add(ScriptSystem.pool);

		GScreen.Button_list.add(new ButtonDialogNext(500, 170, ScriptSystem.dialog_gui, Localisation.get_value_from_id(data[2])));
	}

}
