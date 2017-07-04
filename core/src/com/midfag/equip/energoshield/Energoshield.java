package com.midfag.equip.energoshield;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.midfag.entity.Entity;
import com.midfag.equip.energoshield.attr.ESAttribute;
import com.midfag.equip.energoshield.attr.ESAttributeCharge;
import com.midfag.equip.energoshield.attr.ESAttributeReflect;
import com.midfag.equip.energoshield.attr.ESAttributeRegen;
import com.midfag.equip.energoshield.attr.ESAttributeValue;
import com.midfag.game.GScreen;
import com.midfag.game.Enums.Rarity;


public class Energoshield {
	
	public float base_value;
	public float base_regen_speed;
	public float base_reflect;
	
	public float total_value;
	public float total_regen_speed;
	public float total_reflect;
	
	//public float charge;
	public float value;
	public float warm;
	
	public int attr_count;
	
	public boolean gennable=true;
	
	//public Sprite spr=New;
	public Sprite spr=new Sprite(new Texture(Gdx.files.internal("icon_shield.png")));
	
	public List<ESAttribute> Available_attribute_list = new ArrayList<ESAttribute>();
	
	//public List<WeaponAttribute> Attribute_list_heap = new ArrayList<WeaponAttribute>();
	
	public List<ESAttribute> Attribute_list = new ArrayList<ESAttribute>();
	public float attr_point;
	public float level=1.0f;
	public String name;
	
	public Rarity rarity;
	
	
	
	public Energoshield()
	{
		Available_attribute_list.add(new ESAttributeValue());
		Available_attribute_list.add(new ESAttributeRegen());
		Available_attribute_list.add(new ESAttributeReflect());
		Available_attribute_list.add(new ESAttributeCharge());
		
		name="Simple shield";
		
		rarity=Rarity.COMMON;
	}
	
	public void update_attributes_bonus()
	{
		
		total_value=base_value*level;
		total_regen_speed=base_regen_speed;
		total_reflect=base_reflect;
		

		
		for (int i=0; i<Attribute_list.size(); i++)
		{
			Attribute_list.get(i).calculate(this);
		}

		
		value=total_value;
		
		if (rarity==Rarity.COMMON){spr.setColor(Color.WHITE);}
		if (rarity==Rarity.UNCOMMON){spr.setColor(Color.GREEN);}
		if (rarity==Rarity.RARE){spr.setColor(Color.ROYAL);}
		if (rarity==Rarity.ELITE){spr.setColor(Color.MAGENTA);}
		if (rarity==Rarity.LEGENDARY){spr.setColor(Color.ORANGE);}
		if (rarity==Rarity.RELICT){spr.setColor(Color.CYAN);}
	}
	
	public void update_attributes_bonus(Entity _e)
	{
		update_attributes_bonus();
		
		for (int i=0; i<_e.Skills_list.size();i++)
		{
			if (_e.Skills_list.get(i).learned)
			{_e.Skills_list.get(i).shield_gen_action(this);}
		}
	}
	
	
	public void generate()
	{
		if (gennable)
		{
			base_value*=level;
			
			int r=0;
			if (rarity.ordinal()==0)
			{
				for (int i=0; i<6; i++)
				{
					r=i;
					
					if (Math.random()>0.5f){break;}
				}
				
				if (r==0) {rarity=Rarity.COMMON;}
				if (r==1) {rarity=Rarity.UNCOMMON;}
				if (r==2) {rarity=Rarity.RARE;}
				if (r==3) {rarity=Rarity.ELITE;}
				if (r==4) {rarity=Rarity.LEGENDARY;}
				if (r==5) {rarity=Rarity.RELICT;}
			}
			
				
			attr_point=level*10*(1+rarity.ordinal()/5f);
		
			attr_count=(int) (GScreen.rnd(3));
		
			for (int i=0; i<(attr_count-Available_attribute_list.size())-attr_count; i++)
			{
				Available_attribute_list.remove((int)(Math.random()*Available_attribute_list.size()));
				i--;
			}
		
			for (int i=0; i<500; i++)
			{
				if (!Available_attribute_list.isEmpty())
				{
					
					for (int j=0; j<Available_attribute_list.size(); j++)
					{
						ESAttribute aal=Available_attribute_list.get(j);
						
						if 	(
								(aal.cost>attr_point)//если очков на новый атрибут не хватает
								||
								(aal.level>=aal.max_level)//или бонус достиг максимального уровня
							)
								
						{
								Attribute_list.add(aal);
								
								Available_attribute_list.remove(j);
								j--;
						}
					}
					
					
					if (!Available_attribute_list.isEmpty())
					{
						ESAttribute wa=Available_attribute_list.get((int)(Math.random()*Available_attribute_list.size()));
						
						wa.level++;
						attr_point-=wa.cost;
					}
				}
				else
				{break;}
			}	
		}
	}

}
