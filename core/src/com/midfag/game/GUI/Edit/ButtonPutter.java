package com.midfag.game.GUI.Edit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.enemies.EntityPyra;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;
import com.midfag.game.GUI.buttons.Button;

public class ButtonPutter extends Button {


	public Sprite edit_spr=new Sprite(new Texture(Gdx.files.internal("eye.png")));; 
	public String id;
	public GUIEdit gui;
	public Entity e;
	
	public Vector2 off=new Vector2();
	
	public ButtonPutter(float _x, float _y, Entity _e, GUIEdit _gui)
	{
		super(_x,_y);
		pos.x=_x;
		pos.y=_y;
	
		
		
		
		size_x=80;
		size_y=80;
		
		spr.setSize(80, 80);
		edit_spr.setTexture(_e.spr.getTexture());
		edit_spr.setSize(_e.spr.getTexture().getWidth(), _e.spr.getTexture().getHeight());
		edit_spr.setAlpha(0.95f);
		
		
		e=_e;
		gui=_gui;
		//System.out.println ("ENTITY="+e);
	}
	
	@Override
	public void second_draw()
	{
		float scal=(80f/Math.max(edit_spr.getWidth(), edit_spr.getHeight()));
		edit_spr.setSize(edit_spr.getWidth()*scal, edit_spr.getHeight()*scal);
		edit_spr.setPosition(pos.x-edit_spr.getWidth()/2f*1,pos.y-edit_spr.getHeight()/2);
		
		edit_spr.draw(Main.batch_static);
	}
	
	@Override
	public void some_update(float _d)
	{
		if ((!GScreen.show_edit)||(!gui.entity_mode))
		{
			need_remove=true;
			//GScreen.Button_list.remove(this);
		}
		
		if(is_overlap())
		{
			Helper.log("BUTTON ID="+e.id);
		}
		
		if ((InputHandler.but==0)&&(is_overlap()))
		{
			InputHandler.but=-1;
			
			//System.out.println ("ENTITY="+gui.e);
			gui.indicate_entity=e;
			gui.indicate_entity.spr.setAlpha(0.2f);
			gui.indicate_entity.spr.setSize(e.spr.getTexture().getWidth(), e.spr.getTexture().getHeight());
			
			
		}
	}
}
