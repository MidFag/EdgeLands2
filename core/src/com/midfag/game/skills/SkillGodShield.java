package com.midfag.game.skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.game.GScreen;
import com.midfag.game.skills.Skill;

public class SkillGodShield extends Skill {
	
	

	public SkillGodShield()
	{
		super();
		
		pos.x=170;
		pos.y=215;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_shield_of_god.png")));
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		name="Божья силушка";
		info=	"+1000 ёмкость "+"\n"+
				"+100 регенерация"+"\n"+
				"+25 отражение";
		
		//skill_a=new SkillShield_A_MoreValue();
		
	}
	
	@Override
	public void shield_gen_action(Energoshield _e)
	{
		_e.total_value+=1000;
		_e.total_regen_speed+=100;
		_e.total_reflect+=25;
	}
	
	@Override
	public void learn_action()
	{
		
		System.out.println("!@#$%^&");
		GScreen.pl.armored_shield.update_attributes_bonus(GScreen.pl);
		
		for (int i=0; i<GScreen.pl.inventory.length; i++)
		{
			if (GScreen.pl.inventory[i] instanceof Energoshield)
			{
				((Energoshield)GScreen.pl.inventory[i]).update_attributes_bonus();
			}
		}
	}
	
}
