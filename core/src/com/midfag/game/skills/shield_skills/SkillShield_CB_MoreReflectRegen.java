package com.midfag.game.skills.shield_skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.midfag.game.skills.Skill;

public class SkillShield_CB_MoreReflectRegen extends Skill {
	public SkillShield_CB_MoreReflectRegen()
	{
		super();
		
		pos.x=-35;
		pos.y=-67;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_shield_reflect_regen.png")));
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		name="Возмещение";
		info="10% от отраженного урона"+"\n"+"восстанавливает энергощит";
		
		
	}
}
