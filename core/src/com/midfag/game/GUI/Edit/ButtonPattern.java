package com.midfag.game.GUI.Edit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;
import com.midfag.game.GUI.buttons.Button;

public class ButtonPattern extends Button {


	public Sprite edit_spr; 
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
		
		spr.setSize(100, 100);
	
		//off_bg=true;
		

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
		
		mulx=100f/(10f+5f*(tile_pattern.size_x-1));
		muly=100f/(10f+5f*(tile_pattern.size_y-1));
		
		mul=Math.min(mulx, muly);
		
		
		
		/*
		float sx=(10+5*(tile_pattern.size_y-1))*mul/2f;
		float sy=(100-((12.5f*tile_pattern.size_y-1)+12.5f)*mul)/2f;
		*/
		
		
		
			for (int i=0; i<tile_pattern.size_y; i++)
			for (int j=0; j<tile_pattern.size_x; j++)
			{
				if (tile_pattern.layer_main[j][i]>=0)
				{
					int ty=(int)tile_pattern.layer_main[j][i]/8;
					int tx=tile_pattern.layer_main[j][i]-ty*8;
					
					//GScreen.batch_static.draw(GScreen.tile_texture, pos.x+(j*5)*mul-50*mul/mulx,  pos.y+(i*5)*mul-50*mul/muly,10f*mul,10f*mul);
					GScreen.batch_static.draw 
							(
							GScreen.tile_texture,
							pos.x+(j*5)*mul-50*mul/mulx,
							pos.y+(i*5)*mul-50*mul/muly,
							10f*mul,
							10f*mul,
							(tx*60f+tx+61)*0.001953f,
							(ty*60f+ty+61)*0.001953f,
							(tx*60f+tx+1)*0.001953f,
							(ty*60f+ty+1)*0.001953f
							);
				}
			}
			
			
			for (int i=0; i<tile_pattern.size_y; i++)
			for (int j=0; j<tile_pattern.size_x; j++)
			if (tile_pattern.layer_top[j][i]>=0)
			{
				int ty=(int)tile_pattern.layer_top[j][i]/8;
				int tx=tile_pattern.layer_top[j][i]-ty*8;
				
				//GScreen.batch_static.draw(GScreen.tile_texture, pos.x+(j*5)*mul-50*mul/mulx,  pos.y+(i*5)*mul-50*mul/muly,10f*mul,10f*mul);
				GScreen.batch_static.draw 
						(
						GScreen.tile_texture,
						pos.x+(j*5)*mul-50*mul/mulx,
						pos.y+(i*5)*mul-50*mul/muly,
						10f*mul,
						10f*mul,
						(tx*60f+tx+61)*0.001953f,
						(ty*60f+ty+61)*0.001953f,
						(tx*60f+tx+1)*0.001953f,
						(ty*60f+ty+1)*0.001953f
						);
			}
			
			for (int i=0; i<tile_pattern.elist.size(); i++)
			{
				float siz=(10f*mul)/60f;
				
				Texture t=null;
				Entity e=tile_pattern.elist.get(i);
				
				if (e.icon!=null)
				{t=e.icon;}
				
				if (t!=null)
				{
					GScreen.batch_static.draw
					(
						t,
						pos.x+(e.pos.x/6f)*mul-50*mul/mulx-t.getWidth()*mul/2f,
						pos.y+(e.pos.y/6f)*mul-50*mul/muly-t.getHeight()*mul/2f,
						t.getWidth()*mul,
						t.getHeight()*mul
						);
				}
				
				
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
