package com.midfag.game.skills.weapon_skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.equip.weapon.Weapon;
import com.midfag.game.GScreen;
import com.midfag.game.skills.Skill;

public class SkillWeapon_A_FastReload extends Skill {
	
	

	public SkillWeapon_A_FastReload()
	{
		super();
		
		pos.x=50;
		pos.y=50;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_weapon.png")));
		spr.setSize(50, 50);
		
		name="»скуссна€ перезар€дка";
		info=	"+20% скорость перезар€дки";
		
		//skill_a=new SkillShield_A_MoreValue();
		
	}
	
	@Override
	public void weapon_gen_action(Weapon _w)
	{
		_w.total_reload_time*=0.833f;
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
