package com.midfag.game.skills.weapon_skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.midfag.entity.Entity;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.equip.weapon.Weapon;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Main;
import com.midfag.game.skills.Skill;

public class SkillWeapon_AB_AmmoOrReload extends Skill {
	
	public int chance_stack;
	

	public SkillWeapon_AB_AmmoOrReload()
	{
		super();
		
		pos.x=70;
		pos.y=0;
		
		spr.setTexture(new Texture(Gdx.files.internal("icon_ammo_or_reload.png")));
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

		
		name="Боезапас";
		info=	"Увеличивает размер магазина на 20%, если магазин вмещает не менее 5 патронов"+"\n"+
				"В противном случае увеличивет скорость перезарядки на 20%.";

		
		need_to_indicate=true;
		//skill_a=new SkillShield_A_MoreValue();
		indicate_tex=new Texture(Gdx.files.internal("icon_reload_chance.png"));
		indicate_text="0%";
		
	}
	
	@Override
	public void weapon_start_reload_action(Entity _e, int _i)
	{
		chance_stack++;
		
		
		if (Math.random()<chance_stack/10.0f)
		{
			if (_e.armored[_i]!=null){_e.armored[_i].reload_timer=0.1f; chance_stack=0;}
			highlight_value=0.5f;
			
		}
		
		indicate_text=chance_stack*10+"%";
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
