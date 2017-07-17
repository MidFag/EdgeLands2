package com.midfag.game.GUI;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.midfag.game.GScreen;
import com.midfag.game.Main;

public class GUIDialog extends GUI {
	
	public Texture dialog_texture=new Texture(Gdx.files.internal("dialog_gui.png"));
	public List<DialogPool> dialog_pool=new ArrayList<DialogPool>();
	public int current_pool=0;
	public String exit_point;
	public Sprite spr=new Sprite(new Texture(Gdx.files.internal("dialog_arrow.png")));
	
	//public List<>
	
	public GUIDialog()
	{
		spr.setOrigin(10, 5);
	}
	
	
	
	public void sub_update(float _d) {
		
		/*
		for (DialogPool pool:dialog_pool)
		{
			
		}
		*/
		if (!GScreen.show_dialog)
		{
			GScreen.GUI_list.remove(this);
		}
		
		
		Main.font.setColor(Color.BLACK);
		
		if (current_pool<dialog_pool.size())
		{
			if (dialog_pool.get(current_pool).entity==null)
			{
				GScreen.batch_static.draw(dialog_texture, (GScreen.scr_w-500f)/2f, 50,500,93);
				
				Main.font.draw
							(
								GScreen.batch_static,
								dialog_pool.get(current_pool).text,
								(GScreen.scr_w-500f)/2f+15,
								135,
								480,
								-1,
								true
							);
			}
			else
			{
				//spr.setPosition(px, py);
				float px=dialog_pool.get(current_pool).entity.pos.x-GScreen.camera.position.x+GScreen.scr_h/2f-160;
				
				if (dialog_pool.get(current_pool).entity.pos.x-GScreen.camera.position.x>30)
				{px+=350;}
				else
				{px-=50;}
				
				float py=Math.max
						(
							105,
							(dialog_pool.get(current_pool).entity.pos.y-GScreen.camera.position.y)*1+GScreen.scr_h/2f+50
						);
				
				spr.setPosition
						(
							px,
							py
						);
				
				float a=GScreen.camera.position.x-dialog_pool.get(current_pool).entity.pos.x;
		    	float b=GScreen.camera.position.y-dialog_pool.get(current_pool).entity.pos.y;
		    	//float c=(float) Math.sqrt((a*a)+(b*b));
		    	float c=(float) Math.toDegrees(Math.atan2(a, b));
		    	
		    	//c=c;
		    	/*if (c<0){c=360+c;}
		    	
		    	
		    	if (c>360){c=c-360;}*/
				spr.setRotation(c);
				
				
				GScreen.batch_static.draw(dialog_texture,px,py,320,130);
				
				Main.font.draw(GScreen.batch_static, dialog_pool.get(current_pool).text,px+10,py+117,290,-1,true);
				//spr.draw(GScreen.batch_static);
			}
		}
		
	}
}
