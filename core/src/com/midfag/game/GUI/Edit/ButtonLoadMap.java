package com.midfag.game.GUI.Edit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import com.midfag.entity.Entity;

import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;

import com.midfag.game.GUI.buttons.Button;

public class ButtonLoadMap extends Button {

	
	public String[] ss=new String[100];
	public GUIEdit gui;
	
	public ButtonLoadMap(float _x, float _y, GUIEdit _gui)
	{
		super(_x,_y);
		pos.x=_x;
		pos.y=_y;
		
		gui=_gui;
	}
	
	@Override
	public void second_draw()
	{
		
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
			
			for (int i=0; i<30; i++)
			for (int j=0; j<30; j++)
			{
				
				if (GScreen.cluster[j][i].Entity_list!=null){GScreen.cluster[j][i].Entity_list.clear();}
				if (GScreen.cluster[j][i].Phys_list!=null){GScreen.cluster[j][i].Phys_list.clear();}
			}
			
			FileHandle file = Gdx.files.local("z.txt");
			
			String s=file.readString();
			ss=s.split("\n");
			
			    // if file doesnt exists, then create it
			//System.out.println(ss);
			//System.out.println(ss[0]);
			Entity e=null;
			for (int i=0; i<ss.length; i++)
			{
				if (ss[i].equals("###ENTITY"))
				{
					i++;
					
					String id=ss[i];
					
					e=GUIEdit.get_object_from_id(id);
					System.out.println("ID="+id);	
				}
				
				if (e!=null)
				{
					if (ss[i].equals("pos.x"))
					{
						i++;
						e.pos.x=Integer.parseInt(ss[i]);
					}
					
					if (ss[i].equals("pos.y"))
					{
						i++;
						e.pos.y=Integer.parseInt(ss[i]);
					}
					
					if (ss[i].equals("PUT"))
					{
						
						GScreen.add_entity_to_map(e);
					}
				}
			}
			
			file = Gdx.files.local("z_tile.txt");
			
			s=file.readString();
			ss=s.split("\n");
			
			System.out.println("FIRST DATA="+ss[0]);
			
			for (int i=0; i<300; i++)
			for (int j=0; j<300; j++)
			{
				String sub_s=ss[i].substring(j*2, j*2+2);
				
				if ((i==0)&(j==0)){System.out.println("TWO LETTER="+sub_s);}
				
				if (!sub_s.equals("no"))
					{
						GScreen.tile_map[j][i]=Integer.parseInt(sub_s);
					}
			}
			
			file = Gdx.files.local("z_tile_overlay.txt");
			
			s=file.readString();
			ss=s.split("\n");
			
			System.out.println("FIRST DATA="+ss[0]);
			
			for (int i=0; i<300; i++)
			for (int j=0; j<300; j++)
			{
				String sub_s=ss[i].substring(j*2, j*2+2);
				
				if ((i==0)&(j==0)){System.out.println("TWO LETTER (overlay)="+sub_s);}
				
				if (!sub_s.equals("no"))
					{
						GScreen.tile_map_overlay[j][i]=Integer.parseInt(sub_s);
					}
				else
				{
					GScreen.tile_map_overlay[j][i]=-1;
				}
			}
			
			//System.out.println("LOLWHUT "+"WTF".substring(1, 3));
			
			InputHandler.but=-1;
			
			
		}
	}
}
