package com.midfag.game.skills.shield_skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.game.GScreen;
import com.midfag.game.skills.Skill;

public class SkillShield_C_MoreReflect extends Skill {
	public SkillShield_C_MoreReflect()
	{
		super();
		
		pos.x=0;
		pos.y=-70;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_shield_reflect.png")));
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		name="Улучшенное отражение";
		info="Дополнителное увеличение"+"\n"+"отражения в размере 20%";
	}
	
	@Override
	public void shield_gen_action(Energoshield _e)
	{
		_e.total_reflect+=_e.base_reflect*0.20f;
	}
	
	@Override
	public void learn_action()
	{
		
		
		GScreen.pl.armored_shield.update_attributes_bonus(GScreen.pl);
		
		for (int i=0; i<GScreen.pl.inventory.length; i++)
		{
			if (GScreen.pl.inventory[i] instanceof Energoshield)
			{
				((Energoshield)GScreen.pl.inventory[i]).update_attributes_bonus(GScreen.pl);
			}
		}
	}
}
