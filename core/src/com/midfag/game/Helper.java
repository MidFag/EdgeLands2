package com.midfag.game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;

public class Helper {
	
	//private static final String BUILDER_CLASS = "builder.class";

	public Helper()
	{
		
	}
	
	public static void LoadMap()
	{

		
		for (int i=0; i<30; i++)
		for (int j=0; j<30; j++)
		{
			
			if (GScreen.cluster[j][i].Entity_list!=null){GScreen.cluster[j][i].Entity_list.clear();}
			if (GScreen.cluster[j][i].Phys_list!=null){GScreen.cluster[j][i].Phys_list.clear();}
		}
		
		FileHandle file = Gdx.files.local("z.txt");
		
		String s=file.readString();
		String[] ss = s.split("\n");
		
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
				
				e=get_object_from_id(id);
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
					e.fill_path();
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
	}
	
	public static Entity get_object_from_id(String _id)
    {
		try {
			Class<?> c = Class.forName(_id);
			log("INVOKED ID="+_id);
			Constructor<?>[] constructor=c.getConstructors(); 
			
			
			//System.out.println("CONSTRUCTOR[0]: "+constructor[0]);
			//System.out.println("CONSTRUCTOR[1]: "+constructor[1]);
			
			Entity enn=(Entity) constructor[0].newInstance(new Vector2());
			return enn;


			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
    	return null;
    }

	public static void log(String _s) {
		// TODO Auto-generated method stub
		System.out.println(_s);
		
	}
}
