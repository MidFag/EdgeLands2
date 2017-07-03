package com.midfag.game.skills.shield_skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.midfag.game.skills.Skill;

public class SkillShield_CA_MoreReflectDouble extends Skill {
	public SkillShield_CA_MoreReflectDouble()
	{
		super();
		
		pos.x=35;
		pos.y=-67;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_shield_reflect_double.png")));
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		name="Отмщение";
		info="Отраженные снаряды наносят"+"\n"+"противникам удвоенный урон";
	}
}
