package com.midfag.game.GUI.Edit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.GUI.buttons.Button;

public class ButtonEdit extends Button {


	public Sprite edit_spr=new Sprite(new Texture(Gdx.files.internal("eye.png")));; 
	public String id;
	public GUIEdit gui;
	public Entity e;
	
	public Vector2 off=new Vector2();
	
	public ButtonEdit(float _x, float _y, GUIEdit _gui)
	{
		super(_x,_y);
		pos.x=_x;
		pos.y=_y;
	
		
		
		size_x=72;
		size_y=40;
		
		spr.setTexture(new Texture(Gdx.files.internal("button_edit_big.png")));
		spr.setSize(72, 40);
		
		
		gui=_gui;
		//System.out.println ("ENTITY="+e);
	}
	
	@Override
	public void second_draw()
	{
		
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
			
			System.out.println ("PATTERN=");
			gui.pattern_edit=true;
			gui.indicate_pattern=null;
		}
	}
}
