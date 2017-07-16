package com.midfag.game;

import com.badlogic.gdx.Input.TextInputListener;
import com.midfag.entity.Entity;
import com.midfag.game.GUI.Edit.GUIEdit;
import com.midfag.game.script.ScriptSystem;

public class TextInput implements TextInputListener {
	public GUIEdit gui;
	public Entity e;
	
	   public TextInput(GUIEdit _gui, Entity _e) {
		// TODO Auto-generated constructor stub
		   gui=_gui;
		   e=_e;
	}

	   @Override
	   public void input (String text)
	   {
			boolean already_have=false;
			
			for (Entity l:ScriptSystem.Entity_with_id_list)
			{
				if (l.equals(e))
				{
					already_have=true;
					break;
				}
			}
			
			 e.id_for_script=text;
			 
			if (!already_have){ScriptSystem.Entity_with_id_list.add(e); Helper.log("ENTITY SUCCESSFULLY ADDED!");}
			
		   finish();
	   }

	   @Override
	   public void canceled ()
	   {
		   finish(); 
	   }
	   
	   public void finish()
	   {
		   gui.listener=null;
	   }
	}