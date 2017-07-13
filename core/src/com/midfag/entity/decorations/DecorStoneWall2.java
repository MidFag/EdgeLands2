package com.midfag.entity.decorations;

import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;


public class DecorStoneWall2 extends DecorStoneWall {

	public DecorStoneWall2(Vector2 _v) {
		
		super(_v);


		custom_phys=true;
		
		spr.setTexture(Assets.stone_wall_02);

		icon=Assets.decoration_stone_wall_02_icon;
		
		
		
		spr.setSize(spr.getTexture().getWidth(), spr.getTexture().getHeight());
		spr.setOrigin(11.0f, 5f);
		diagonal=true;
		
		id=this.getClass().getName();
		//spr.setOrigin(10.0f, 65);
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorStoneWall2(new Vector2());
	}
	
	@Override
	public void do_custom_phys()
	{
		int x=(int)(pos.x/300);
		int y=(int)(pos.y/300);
		
		Phys p=new Phys(new Vector2(pos.x-20,pos.y-60),new Vector2(pos.x+20,pos.y-60),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x+20,pos.y-60),new Vector2(pos.x+20,pos.y+80),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x+20,pos.y+80),new Vector2(pos.x-20,pos.y+80),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x-20,pos.y+80),new Vector2(pos.x-20,pos.y-60),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
	}
	
	public void fill_path()
	{
		if (path)
		for (int i=-2; i<3; i++)
		for (int j=-1; j<1; j++)
		{
			GScreen.path[Math.round(pos.x/30f)+j][Math.round(pos.y/30f)+i]=900;
		}
	}
	

}
