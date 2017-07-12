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

public class ButtonPattern extends Button {


	public Sprite edit_spr=new Sprite(new Texture(Gdx.files.internal("decor_pilon.png"))); 
	public TilePattern tile_pattern;
	public GUIEdit gui;

	
	public Vector2 off=new Vector2();
	
	public ButtonPattern(float _x, float _y, TilePattern _pat, GUIEdit _gui)
	{
		super(_x,_y);
		pos.x=_x;
		pos.y=_y;
		size_x=100;
		size_y=100;
	
		//off_bg=true;
		
		spr.setSize(100, 100);
		edit_spr.setAlpha(0.95f);
		edit_spr.setSize(100, 100);
		tile_pattern=_pat;
		
		gui=_gui;
		
		System.out.println ("TILE PATTERN");
		

	}
	
	@Override
	public void second_draw()
	{
		
		
		float mulx=1;
		float muly=1;
		float mul=1;
		
		mulx=100f/(10+5*(tile_pattern.size_x-1));
		muly=100f/(10+5*(tile_pattern.size_y-1));
		
		mul=Math.min(mulx, muly);
		
		
		
		/*
		float sx=(10+5*(tile_pattern.size_y-1))*mul/2f;
		float sy=(100-((12.5f*tile_pattern.size_y-1)+12.5f)*mul)/2f;
		*/
		
			for (int i=0; i<tile_pattern.size_y; i++)
			for (int j=0; j<tile_pattern.size_x; j++)
			{
				if (tile_pattern.layer_main[j][i]>=0)
				{GScreen.batch_static.draw(GScreen.tile[tile_pattern.layer_main[j][i]], pos.x+(j*5)*mul-50*mul/mulx,  pos.y+(i*5)*mul-50*mul/muly,10f*mul,10f*mul);}
			}
			
			for (int i=0; i<tile_pattern.size_y; i++)
			for (int j=0; j<tile_pattern.size_x; j++)
			{
				if (tile_pattern.layer_top[j][i]>=0)
				{GScreen.batch_static.draw(GScreen.tile[tile_pattern.layer_top[j][i]], pos.x+(j*5)*mul-50*mul/mulx,  pos.y+(i*5)*mul-50*mul/muly,10f*mul,10f*mul);}
			}
	}
	
	@Override
	public void some_update(float _d)
	{
		if ((!GScreen.show_edit)||(!gui.pattern_mode))
		{
			need_remove=true;
			//GScreen.Button_list.remove(this);
		}
		
		if ((InputHandler.but==0)&&(is_overlap()))
		{
			InputHandler.but=-1;
			
			//System.out.println ("TILE="+tile_id);
			gui.indicate_entity=null;
			
			gui.indicate_pattern=tile_pattern;
			gui.pattern_edit=false;
			//gui.tile=tile_id;
			//gui.e.spr.setAlpha(0.2f);
			
			
		}
	}
}
