package com.midfag.game.skills.shield_skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
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
}
