package com.midfag.game.skills.shield_skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.midfag.entity.Entity;
import com.midfag.game.skills.Skill;

public class SkillShield_BA_WarmProtect extends Skill {
	public SkillShield_BA_WarmProtect()
	{
		super();
		
		pos.x=-0;
		pos.y=70;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_shield_regen_protect.png")));
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		name="Ãàğàíòèÿ";
		info="Êàæäûå 5 ñåêóíä"+"\n"+"ğåãåíåğàöèÿ ùèòà íå ñáèâàåòñÿ"+"\n"+"Äåéñòâóåò â òå÷åíèè 1é ñåêóíäû";
		
		
		
		cooldown_base=5f;
		duration_base=2f;
		cooldown=5f;
		
		need_to_indicate=true;
		
	}
	
	@Override
	public float warm_damage_action(Entity _e) {
		// TODO Auto-generated method stub
		if (duration>0)
		{return 10000;}
		
		return 0;
	}
}
