package com.midfag.equip.energoshield;



public class EnergoshieldSimple extends Energoshield {
	

	
	public EnergoshieldSimple()
	{
		base_value=500;
		base_regen_speed=50;
		base_reflect=5;
		
		name="Sample shield";
		
		generate();
		update_attributes_bonus();
	}
	

}
