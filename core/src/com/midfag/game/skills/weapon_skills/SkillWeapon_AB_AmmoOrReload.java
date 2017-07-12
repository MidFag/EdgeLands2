package com.midfag.game.skills.weapon_skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.midfag.equip.weapon.Weapon;
import com.midfag.game.GScreen;
import com.midfag.game.skills.Skill;

public class SkillWeapon_AB_AmmoOrReload extends Skill {
	
	public int chance_stack;
	

	public SkillWeapon_AB_AmmoOrReload()
	{
		super();
		
		pos.x=50;
		pos.y=0;
		
		spr.setTexture(new Texture(Gdx.files.internal("icon_ammo_or_reload.png")));
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

		
		name="Боезапас";
		info=	"Увеличивает размер магазина на 20%, если магазин вмещает не менее 5 патронов"+"\n"+
				"В противном случае увеличивет скорость перезарядки на 20%.";

		
		need_to_indicate=false;
		//skill_a=new SkillShield_A_MoreValue();
		//indicate_tex=new Texture(Gdx.files.internal("icon_reload_chance.png"));
		indicate_text="";
		
	}
	
	
	@Override
	public void weapon_gen_action(Weapon _w)
	{
		if (_w.base_ammo_size>=5)
		{_w.total_ammo_size+=_w.base_ammo_size*0.20f;}
		else
		{_w.total_reload_time*=0.8333f;}
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
