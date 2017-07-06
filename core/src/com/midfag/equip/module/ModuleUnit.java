package com.midfag.equip.module;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.midfag.entity.Entity;
import com.midfag.equip.energoshield.attr.ESAttribute;
import com.midfag.equip.module.attr.ModuleAttribute;

import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Main;
import com.midfag.game.Enums.Rarity;

public class ModuleUnit {

	public float base_cooldown;
	public float total_cooldown;
	public float cooldown;
	
	public float base_duration;
	public float total_duration;
	public float duration;
	
	public Texture tex=new Texture(Gdx.files.internal("icon_firle.png"));
	public Rarity rarity;
	public float level=1.0f;
	
	public String name;
	public String description;
	private int highlight_value;
	private boolean gennable=true;
	private float attr_point;
	private int attr_count;
	public List<ModuleAttribute> Available_attribute_list = new ArrayList<ModuleAttribute>();
	public List<ModuleAttribute> Attribute_list = new ArrayList<ModuleAttribute>();
	public Color color;
	
	
	public ModuleUnit()
	{
		
	}
	
	public void update(Entity _e, float _d)
	{
		
	}
	
	public void use(Entity _e)
	{
		
	}
	
	public String get_description()
	{
		return "";
	}
	
	public void update_attributes_bonus(Entity _e)
	{
		update_stats();
		
		for (int i=0; i<_e.Skills_list.size();i++)
		{
			if (_e.Skills_list.get(i).learned)
			{_e.Skills_list.get(i).module_gen_action(this);}
		}
	}
	
	public void generate()
	{
		if (gennable)
		{

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
		
			attr_count=(int) (GScreen.rnd(3))+10;
		
			for (int i=0; i<(Available_attribute_list.size()-attr_count); i++)
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
						ModuleAttribute aal=Available_attribute_list.get(j);
						
						if 	(
								(aal.cost>attr_point)//если очков на новый атрибут не хватает
								||
								(aal.level>=aal.max_level)//или бонус достиг максимального уровня
							)
								
						{
								System.out.println("Available_attribute_list_remove="+aal.name);
								Attribute_list.add(aal);//добавляем максимально возможно апнутый атрибут в список имеющихся бонусов
								
								Available_attribute_list.remove(j);//убираем атрибут из списка апа
								j--;
						}
					}
					
					
					if (!Available_attribute_list.isEmpty())
					{
						ModuleAttribute wa=Available_attribute_list.get((int)(Math.random()*Available_attribute_list.size()));//выбираем случайный атрибут из имеющихся
						
						wa.level++;//апаем его уровень
						attr_point-=wa.cost;//уменьшаем очки улучшения
					}
				}
				else
				{break;}
			}	
		}
	}
	
	public void recalculate_base() {
		// TODO Auto-generated method stub
		
		
	}

	public void indicate(float _x, float _y, float _d)
	{
		

		Main.batch_static.draw(tex, _x-22, _y-22);
		
		
		if (total_cooldown>0)
		{
			//Main.batch_static.setColor(Color.RED);
			Main.batch_static.draw(Assets.icon_cooldown, _x-25, _y-25+25*(1-cooldown/total_cooldown),50f,50f*cooldown/total_cooldown);
		}
		
		if (total_duration>0)
		{
			//Main.batch_static.setColor(Color.RED);
			Main.batch_static.draw(Assets.icon_duration, _x-25, _y-25+25*(1-duration/total_duration),50f,50f*duration/total_duration);
		}
		
		if (highlight_value>0)
		{
			float col=1f-Math.abs(0.25f-highlight_value)*4f;
					
			Main.batch_static.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);
			
			Main.batch_static.setColor(col, col, col, 1);
			highlight_value-=_d;
			Main.batch_static.draw(Assets.highlight, _x-50, _y-50);
			Main.batch_static.setColor(Color.WHITE);
					
			Main.batch_static.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		}
		
		
	}
	
	

	
	
	public void update_stats()
	{
		total_duration=base_duration;
		total_cooldown=base_cooldown;
		
		additional_update_stats();
		
		for (int i=0; i<Attribute_list.size(); i++)
		{
			Attribute_list.get(i).calculate(this);
		}
		
		if (rarity==Rarity.COMMON){color=Color.WHITE;}
		if (rarity==Rarity.UNCOMMON){color=Color.GREEN;}
		if (rarity==Rarity.RARE){color=Color.ROYAL;}
		if (rarity==Rarity.ELITE){color=Color.MAGENTA;}
		if (rarity==Rarity.LEGENDARY){color=Color.ORANGE;}
		if (rarity==Rarity.RELICT){color=Color.CYAN;}

	}
	

	public void additional_update_stats() {
		// TODO Auto-generated method stub
		
	}

	public String get_name() {
		// TODO Auto-generated method stub
		return name;
	}

	public boolean can_use() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean can_use_default()
	{
		if ((cooldown<=0)&&(duration<=0))
		{return true;}
		
		return false;
	}
	
	
}
