package com.midfag.game.skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SkillShield_AB_ValueHalfDamageTime extends Skill {
	public SkillShield_AB_ValueHalfDamageTime()
	{
		super();
		
		pos.x=70;
		pos.y=00;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_value_half_damage_time.png")));
		spr.setSize(50, 50);
		
		name="������ �����������";
		info="������ 5 ������"+"\n"+"���������� ���� ���������� �����."+"\n"+"��������� 1 �������";
	}
}
