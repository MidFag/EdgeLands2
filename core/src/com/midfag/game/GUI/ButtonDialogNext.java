package com.midfag.game.GUI;

import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;
import com.midfag.game.GUI.buttons.Button;
import com.midfag.game.script.ScriptSystem;

public class ButtonDialogNext extends Button {

	public GUIDialog gui;
	public String text; 
	public ButtonDialogNext(float _x, float _y, GUIDialog _gui, String _s) {
		super(_x, _y);
		
		spr.setSize(500, 30);
		size_x=500;
		size_y=30;
		
		gui=_gui;
		text=_s;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void some_update(float _d)
	{
		if ((!GScreen.show_dialog))
		{
			need_remove=true;
			//GScreen.Button_list.remove(this);
		}
		
		if ((InputHandler.but==0)&&(is_overlap()))
		{
			InputHandler.but=-1;
			
			gui.current_pool++;
			if (gui.current_pool>=gui.dialog_pool.size())
			{
				/*
				GScreen.show_dialog=false;
				GScreen.main_control=true;
				GScreen.pl.active=true;*/
				
				gui.dialog_pool.clear();
				
				ScriptSystem.execute(gui.exit_point);
			}
		}
	}
	
	public void second_draw()
	{
		Main.font.draw(GScreen.batch_static, text, pos.x-250, pos.y,500,1,true);
	}

}
