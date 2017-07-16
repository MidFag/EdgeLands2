package com.midfag.equip.energoshield;



public class EnergoshieldRobo extends Energoshield {
	

	
	public EnergoshieldRobo()
	{
		super();
		base_value=200;
		value=200;
		base_regen_speed=30;
		base_reflect=10;
		
		name="Roboshield";
		gennable=false;
		
		generate();
		update_attributes_bonus();

	}
	

}
