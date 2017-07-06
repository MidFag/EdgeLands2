package com.midfag.equip.module.attr;

import com.midfag.entity.Entity;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.equip.module.ModuleUnit;

public class ModuleAttribute {

	public int level=1;
	public float cost;
	public float max_level;
	//public
	public String name;
	
	public ModuleAttribute()
	{
		
	}
	
	//@Override
	public void calculate(ModuleUnit _m)
	{
		
	}

	public void end_action(Entity _e, float _d) {
		// TODO Auto-generated method stub
		
	}
	
	public String get_descr()
	{
		return "";
	}
	
}
