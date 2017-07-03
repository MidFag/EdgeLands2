package com.midfag.game.GUI.Edit;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.decorations.DecorBuilding;
import com.midfag.entity.decorations.DecorBuildingWall;
import com.midfag.entity.decorations.DecorStoneBarak;
import com.midfag.entity.decorations.DecorStoneWall;
import com.midfag.entity.decorations.DecorStonePilon;
import com.midfag.entity.decorations.DecorStoneWall2;
import com.midfag.entity.enemies.EntityPyra;
import com.midfag.entity.enemies.EntityWheel;
import com.midfag.game.Assets;
import com.midfag.game.Cluster;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;
import com.midfag.game.GUI.GUI;
import com.midfag.game.GUI.buttons.Button;

public class GUIEdit extends GUI {
	
	public List<Button> Button_list = new ArrayList<Button>();
	public List<TilePattern> Pattern_list = new ArrayList<TilePattern>();
	//public GScreen G=Main.screen;
	public Entity indicate_entity=new Entity(new Vector2(),true);
	public static List<Entity> Object_list = new ArrayList<Entity>();
	
	public String id;
	public int tile=-1;
	
	public static Entity selected_object;
	public static Cluster selected_cluster;

	public boolean top_layer=false;
	
	public boolean entity_mode=false;
	public boolean tile_mode=false;
	public boolean pattern_mode=false;
	public boolean pattern_edit=false;
	
	public Vector2 pattern_first_point=new Vector2(-777,-777);
	public TilePattern indicate_pattern;
	 
	public GUIEdit()
	{
		indicate_entity=new Entity(new Vector2(),true);
		//G=GScreen.get_this();
		
		for (int k=0; k<5; k++)
		{
			TilePattern tp=new TilePattern();
			
			tp.size_x=5;
			tp.size_y=5;
			for (int i=0; i<tp.size_x;i++)
			for (int j=0; j<tp.size_y;j++)	
			{
				tp.layer_main[j][i]=(int)(Math.random()*15);
				
				tp.layer_top[j][i]=-1;
			}
			Pattern_list.add(tp);
		}
	}
	
	//@Switcher
	
	
	@Override
	public void sub_update(float _d) 
	{
		
		if(!GScreen.show_edit){GScreen.GUI_list.remove(this);}
		
		int mod=3;
		
		if (Gdx.input.isKeyPressed(112)&&(selected_object!=null)){selected_object.dead_action(true);}
		
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){mod=1;}
		
		/*
			if (InputHandler.key==Keys.COMMA){e.spr.rotate(-15);InputHandler.key=-1;}	
			else
			if (InputHandler.key==Keys.PERIOD){e.spr.rotate(15);InputHandler.key=-1;}	
			*/
		if (InputHandler.key==Keys.COMMA){top_layer=false;}
		if (InputHandler.key==Keys.PERIOD){top_layer=true;}
			
		float xx=(int)(InputHandler.posx/mod)*mod;
		float yy=(int)(InputHandler.posy/mod)*mod;
		

		
		Main.batch.begin();
			if (pattern_edit)
				{Main.batch.draw(Assets.point_start,(int)(InputHandler.posx/30)*30,(int)(InputHandler.posy/30)*30);}
			
			if (indicate_pattern!=null)
			{
				Main.batch.setColor(1, 0.8f, 0.6f, 0.5f);
				
				for (int i=0; i<indicate_pattern.size_y; i++)
				for (int j=0; j<indicate_pattern.size_x; j++)
				{
					if (indicate_pattern.layer_main[j][i]>=0)
					
					Main.batch.draw(Assets.tile[indicate_pattern.layer_main[j][i]],(int)(InputHandler.posx/30)*30+j*30-15,(int)(InputHandler.posy/30)*30+i*30-15);
				}
				
				for (int i=0; i<indicate_pattern.size_y; i++)
				for (int j=0; j<indicate_pattern.size_x; j++)
				{
					if (indicate_pattern.layer_top[j][i]>=0)
					Main.batch.draw(Assets.tile[indicate_pattern.layer_top[j][i]],(int)(InputHandler.posx/30)*30+j*30-15,(int)(InputHandler.posy/30)*30+i*30-15);
				}
				
				Main.batch.setColor(1, 1, 1, 1);
			}
			
			Main.batch.draw(Assets.point_start,pattern_first_point.x*30,pattern_first_point.y*30);
			if (pattern_first_point.x>0)
			{Main.batch.draw(Assets.rama,pattern_first_point.x*30,pattern_first_point.y*30,InputHandler.posx-pattern_first_point.x*30,InputHandler.posy-pattern_first_point.y*30);}
			
		
		
		if ((InputHandler.realy>70)&&(InputHandler.realy<700-70))
		{
			if (
					(InputHandler.but==0)
					&&
					(indicate_entity!=null)
					&&
					(!indicate_entity.id.equals(""))
				)
			{
			
				InputHandler.but=-1;
				
				if (InputHandler.realx<800)
				{
					Entity en=Helper.get_object_from_id(indicate_entity.id);
					
					if (en!=null)
					{
						en.pos.x=xx;
						en.pos.y=yy;
						
						en.spr.setRotation(indicate_entity.spr.getRotation());
						en.init();
						GScreen.add_entity_to_map(en);
						
						en.fill_path();
					}
				}
			}
			
			if ((InputHandler.but==0)&&(pattern_edit))
			{
				if (pattern_first_point.x==-777)
				{
					pattern_first_point.x=(int)(InputHandler.posx/30);
					pattern_first_point.y=(int)(InputHandler.posy/30);
				}
				//if (1)
			}
			
			if ((InputHandler.but==0)&&(indicate_pattern!=null))
			{
				for (int i=0; i<indicate_pattern.size_y; i++)
				for (int j=0; j<indicate_pattern.size_x; j++)
				{
					GScreen.tile_map[(int)(InputHandler.posx/30)+j][(int)(InputHandler.posy/30)+i]=indicate_pattern.layer_main[j][i];
					GScreen.tile_map_overlay[(int)(InputHandler.posx/30)+j][(int)(InputHandler.posy/30)+i]=indicate_pattern.layer_top[j][i];
					
					System.out.println("PUT_PATTERN");
				}
			}
			
			if ((InputHandler.but!=0)&&(pattern_edit)&&(pattern_first_point.x>0))
			{
				int sx=(int)(pattern_first_point.x);
				int sy=(int)(pattern_first_point.y);
				
				int ex=(int)(InputHandler.posx/30f);
				int ey=(int)(InputHandler.posy/30f);
				
				int swap=0;
				
				if (sx>ex)
				{
					swap=sx;
					sx=ex;
					ex=swap;
				}
				
				if (sy>ey)
				{
					swap=sy;
					sy=ey;
					ey=swap;
				}
				
				for (int i=0; i<15;	i++)
				for (int j=0; j<15;	j++)
				{
					Pattern_list.get(0).layer_main[j][i]=-1;
					Pattern_list.get(0).layer_top[j][i]=-1;
				}
				
				for (int i=0; i<=ex-sx;	i++)
				for (int j=0; j<=ey-sy;	j++)
				{
					Pattern_list.get(0).layer_main[i][j]=GScreen.tile_map[sx+i][sy+j];
					Pattern_list.get(0).layer_top[i][j]=GScreen.tile_map_overlay[sx+i][sy+j];
					//GScreen.tile_map[sx+i][sy+j]=15;
				}
				
				Pattern_list.get(0).size_x=ex-sx+1;
				Pattern_list.get(0).size_y=ey-sy+1;
				pattern_first_point.x=-777;
				pattern_edit=false;
			}
			

			if (InputHandler.realx<850)
			{
				if (!top_layer) {Main.font.setColor(0.5f, 0.5f, 0.5f, 0.5f);}else{Main.font.setColor(0.25f, 1.0f, 0.5f, 1.0f);}
				Main.font.draw(Main.batch_static, "TOP LAYER: ", 170, 530);
			}
			
			
			if (
					(InputHandler.but==0)
					&&
					(tile>=0)
					&&
					(InputHandler.realx<850)
				)
				
				
				{
					//System.out.println("PUT TILE!");
					if (!top_layer)
					{GScreen.tile_map[(int)(InputHandler.posx/30)][(int)(InputHandler.posy/30)]=tile;}
					else
					{GScreen.tile_map_overlay[(int)(InputHandler.posx/30)][(int)(InputHandler.posy/30)]=tile;}
					
					
				}
			
				if ((InputHandler.key==112))
				{
					GScreen.tile_map_overlay[(int)(InputHandler.posx/30)][(int)(InputHandler.posy/30)]=-1;
				}
				


				
			if ((InputHandler.but==1))
			{
				if (selected_object!=null){selected_object.spr.setColor(Color.WHITE);}
				
				indicate_entity=null;
				
				int cx=(int)(xx/300f);
				int cy=(int)(yy/300f);
				
				
					
					Main.batch.draw(Assets.mech_foot,xx,yy);
				
				
				float near_dist=9999;
				
				GScreen.temp_vectorA.x=xx;
				GScreen.temp_vectorA.y=yy;
				

				
				selected_object=null;
				selected_cluster=null;
				
				
				
				for (int i=cx-1; i<=cx+1; i++)
				for (int j=cy-1; j<=cy+1; j++)
				if ((i>=0)&&(j>=0))
				{
					
					
						Main.batch.draw(Assets.mech_foot,cx*300+150,cy*300+150);
					
					
					for (int k=0; k<GScreen.cluster[i][j].Entity_list.size(); k++)
					{
						if (GScreen.temp_vectorA.dst(GScreen.cluster[i][j].Entity_list.get(k).pos)<near_dist)
						{
							near_dist=GScreen.temp_vectorA.dst(GScreen.cluster[i][j].Entity_list.get(k).pos);
							
							selected_object=GScreen.cluster[i][j].Entity_list.get(k);
							selected_cluster=GScreen.cluster[i][j];
						}
					}
				}
				
				
				if (selected_object!=null){selected_object.spr.setColor(Color.GREEN);}
			}
		}
		
		if ((InputHandler.key==Keys.X)&&(selected_object!=null))
		{
			InputHandler.key=-1;
			
			selected_object.order++;
			if (selected_object.order>2){selected_object.order=0;}
			/*selected_cluster.Entity_list.remove(selected_object);
			selected_cluster.Entity_list.add(selected_object);*/
		}
		
		if ((InputHandler.key==Keys.C)&&(selected_object!=null))
		{
			selected_object.hard_move(xx-selected_object.pos.x, yy-selected_object.pos.y, 1);
			
		}
		
		if (indicate_entity!=null)
		{
			indicate_entity.spr.setPosition(xx-indicate_entity.spr.getOriginX(), yy-indicate_entity.spr.getOriginY());
			//Main.batch.draw(edit_spr.getTexture(), edit_spr.getVertices(), 10, 20);
			indicate_entity.spr.draw(Main.batch);
		}
		
	}
}
