package com.midfag.equip.energoshield;



public class EnergoshieldRobo extends Energoshield {
	

	
	public EnergoshieldRobo()
	{
		super();
		base_value=50;
		value=50;
		base_regen_speed=10;
		base_reflect=5;
		
		name="Roboshield";
		gennable=false;
		
		update_attributes_bonus();

	}
	

}
