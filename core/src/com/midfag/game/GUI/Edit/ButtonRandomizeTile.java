package com.midfag.game.GUI.Edit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;
import com.midfag.game.GUI.buttons.Button;

public class ButtonRandomizeTile extends Button {

	public Texture tex;
	
	
	public ButtonRandomizeTile(float _x, float _y)
	{
		super(_x,_y);
		pos.x=_x;
		pos.y=_y;
		
		tex=new Texture(Gdx.files.internal("button_randomize_tile.png"));
		
		
	}
	
	@Override
	public void second_draw()
	{
		GScreen.batch_static.draw(tex, pos.x-25, pos.y-25);
	}
	
	//@SuppressWarnings("static-access")
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
			
			int px=(int)(GScreen.pl.pos.x/30f);
			int py=(int)(GScreen.pl.pos.y/30f);
			
			for (int j=px-15; j<=px+15; j++)
			for (int i=py-15; i<=py+15; i++)
			{
				if ((GScreen.tile_map[j][i]==14)||(GScreen.tile_map[j][i]==15)||(GScreen.tile_map[j][i]==20)||(GScreen.tile_map[j][i]==21))
				{
					switch ((int)GScreen.rnd(4))
		        	{
		        		case 0: GScreen.tile_map[j][i]=14;	break;
		        		case 1: GScreen.tile_map[j][i]=15;	break;
		        		case 2: GScreen.tile_map[j][i]=20;	break;
		        		case 3: GScreen.tile_map[j][i]=21;	break;
		        	}
				}
			}
			
			for (int j=px-15; j<=px+15; j++)
			for (int i=py-15; i<=py+15; i++)
			{
				if ((GScreen.tile_map_overlay[j][i]==14)||(GScreen.tile_map_overlay[j][i]==15)||(GScreen.tile_map_overlay[j][i]==20)||(GScreen.tile_map_overlay[j][i]==21))
				{
					switch ((int)GScreen.rnd(4))
		        	{
		        		case 0: GScreen.tile_map_overlay[j][i]=14;	break;
		        		case 1: GScreen.tile_map_overlay[j][i]=15;	break;
		        		case 2: GScreen.tile_map_overlay[j][i]=20;	break;
		        		case 3: GScreen.tile_map_overlay[j][i]=21;	break;
		        	}
				}
			}
			
		
		}
		
	}
}
