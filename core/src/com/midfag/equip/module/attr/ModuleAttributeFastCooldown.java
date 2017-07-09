package com.midfag.equip.module.attr;

import com.midfag.equip.energoshield.Energoshield;
import com.midfag.equip.module.ModuleUnit;
import com.midfag.equip.module.ModuleUnitPush;

public class ModuleAttributeFastCooldown extends ModuleAttribute {


	//public
	
	public ModuleAttributeFastCooldown()
	{
		cost=1;
		
		max_level=100;
		name="”скорение перезар€дки";;
	}
	
	@Override
	public void calculate(ModuleUnit _m)
	{
		System.out.println("CALCULATED");
		_m.total_cooldown/=1f+(level/50f);
		//_m.total_cooldown+=_m.base_cooldown*(level/25.0f);
	}
	
	@Override
	public String get_descr()
	{
		return 	"”скор€ет перез€р€дку модул€ на "+2*level+"%";
	}
	
}
