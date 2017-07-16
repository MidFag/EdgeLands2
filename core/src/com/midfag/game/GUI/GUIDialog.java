package com.midfag.game.GUI;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.game.GScreen;
import com.midfag.game.Main;

public class GUIDialog extends GUI {
	
	public Texture dialog_texture=new Texture(Gdx.files.internal("dialog.png"));
	public List<DialogPool> dialog_pool=new ArrayList<DialogPool>();
	public int current_pool=0;
	public String exit_point;
	
	//public List<>
	
	
	
	
	
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
		
		GScreen.batch_static.draw(dialog_texture, (GScreen.scr_w-500f)/2f, (GScreen.scr_h-300f)/2f);
		
		if (current_pool<dialog_pool.size())
		{
			Main.font.draw
							(
								GScreen.batch_static,
								dialog_pool.get(current_pool).text,
								(GScreen.scr_w-500f)/2f+15,
								(GScreen.scr_h-300f)/2f+285,
								480,
								-1,
								true
							);
		}
		
	}
}
