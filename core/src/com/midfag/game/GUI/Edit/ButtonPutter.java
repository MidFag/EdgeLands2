package com.midfag.game.GUI.Edit;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
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
	public List<Entity> list;
	public int entity_id;
	
	public Vector2 off=new Vector2();
	
	public ButtonPutter(float _x, float _y, int _id, GUIEdit _gui, List<Entity> _list)
	{
		super(_x,_y);
		pos.x=_x;
		pos.y=_y;
	
		entity_id=_id;
		
		
		size_x=50;
		size_y=50;
		
		list=_list;
		
		spr.setSize(50, 50);
		
		
		gui=_gui;
		//System.out.println ("ENTITY="+e);
	}
	
	@Override
	public void second_draw()
	{
		/*
		float scal=(80f/Math.max(list.get(entity_id).spr.getWidth(), edit_spr.getHeight()));
		edit_spr.setSize(edit_spr.getWidth()*scal, edit_spr.getHeight()*scal);
		edit_spr.setPosition(pos.x-edit_spr.getWidth()/2f*1,pos.y-edit_spr.getHeight()/2);
		
		edit_spr.draw(GScreen.batch_static);*/
		
		if ((entity_id+gui.id_offset<list.size())&&(list.get(entity_id+gui.id_offset).icon!=null))
		{GScreen.batch_static.draw(list.get(entity_id+gui.id_offset).icon, pos.x-20,pos.y-20);}
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
			//Helper.log("BUTTON ID="+e.id);
		}
		
		if ((InputHandler.but==0)&&(is_overlap())&&(entity_id+gui.id_offset<list.size()))
		{
			InputHandler.but=-1;
			
			//System.out.println ("ENTITY="+gui.e);
			gui.indicate_entity=list.get(entity_id+gui.id_offset);
			gui.indicate_entity.spr.setAlpha(0.2f);
			gui.indicate_entity.spr.setSize(list.get(entity_id+gui.id_offset).spr.getTexture().getWidth(), list.get(entity_id+gui.id_offset).spr.getTexture().getHeight());
			
			
		}
	}
}
