package com.midfag.game.GUI.Edit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;
import com.midfag.game.GUI.buttons.Button;

public class ButtonPutterTile extends Button {


	public int tile_id;
	public GUIEdit gui;

	
	public Vector2 off=new Vector2();
	
	public ButtonPutterTile(float _x, float _y, int _tile, GUIEdit _gui)
	{
		super(_x,_y);
		pos.x=_x;
		pos.y=_y;
		size_x=55;
		size_y=55;
	
		//off_bg=true;
		
		spr.setSize(55, 55);

		tile_id=_tile;
		
		gui=_gui;
		
		System.out.println ("TILE BUTTON="+tile_id);
		

	}
	
	@Override
	public void second_draw()
	{
		if ((tile_id>=0)&&(GScreen.tile[tile_id+gui.id_offset]!=null))
		{
			GScreen.batch_static.draw(GScreen.tile[tile_id+gui.id_offset], pos.x-25,pos.y-25,50,50);
		}
	}
	
	@Override
	public void some_update(float _d)
	{
		if ((!GScreen.show_edit)||(!gui.tile_mode))
		{
			need_remove=true;
			//GScreen.Button_list.remove(this);
		}
		
		if ((InputHandler.but==0)&&(is_overlap()))
		{
			InputHandler.but=-1;
			
			System.out.println ("TILE="+tile_id);
			gui.indicate_entity=null;
			gui.tile=tile_id+gui.id_offset;
			
			//gui.e.spr.setAlpha(0.2f);
			
			
		}
	}
}
