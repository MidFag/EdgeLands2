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


	public Sprite edit_spr=new Sprite(new Texture(Gdx.files.internal("decor_pilon.png"))); 
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
		edit_spr.setAlpha(0.95f);
		edit_spr.setSize(50, 50);
		tile_id=_tile;
		
		gui=_gui;
		
		System.out.println ("TILE BUTTON="+tile_id);
		
		if (tile_id>=0)
		{
			edit_spr.setTexture(GScreen.tile[tile_id]);
		}
	}
	
	@Override
	public void second_draw()
	{
		if (tile_id>=0)
		{
			edit_spr.setPosition(pos.x-edit_spr.getWidth()/2,pos.y-edit_spr.getHeight()/2);
			edit_spr.draw(
					GScreen.batch_static
					);
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
			gui.tile=tile_id;
			//gui.e.spr.setAlpha(0.2f);
			
			
		}
	}
}
