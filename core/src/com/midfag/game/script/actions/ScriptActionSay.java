package com.midfag.game.script.actions;

import com.midfag.game.Helper;

public class ScriptActionSay extends ScriptAction {
	
	String say;

	public ScriptActionSay(String _say) {
		// TODO Auto-generated constructor stub
//		name=_name;
		
		say=_say;
	}
	
	public void action()
	{
		Helper.log("SCRIPT SAY <"+say+">");
	}

}
