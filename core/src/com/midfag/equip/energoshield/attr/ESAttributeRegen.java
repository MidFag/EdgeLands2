package com.midfag.equip.energoshield.attr;

import com.midfag.equip.energoshield.Energoshield;

public class ESAttributeRegen extends ESAttribute {
	

	
	public ESAttributeRegen()
	{
		name="regeneration";
		cost=2.0f;
		max_level=100;
	}
	
	@Override
	public void calculate(Energoshield _e)
	{
		_e.total_regen_speed=_e.base_regen_speed*(1+level*0.10f)+level*2;
	}
	
	@Override
	public String get_descr() {
		// TODO Auto-generated method stub
		return "+"+level*10f+"% скорость регенерации +"+level*2+"/сек скорость регенерации";
	}
}
