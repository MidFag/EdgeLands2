package com.midfag.game.GUI.cinematic;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.midfag.game.GScreen;
import com.midfag.game.Main;
import com.midfag.game.GUI.GUI;

public class GUICinematic extends GUI {
	
	public Texture bg=new Texture(Gdx.files.internal("intro_bg.png"));
	public List<CinematicFilm> Film_list=new ArrayList<CinematicFilm>();

	
	//public List<>
	
	public GUICinematic()
	{
		//spr.setOrigin(10, 5);
	}
	
	
	
	public void sub_update(float _d) {
		
		/*
		for (DialogPool pool:dialog_pool)
		{
			
		}
		*/
		
		GScreen.batch_static.draw(bg, 0, 0, GScreen.scr_w, GScreen.scr_h);
		
		for (CinematicFilm cf:Film_list)
		{
			cf.update(_d);
			cf.draw(_d);
		}
		
		for (int i=0; i<Film_list.size(); i++)
		{
			if ((Film_list.get(i).dissapear_process)&&(Film_list.get(i).shader_progress<=0))
			{
				Film_list.remove(i);
				i--;
			}
		}
		
	}



	public void add_film(CinematicFilm _cf) {
		// TODO Auto-generated method stub
		for (CinematicFilm cf:Film_list)
		{
			cf.dissapear_process=true;
		}
		Film_list.add(_cf);
		
	}
}
