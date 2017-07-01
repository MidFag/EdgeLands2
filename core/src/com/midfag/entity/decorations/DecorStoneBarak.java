package com.midfag.entity.decorations;


import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;

public class DecorStoneBarak extends Entity {

	public DecorStoneBarak(Vector2 _v,boolean _custom) {
		
		super(_v, _custom);

		
		spr.setTexture(Assets.stone_barak);

		id="stone_barak";
		
		diagonal=false;
		
		spr.setSize(300, 300);
		spr.setOrigin(150f, 0);
		//spr.setAlpha(0.5f);
		
		is_AI=false;
		is_player=false;
		
		armored_shield.value=10000;
		armored_shield.total_value=10000;
		armored_shield.total_regen_speed=0;
		armored_shield.total_reflect=0;
		
		is_decor=true;
		diagonal=false;
		
		
		
		path=true;

		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub

		return new DecorStoneBarak(new Vector2(),true);
		
	}
	
	@Override
	public void do_custom_phys()
	{
		int x=(int)(pos.x/300);
		int y=(int)(pos.y/300);
		
		
		Phys p=new Phys(new Vector2(pos.x-140,pos.y),new Vector2(pos.x+140,pos.y),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x+140,pos.y+200),new Vector2(pos.x+140,pos.y),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x-140,pos.y+200),new Vector2(pos.x+140,pos.y+200),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x-140,pos.y+200),new Vector2(pos.x-140,pos.y),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
	}
	

}
