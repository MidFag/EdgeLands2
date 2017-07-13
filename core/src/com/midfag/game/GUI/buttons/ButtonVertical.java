package com.midfag.game.GUI.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.InputHandler;
import com.midfag.game.GUI.Edit.GUIEdit;

public class ButtonVertical extends Button {
	public GUIEdit gui;
	public boolean up;

	public ButtonVertical(float _x, float _y, GUIEdit _gui, boolean _up) {
		super(_x, _y);
		
		if (_up)
		{spr.setTexture(new Texture(Gdx.files.internal("button_up.png")));}
		else
		{spr.setTexture(new Texture(Gdx.files.internal("button_down.png")));}	
		
		spr.setSize(50, 20);
		
		size_x=50;
		size_y=20;
		
		gui=_gui;
		up=_up;
		// TODO Auto-generated constructor stub
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
			InputHandler.but=-1;
			
			if (up) {gui.id_offset-=10;} else {gui.id_offset+=10;}
			
			if (gui.id_offset>19){gui.id_offset=19;}
			if (gui.id_offset<0){gui.id_offset=0;}
		}
	}
	

}
