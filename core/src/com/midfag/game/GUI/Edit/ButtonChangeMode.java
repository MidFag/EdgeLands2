package com.midfag.game.GUI.Edit;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import com.midfag.entity.Entity;
import com.midfag.entity.EntityHuman;
import com.midfag.entity.decorations.*;

import com.midfag.entity.enemies.*;


import com.midfag.game.Enums.EditMode;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;

import com.midfag.game.GUI.buttons.Button;
import com.midfag.game.GUI.buttons.ButtonVertical;


public class ButtonChangeMode extends Button {


	public Sprite edit_spr=new Sprite(new Texture(Gdx.files.internal("button_entity.png")));; 
	public String id;
	public GUIEdit gui;
	public int em;
	
	public List<Entity> entity_list=new ArrayList<Entity>();
	
	public Vector2 off=new Vector2();
	
	public ButtonChangeMode(float _x, float _y, EditMode _em,  GUIEdit _gui)
	{
		super(_x,_y);
		pos.x=_x;
		pos.y=_y;
	
		em=_em.ordinal();
		if (em==EditMode.TILE.ordinal()){edit_spr.setTexture(new Texture(Gdx.files.internal("button_tile.png")));}
		if (em==EditMode.PATTERN.ordinal()){edit_spr.setTexture(new Texture(Gdx.files.internal("button_pattern.png")));}
		//spr.setTexture(new Texture(Gdx.files.internal("button_entity.png")));
		
		
		
		edit_spr.setSize(edit_spr.getTexture().getWidth(), edit_spr.getTexture().getHeight());
		edit_spr.setAlpha(0.95f);
		
		
		gui=_gui;
		//System.out.println ("ENTITY="+e);
	}
	
	@Override
	public void second_draw()
	{
		float scal=(40f/Math.max(edit_spr.getWidth(), edit_spr.getHeight()));
		edit_spr.setSize(edit_spr.getWidth()*scal, edit_spr.getHeight()*scal);
		edit_spr.setPosition(pos.x-edit_spr.getWidth()/2f*1,pos.y-edit_spr.getHeight()/2);
		
		edit_spr.draw(GScreen.batch_static);
	}
	
	
	public void clear_GUI()
	{
		gui.tile=-1;
		gui.tile_mode=false;
		gui.entity_mode=false;
		gui.pattern_mode=false;
		gui.indicate_pattern=null;
	}
	
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
			
			System.out.println("EDIT MODE="+em);
			

			
			gui.indicate_entity=null;
			
			if ((em==EditMode.TILE.ordinal())&&(!gui.tile_mode))
			{
				clear_GUI();
				gui.tile_mode=true;
				
				
				//GScreen.Button_list.add(new ButtonPutterTile(50,50,-1,gui));
	    		for (int i=0; i<10; i++)
	    		if (GScreen.tile[i]!=null)
	    		{GScreen.Button_list.add(new ButtonPutterTile(50+i*60,50,i,gui));}
	    		
	    		
	    		
	    		
			}
			
			if ((em==EditMode.ENTITY.ordinal())&&(!gui.entity_mode))
			{
				clear_GUI();
				gui.entity_mode=true;
				
				entity_list.clear();
				
				entity_list.add(new DecorStoneWall(new Vector2()));
				entity_list.add(new EntityPyra(new Vector2()));
				entity_list.add(new DecorStonePilon(new Vector2()));
				entity_list.add(new DecorStoneWall2(new Vector2()));
				entity_list.add(new DecorTubeCystern(new Vector2()));
				entity_list.add(new DecorCystern(new Vector2()));
				entity_list.add(new EntityWheel(new Vector2()));
				entity_list.add(new DecorStoneBarak(new Vector2()));
				entity_list.add(new EntityEliteWheel(new Vector2()));
				entity_list.add(new DecorTubeBig(new Vector2()));
				entity_list.add(new DecorTrain(new Vector2()));
				entity_list.add(new DecorTrainVagonRect(new Vector2()));
				entity_list.add(new DecorTrainVagonOpen(new Vector2()));
				entity_list.add(new DecorPowerLine(new Vector2()));
				entity_list.add(new DecorTree(new Vector2()));
				entity_list.add(new DecorSteelBox(new Vector2()));
				entity_list.add(new DecorSteelBoxCap(new Vector2()));
				entity_list.add(new DecorSteelBoxDoor(new Vector2()));
				entity_list.add(new EntityHuman(new Vector2()));
				
				for (int i=0; i<10; i++)
				if (i<entity_list.size())
	    		{
					GScreen.Button_list.add(new ButtonPutter(50+55*i,50,i,gui,entity_list));
				}
	    		
			}
			
			if ((em==EditMode.PATTERN.ordinal())&&(!gui.pattern_mode))
			{
				clear_GUI();
				gui.pattern_mode=true;
				//unreal 13.07.2017 11:01:47
				for (int i=0; i<5; i++)
				{GScreen.Button_list.add(new ButtonPattern(50+120*i,50,gui.Pattern_list.get(i),gui));}
				
				GScreen.Button_list.add(new ButtonEdit(1000-80,80,gui));
			}
		}
	}
}
