package com.midfag.equip.module.attr;

import java.util.List;

import com.midfag.entity.AnimationEffectFreeze;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;

public class ModuleAttributeExplosionIce extends ModuleAttribute {


	//public
	
	public ModuleAttributeExplosionIce()
	{
		cost=1;
		
		max_level=100;
		name="Волна холода";
	}
	
	@Override
	public void end_action(Entity _e, float _d)
	{
		Assets.freeze.play();
		
		List<Entity> l=GScreen.get_entity_list(_e.pos);
		_e.Effect.add(new AnimationEffectFreeze(_e.pos,0,0));
		
		for (int i=0; i<l.size(); i++)
		{
			l.get(i).freeze_it(		2f*level/(1f+_e.pos.dst(l.get(i).pos)/100f)		);
		}
	}
	
	@Override
	public String get_descr()
	{
		return 	"Заморозка ближайших целей с силой "+1*level+" при завершении действия модуля";
	}
	
	
}
