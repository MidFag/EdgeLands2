package com.midfag.game.GUI.Edit;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;

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
	public Entity indicate_entity=new Entity(new Vector2());
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
	public Vector2 temp_vector=new Vector2(0,0);
	public TilePattern indicate_pattern;
	public int id_offset;
	 
	public GUIEdit()
	{
		indicate_entity=new Entity(new Vector2());
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
		
		if (Gdx.input.isKeyPressed(112)&&(selected_object!=null)){selected_object.dead_action(true); selected_object=null;}
		
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
		

		
		GScreen.batch.begin();
			if (pattern_edit)
				{GScreen.batch.draw(Assets.point_start,(int)(InputHandler.posx/30)*30,(int)(InputHandler.posy/30)*30);}
			
			if (indicate_pattern!=null)
			{
				GScreen.batch.setColor(1, 1.0f, 1.0f, 0.5f);
				
				for (int i=0; i<indicate_pattern.size_y; i++)
				for (int j=0; j<indicate_pattern.size_x; j++)
				{
					if (indicate_pattern.layer_main[j][i]>=0)
					{	
						int ty=(int)indicate_pattern.layer_main[j][i]/8;
						int tx=indicate_pattern.layer_main[j][i]-ty*8;
					
					//GScreen.batch_static.draw(GScreen.tile_texture, pos.x+(j*5)*mul-50*mul/mulx,  pos.y+(i*5)*mul-50*mul/muly,10f*mul,10f*mul);
						GScreen.batch.draw 
							(
							GScreen.tile_texture,
							(int)(InputHandler.posx/30f)*30f+j*30f-15f,
							(int)(InputHandler.posy/30f)*30f+i*30f-15f,
							60,
							60,
							(tx*60f+tx+1)*0.001953f,
							(ty*60f+ty+61)*0.001953f,
							(tx*60f+tx+61)*0.001953f,
							(ty*60f+ty+1)*0.001953f
							);;
					}
				}
				
				for (int i=0; i<indicate_pattern.size_y; i++)
				for (int j=0; j<indicate_pattern.size_x; j++)
					{
						if (indicate_pattern.layer_top[j][i]>=0)
						{	
							int ty=(int)indicate_pattern.layer_top[j][i]/8;
							int tx=indicate_pattern.layer_top[j][i]-ty*8;
						
						//GScreen.batch_static.draw(GScreen.tile_texture, pos.x+(j*5)*mul-50*mul/mulx,  pos.y+(i*5)*mul-50*mul/muly,10f*mul,10f*mul);
							GScreen.batch.draw 
								(
								GScreen.tile_texture,
								(int)(InputHandler.posx/30f)*30f+j*30f-15f,
								(int)(InputHandler.posy/30f)*30f+i*30f-15f,
								60,
								60,
								(tx*60f+tx+1)*0.001953f,
								(ty*60f+ty+61)*0.001953f,
								(tx*60f+tx+61)*0.001953f,
								(ty*60f+ty+1)*0.001953f
								);;
						}
					}
				

				
				for (int i=0; i<indicate_pattern.elist.size(); i++)
				{
					indicate_pattern.elist.get(i).spr.setPosition
							(
								indicate_pattern.elist.get(i).pos.x+InputHandler.posx-indicate_pattern.elist.get(i).spr.getOriginX(),
								indicate_pattern.elist.get(i).pos.y+InputHandler.posy-indicate_pattern.elist.get(i).spr.getOriginY()
							);
					
					indicate_pattern.elist.get(i).spr.setColor(1, 1, 1, 0.5f);
					indicate_pattern.elist.get(i).draw_action(_d,1);
					indicate_pattern.elist.get(i).spr.setColor(1, 1, 1, 1.0f);

				}
				
				/*
				for (int i=0; i<indicate_pattern.size_y; i++)
				for (int j=0; j<indicate_pattern.size_x; j++)
				{
					if (indicate_pattern.layer_top[j][i]>=0)
					GScreen.batch.draw(GScreen.tile[indicate_pattern.layer_top[j][i]],(int)(InputHandler.posx/30)*30+j*30-15,(int)(InputHandler.posy/30)*30+i*30-15);
				}*/
				
				GScreen.batch.setColor(1, 1, 1, 1);
			}
			
			GScreen.batch.draw(Assets.point_start,(int)(pattern_first_point.x/30f)*30f,(int)(pattern_first_point.y/30.0f)*30f);
			
			//GScreen.batch.draw(Assets.point_start,(pattern_first_point.x+InputHandler.posx)/2f-15,(pattern_first_point.y+InputHandler.posy)/2f-15);
			if (pattern_first_point.x>0)
			{GScreen.batch.draw(Assets.rama,pattern_first_point.x,pattern_first_point.y,InputHandler.posx-pattern_first_point.x,InputHandler.posy-pattern_first_point.y);}
			
		GScreen.batch.end();
		
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
						//en.init("gui edit");
						GScreen.add_entity_to_map(en);
						
						en.fill_path();
					}
				}
			}
			
			if ((InputHandler.but==0)&&(pattern_edit))
			{
				if (pattern_first_point.x==-777)
				{
					pattern_first_point.x=(int)(InputHandler.posx);
					pattern_first_point.y=(int)(InputHandler.posy);
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
					
					
				}
				
				System.out.println("PUT_PATTERN");
				
				if (indicate_pattern.elist.size()>0)
				{
					InputHandler.but=-1;
					
					
					for (int i=0; i<indicate_pattern.elist.size(); i++)
					{
						Entity el=indicate_pattern.elist.get(i);
						Entity en=Helper.get_object_from_id(el.id);
						
						en.pos.x=el.pos.x+InputHandler.posx-el.spr.getOriginX()*0;
						en.pos.y=el.pos.y+InputHandler.posy-el.spr.getOriginY()*0;
						
						GScreen.add_entity_to_map(en);
					}
				}
			}
			
			if ((InputHandler.but!=0)&&(pattern_edit)&&(pattern_first_point.x>0))
			{
				int sx=(int)(pattern_first_point.x/30.0f);
				int sy=(int)(pattern_first_point.y/30.0f);
				
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
				
				List<Entity> l=GScreen.get_entity_list(temp_vector.set(InputHandler.posx, InputHandler.posy));
				
				Helper.log("ENTITY ON LIST="+l.size());
				
				Pattern_list.get(0).elist.clear();
				
				if (l.size()>0)
				{
					float cx=(pattern_first_point.x+InputHandler.posx)/2f;
					float cy=(pattern_first_point.y+InputHandler.posy)/2f;
					
					float dx=Math.abs(pattern_first_point.x-InputHandler.posx);
					float dy=Math.abs(pattern_first_point.y-InputHandler.posy);
					
					try {
						
						for (int i=0; i<l.size(); i++)
						{
							if (
									(Math.abs(l.get(i).pos.x-cx)<dx/2f)
									&&
									(Math.abs(l.get(i).pos.y-cy)<dy/2f)
								)
							{

								Entity enn=Helper.get_object_from_id(l.get(i).id);
								
								enn.pos.x=l.get(i).pos.x-pattern_first_point.x;
								enn.pos.y=l.get(i).pos.y-pattern_first_point.y;
								
								Pattern_list.get(0).elist.add(enn);
								Helper.log("ELIST ADDED ["+i+"]");
							}
						}
						
						
					} 
					 catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				Pattern_list.get(0).size_x=ex-sx+1;
				Pattern_list.get(0).size_y=ey-sy+1;
				pattern_first_point.x=-777;
				pattern_edit=false;
			}
			
			if (InputHandler.realx<850)
			{
				if (!top_layer) {Main.font.setColor(0.5f, 0.5f, 0.5f, 0.5f);}else{Main.font.setColor(0.25f, 1.0f, 0.5f, 1.0f);}
				Main.font.draw(GScreen.batch_static, "TOP LAYER: ", 170, 530);
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
					if (((int)(InputHandler.posx/30)>=0)&&((int)(InputHandler.posy/30)>=0)&&((int)(InputHandler.posx/30)<300)&&((int)(InputHandler.posy/30)<300))
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
				
				GScreen.batch.begin();
					
					GScreen.batch.draw(Assets.mech_foot,xx,yy);
				GScreen.batch.end();
				
				float near_dist=9999;
				
				GScreen.temp_vectorA.x=xx;
				GScreen.temp_vectorA.y=yy;
				

				
				selected_object=null;
				selected_cluster=null;
				
				
				GScreen.batch.begin();
				for (int i=cx-1; i<=cx+1; i++)
				for (int j=cy-1; j<=cy+1; j++)
				if ((i>=0)&&(j>=0))
				{
					
					
						GScreen.batch.draw(Assets.mech_foot,cx*300+150,cy*300+150);
					
					
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
				GScreen.batch.end();
				
				if (selected_object!=null){selected_object.spr.setColor(Color.GREEN); selected_object.spr.setAlpha(0.5f);}
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
		{GScreen.batch.begin();
			indicate_entity.spr.setPosition(xx-indicate_entity.spr.getOriginX(), yy-indicate_entity.spr.getOriginY());
			//GScreen.batch.draw(edit_spr.getTexture(), edit_spr.getVertices(), 10, 20);
			indicate_entity.spr.draw(GScreen.batch);
		GScreen.batch.end();}
		
	}
}
