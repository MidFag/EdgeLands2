package com.midfag.game.skills.weapon_skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.midfag.equip.weapon.Weapon;
import com.midfag.game.GScreen;
import com.midfag.game.skills.Skill;

public class SkillWeapon_BB_AdvancedBloodlust extends Skill {
	
	

	public SkillWeapon_BB_AdvancedBloodlust()
	{
		super();
		
		pos.x=0;
		pos.y=50;
		
		spr.setTexture(new Texture(Gdx.files.internal("icon_bloodlust_second.png")));
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		name="“€га к насилию";
		info=	"+15% урон"+"\n"+
				"+15% скорость стрельбы";
		
		//skill_a=new SkillShield_A_MoreValue();
		
	}
	
	@Override
	public void weapon_gen_action(Weapon _w)
	{
		_w.total_damage+=_w.base_damage*0.15f;
		_w.total_shoot_cooldown=_w.total_shoot_cooldown*0.8696f;
	}
	
	@Override
	public void learn_action()
	{
		
		System.out.println("!@#$%^&");
		if (GScreen.pl.armored[0]!=null)GScreen.pl.armored[0].update_attributes_bonus(GScreen.pl);
		if (GScreen.pl.armored[1]!=null)GScreen.pl.armored[1].update_attributes_bonus(GScreen.pl);
		
		for (int i=0; i<GScreen.pl.inventory.length; i++)
		{
			if (GScreen.pl.inventory[i] instanceof Weapon)
			{
				((Weapon)GScreen.pl.inventory[i]).update_attributes_bonus();
			}
		}
	}
	
}
