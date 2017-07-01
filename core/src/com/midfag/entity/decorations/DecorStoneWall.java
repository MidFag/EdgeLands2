package com.midfag.entity.decorations;


import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;

public class DecorStoneWall extends Entity {

	public DecorStoneWall(Vector2 _v,boolean _custom) {
		
		super(_v, _custom);
		
		is_AI=false;
		is_player=false;
		
		armored_shield.value=10000;
		armored_shield.total_value=10000;
		armored_shield.total_regen_speed=0;
		armored_shield.total_reflect=0;
		
		is_decor=true;
		diagonal=false;
		
		spr.setTexture(Assets.stone_wall_01);
		spr.setSize(spr.getTexture().getWidth(), spr.getTexture().getHeight());
		spr.setOrigin(50.0f, 10f);
		id="stone_wall";
		
		path=true;
		

		
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorStoneWall(new Vector2(),true);
	}
	
	@Override
	public void do_custom_phys()
	{
		int x=(int)(pos.x/300);
		int y=(int)(pos.y/300);
		
		
		Phys p=new Phys(new Vector2(pos.x-60,pos.y-10),new Vector2(pos.x+60,pos.y-10),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x+60,pos.y+20),new Vector2(pos.x+60,pos.y-10),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x-60,pos.y+20),new Vector2(pos.x+60,pos.y+20),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x-60,pos.y+20),new Vector2(pos.x-60,pos.y-10),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
	}
	
	public void fill_path()
	{
		if (path)
		for (int i=-1; i<1; i++)
		for (int j=-2; j<3; j++)
		{
			GScreen.path[Math.round(pos.x/30f)+j][Math.round(pos.y/30f)+i]=900;
		}
	}

}
