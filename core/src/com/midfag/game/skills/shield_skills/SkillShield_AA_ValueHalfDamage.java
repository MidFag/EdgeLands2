package com.midfag.game.skills.shield_skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.midfag.entity.Entity;
import com.midfag.game.skills.Skill;


//@SuppressWarnings("unused")
public class SkillShield_AA_ValueHalfDamage extends Skill {
	public SkillShield_AA_ValueHalfDamage()
	{
		super();
		
		pos.x=-00;
		pos.y=75;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_value_half_damage.png")));
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		name="Укрепление";
		info="Получаемый урон уменьшается вдвое"+"\n"+"если заряд щита превышает 85%";
	}
	
	@Override
	public float damage_action(Object _o, float _damage)
	{
		if (_o instanceof Entity){if (((Entity)_o).armored_shield.value/((Entity)_o).armored_shield.total_value>=0.85f){return _damage/2;}}
		return _damage;
	}
}
