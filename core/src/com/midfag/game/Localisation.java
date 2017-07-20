package com.midfag.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.midfag.game.GUI.DialogPool;

public class Localisation {
	public static List<String> id=new ArrayList< String>();
	public static List<String> value=new ArrayList< String>();
	
	public Localisation()
	{

	}
	
	public static void locad_local()
	{
		FileHandle file = Gdx.files.local("local_rus.txt");
		
		String s=file.readString();
		String[] lines = s.split("\n");

		for (int i=0; i<lines.length; i++)
		{
			lines[i]=lines[i].substring(0,lines[i].length()-1);
			String[] data = lines[i].split("\t");
			
			id.add(data[0]);
			value.add(data[1].replace("###", "\n"));
			
			Helper.log("ID: ["+data[0]+"] value:"+data[1]);
		}
		
		
	}

	public static String get_value_from_id(String _id) {
		// TODO Auto-generated method stub
		for (int i=0; i<id.size(); i++)
		{
			if (id.get(i).equals(_id))
			{return value.get(i);}
		}
		
		return "ERROR: ID <"+_id+"> NOT FOUND";
	}
}
