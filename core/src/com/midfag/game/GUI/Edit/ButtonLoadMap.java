package com.midfag.game.GUI.Edit;



import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.InputHandler;

import com.midfag.game.GUI.buttons.Button;

public class ButtonLoadMap extends Button {

	
	public String[] ss=new String[100];
	public GUIEdit gui;
	
	public ButtonLoadMap(float _x, float _y, GUIEdit _gui)
	{
		super(_x,_y);
		pos.x=_x;
		pos.y=_y;
		
		gui=_gui;
	}
	
	@Override
	public void second_draw()
	{
		
	}
	
	@Override
	public void some_update(float _d)
	{
		if (!GScreen.show_edit)
		{
			need_remove=true;
			//GScreen.Button_list.remove(this);
		}
		
		if ((InputHandler.but==0)&&(is_overlap()))
		{
			Helper.LoadMap();
			
			//System.out.println("LOLWHUT "+"WTF".substring(1, 3));
			
			InputHandler.but=-1;
			
			
		}
	}
}
