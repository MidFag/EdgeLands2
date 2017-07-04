package com.midfag.equip.energoshield.attr;

import com.midfag.equip.energoshield.Energoshield;

public class ESAttributeValue extends ESAttribute {
	

	
	public ESAttributeValue()
	{
		name="shield";
		cost=3;
		max_level=100;
	}
	
	@Override
	public void calculate(Energoshield _e)
	{
		_e.total_value=_e.base_value*(1+level*0.05f);
	}
}
