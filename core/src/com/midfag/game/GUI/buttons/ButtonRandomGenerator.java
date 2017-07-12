package com.midfag.game.GUI.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.equip.energoshield.EnergoshieldSimple;
import com.midfag.equip.module.ModuleUnitPush;
import com.midfag.equip.module.ModuleUnitTimeSlow;
import com.midfag.equip.module.ModuleUnitTurret;
import com.midfag.equip.weapon.WeaponLegendaryBlender;
import com.midfag.equip.weapon.WeaponLegendaryChaos;
import com.midfag.equip.weapon.WeaponLegendaryPing;
import com.midfag.equip.weapon.WeaponSimpleFirle;
import com.midfag.equip.weapon.WeaponSimpleLaser;
import com.midfag.equip.weapon.WeaponSimpleMinigun;
import com.midfag.equip.weapon.WeaponSimpleShotgun;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.Enums.EquipGenerationType;
import com.midfag.game.Main;

public class ButtonRandomGenerator extends Button {

	public EquipGenerationType egt;
	public Texture tex;
	
	public ButtonRandomGenerator(float _x, float _y, EquipGenerationType _egt) {
		super(_x, _y);
		
		egt=_egt;
		if (!GScreen.show_equip){need_remove=true;}
		
		if (egt==EquipGenerationType.WEAPON)	{tex=new Texture(Gdx.files.internal("skill_weapon.png"));}
		if (egt==EquipGenerationType.SHIELD)	{tex=new Texture(Gdx.files.internal("skill_shield.png"));}
		if (egt==EquipGenerationType.MODULE)	{tex=new Texture(Gdx.files.internal("icon_time_control.png"));}
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void after_draw()
	{
		GScreen.batch_static.setColor(Color.WHITE);
		GScreen.batch_static.draw(tex, pos.x-22, pos.y-22);
	}
	
	public void some_update(float _d)
	{
		if ((is_overlap())&&(InputHandler.but==0))
		{
				InputHandler.but=-1;
				
				if (egt==EquipGenerationType.MODULE)
				{
					for (int i=0; i<30; i++)//;
			        {
						switch ((int)GScreen.rnd(3))
			        	{
			        		case 0: GScreen.pl.inventory[i]=new ModuleUnitPush();		break;
			        		case 1: GScreen.pl.inventory[i]=new ModuleUnitTimeSlow();	break;
			        		case 2: GScreen.pl.inventory[i]=new ModuleUnitTurret();		break;
			        	}
			        }
				}
				
								
				if (egt==EquipGenerationType.SHIELD)
				{
					for (int i=0; i<30; i++)//;
			        {
						GScreen.pl.inventory[i]=new EnergoshieldSimple();
						((Energoshield) GScreen.pl.inventory[i]).update_attributes_bonus(GScreen.pl);
					}
					
				}
				
				if (egt==EquipGenerationType.WEAPON)
				{  
					for (int i=0; i<30; i++)//;
			        {
						if (Math.random()>0.05)
			        	{
			        		switch ((int)GScreen.rnd(4))
				        	{
				        		case 0: GScreen.pl.inventory[i]=new WeaponSimpleFirle();		break;
				        		case 1: GScreen.pl.inventory[i]=new WeaponSimpleMinigun();		break;
				        		case 2: GScreen.pl.inventory[i]=new WeaponSimpleShotgun();		break;
				        		case 3: GScreen.pl.inventory[i]=new WeaponSimpleLaser(); 		break;
				        	}
			        	}
			        	else
			        	switch ((int)GScreen.rnd(3))
				        {
				       		case 0: GScreen.pl.inventory[i]=new WeaponLegendaryBlender();	break;
				       		case 1: GScreen.pl.inventory[i]=new WeaponLegendaryChaos();		break;
				       		case 2: GScreen.pl.inventory[i]=new WeaponLegendaryPing();		break;
				        }
			        }
				}
			
		}
	}

}
