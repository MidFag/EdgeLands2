package com.midfag.game.GUI.cinematic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Main;

public class CinematicFilm {
	public boolean dissapear_process=false;
	public float shader_progress=-0.05f;
	
	public Texture tex;
	float mov;
	float scal=1;
	
	float h,w=0;
	
	public CinematicFilm(String _tex_path)
	{
		tex=new Texture(Gdx.files.internal(_tex_path+".png"));
		h=tex.getHeight();
		w=tex.getWidth();
	}
	
	public void update(float _d)
	{
		if (!dissapear_process)
		{
			if (shader_progress<1)
			{shader_progress+=_d/4f;
			if (shader_progress>1){shader_progress=1;}}
			
			
		}
		else
		{
			if (shader_progress>0)
			{shader_progress-=_d/2;}
			else
			{shader_progress=0;}
		}
	}

	public void draw(float _d) {
		// TODO Auto-generated method stub

			GScreen.batch_static.setShader(Main.shader_dissolve);
			Assets.dissolve.bind(1);
			Main.shader_time_slow.setUniformi("u_texture", 1);
			
			tex.bind(0);
			Main.shader_time_slow.setUniformi("u_texture2", 0);
			//Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
												//		(1-0.5)/0.5
												//		(0.5-1.0)/1
			
			Main.shader_time_slow.setUniformf("value", shader_progress);
			
			
			//Main.shader_time_slow.setUniformf("value", 1);
			//GScreen.batch_static.setColor(1,1,1,shader_progress);

			if (!dissapear_process)
			{
				mov=shader_progress*75;
				Main.shader_time_slow.setUniformf("value", shader_progress);
			}
			
			if (dissapear_process)
			{
				scal*=Math.pow(0.5f,_d/1f);
				scal=Math.max(0.01f, scal);
				GScreen.batch_static.setColor(1,1,1,shader_progress);
			}
			GScreen.batch_static.draw(tex, (GScreen.scr_w-w*scal)/2f, (GScreen.scr_h-h*scal)/2f-50+mov,w*scal,h*scal);
			GScreen.batch_static.setColor(Color.WHITE);
			
			GScreen.batch_static.setShader(Main.shader_default);
		
	}
}
