package com.midfag.equip.module.attr;

import com.midfag.equip.energoshield.Energoshield;
import com.midfag.equip.module.ModuleUnit;
import com.midfag.equip.module.ModuleUnitPush;

public class ModuleAttributeDuration extends ModuleAttribute {


	//public
	
	public ModuleAttributeDuration()
	{
		cost=1;
		
		max_level=100;
		name="Улучшние урона";
	}
	
	@Override
	public void calculate(ModuleUnit _m)
	{
		System.out.println("CALCULATED");
		_m.total_duration+=_m.base_duration*(level/20.0f);
		_m.total_cooldown+=_m.base_cooldown*(level/25.0f);
	}
	
	
}
