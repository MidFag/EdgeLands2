package com.midfag.equip.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.missiles.Missile;

import com.midfag.entity.missiles.MissileSimple;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;

public class WeaponSimpleShotgun extends Weapon {
	
	
	
		public WeaponSimpleShotgun()
		{
			base_damage=7;
			base_missile_count=10;
			base_shoot_cooldown=1;
			base_dispersion=10;
			base_dispersion_additional=15;
			base_ammo_size=7;
			base_reload_time=3;
			
			missile_speed=2100;
			
			generate();
			update_attributes_bonus();
			
			name="����������� ��������";
			
			spr.setTexture(new Texture(Gdx.files.internal("icon_shotgun.png")));
			spr.setSize(75, 40);
			
			model.setTexture(new Texture(Gdx.files.internal("model_shotgun.png")));
			model.setSize(200, 90);
		}
		

		@Override
		public Sound get_shoot_sound()
		{
			return Assets.shoot02;
		}
		
		
		//public void
}
