package com.midfag.entity.decorations;


import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.Phys;

public class DecorCystern extends DecorStoneWall {

	public DecorCystern(Vector2 _v,boolean _custom) {
		
		super(_v, _custom);
		
		id=this.getClass().getName();
		
		spr.setTexture(Assets.cystern);

		
		Helper.log("ID="+id);
		
		diagonal=false;
		
		spr.setSize(200, 200);
		spr.setOrigin(100, 50);
		
		path=true;
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	

			
	
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorCystern(new Vector2(),true);
	}
	
	@Override
	public void do_custom_phys()
	{
		int x=(int)(pos.x/300);
		int y=(int)(pos.y/300);
		
		
		Phys p=new Phys(new Vector2(pos.x-80,pos.y-80),new Vector2(pos.x+80,pos.y-80),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x+80,pos.y-80),new Vector2(pos.x+80,pos.y+80),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x+80,pos.y+80),new Vector2(pos.x-80,pos.y+80),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x-80,pos.y+80),new Vector2(pos.x-80,pos.y-80),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
	}
	
	public void fill_path()
	{
		if (path)
		for (int i=0; i<1; i++)
		for (int j=0; j<1; j++)
		{
			GScreen.path[Math.round(pos.x/30f)+j][Math.round(pos.y/30f)+i]=900;
		}
	}
	

}
