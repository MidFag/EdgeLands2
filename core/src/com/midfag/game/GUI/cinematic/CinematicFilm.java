package com.midfag.game.GUI.cinematic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.game.GScreen;

public class CinematicFilm {
	public boolean dissapear_process=false;
	public float shader_progress=-0.2f;
	
	public Texture tex;
	
	public CinematicFilm(String _tex_path)
	{
		tex=new Texture(Gdx.files.internal(_tex_path+".png"));
	}
	
	public void update(float _d)
	{
		if (!dissapear_process)
		{
			if (shader_progress<1)
			{shader_progress+=_d/2f;
			if (shader_progress>1){shader_progress=1;}}
			
			
		}
		else
		{
			if (shader_progress>0)
			{shader_progress-=_d;}
			else
			{shader_progress=0;}
		}
	}

	public void draw(float _d) {
		// TODO Auto-generated method stub
		if (shader_progress>0)
		{
			GScreen.batch_static.setColor(1,1,1,shader_progress);
			GScreen.batch_static.draw(tex, (GScreen.scr_w-tex.getWidth())/2f, (GScreen.scr_h-tex.getHeight())/2f-50+shader_progress*75);
			GScreen.batch_static.setColor(Color.WHITE);
		}
	}
}
