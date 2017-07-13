package com.midfag.game.GUI.Edit;

import java.util.ArrayList;
import java.util.List;

import com.midfag.entity.Entity;

public class TilePattern
{
	public int size_x=1;
	public int size_y=1;
	
	public int layer_top[][]=new int[30][30];
	public int layer_main[][]=new int[30][30];
	
	public List<Entity> elist=new ArrayList<Entity>();
	
		public TilePattern()
		{
			
		}
	
}
