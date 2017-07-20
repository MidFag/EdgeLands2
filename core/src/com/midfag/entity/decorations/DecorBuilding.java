package com.midfag.entity.decorations;


import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;


public class DecorBuilding extends Entity {
	
	
	private float alpha=1;

	public DecorBuilding(Vector2 _v) {
		
		super(_v);
		
		custom_phys=true;

		id=this.getClass().getName();
		
		is_decor=true;
		is_AI=false;

		
		
		spr.setTexture(Assets.building_wall_in);
		spr.setSize(spr.getTexture().getWidth(), spr.getTexture().getHeight());
		spr.setOrigin(100.0f, 5f);

		
		diagonal=true;
		//spr.setOrigin(10.0f, 65);
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorBuilding(new Vector2());
	}
	
	@Override
	public void some_draw()
	{
	
	}
	
	@Override
	public void some_update(float _d)
	{
		if ((Math.abs(GScreen.pl.pos.x-pos.x)<100)&&(Math.abs(GScreen.pl.pos.y-pos.y)<70)&&(GScreen.pl.pos.y>pos.y))
		{
			alpha-=_d*5;
		}
		else
		{
			alpha+=_d*5;
		}
		
		if (alpha<0.25f) {alpha=0.25f;}
		if (alpha>1) {alpha=1;}
		spr.setAlpha(alpha);
	}
	@Override
	public void do_custom_phys()
	{
		int x=(int)(pos.x/300);
		int y=(int)(pos.y/300);
		
		
		Phys p=new Phys(new Vector2(pos.x-50,pos.y-10),new Vector2(pos.x+50,pos.y-10),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x+50,pos.y-10),new Vector2(pos.x+50,pos.y+20),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x+50,pos.y+20),new Vector2(pos.x-50,pos.y+20),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x-50,pos.y+20),new Vector2(pos.x-50,pos.y-20),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
	}

}
