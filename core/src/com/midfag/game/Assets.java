package com.midfag.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Assets {
	

	
	public static Texture panel;
	public static Texture diod;
	public static Texture particle;
	
	public static Texture tube;
	public static Texture tube_carcas;
	
	public static Texture explosion;
	
	public static Sound shoot00;
	public static Sound shoot01;
	public static Sound shoot02;
	public static Sound shoot04=Gdx.audio.newSound(Gdx.files.internal("shoot04.wav"));
	
	public static Sound expl=Gdx.audio.newSound(Gdx.files.internal("expl.wav"));
	
	public static Sound metal_sound;

	public static Sound plastic;
	
	public static Sound metal_destroy;
	public static Sound saw;
	//public static Sound music;
	
	public static long music_id;
	
	static Music music = Gdx.audio.newMusic(Gdx.files.internal("music01.mp3"));

	public static Sound flash;

	public static Sound minigun;

	public static Sound shoot03;
	public static Sound shoot75523235;

	public static Sound chaos;
	public static Sound shoot_laser;
	
	public static Sound engine=Gdx.audio.newSound(Gdx.files.internal("engine3.wav"));;
	
	public static Sound crash=Gdx.audio.newSound(Gdx.files.internal("crash.wav"));;
	public static Sound time_slow=Gdx.audio.newSound(Gdx.files.internal("time_slow.wav"));;
	public static Sound jet=Gdx.audio.newSound(Gdx.files.internal("jet.wav"));;
	public static long time_slow_id;
	
	public static Sound freeze=Gdx.audio.newSound(Gdx.files.internal("freeze.wav"));;
	
	public static Sound gate=Gdx.audio.newSound(Gdx.files.internal("gate.wav"));
	public static Sound knock=Gdx.audio.newSound(Gdx.files.internal("knock.wav"));
	public static Sound door=Gdx.audio.newSound(Gdx.files.internal("door.wav"));
	
	public static Sprite skill_wheel=new Sprite(new Texture(Gdx.files.internal("eye.png")));
	public static Sprite select_sprite=new Sprite(new Texture(Gdx.files.internal("selected_skill.png")));

	public static Texture missile;
	
	public static Texture rect=new Texture(Gdx.files.internal("rect.png"));
	public static Texture rect_white=new Texture(Gdx.files.internal("rect_white.png"));
	public static Texture round=new Texture(Gdx.files.internal("round_bg.png"));
	
	public static Texture stone_wall_01=new Texture(Gdx.files.internal("stone_wall_01.png"));
	public static Texture stone_pilon_01=new Texture(Gdx.files.internal("stone_pilon_01.png"));
	public static Texture stone_barak=new Texture(Gdx.files.internal("decor_stone_barak.png"));
	
	public static Texture planet0=new Texture(Gdx.files.internal("planet_00.png"));
	public static Texture planet1=new Texture(Gdx.files.internal("planet_01.png"));
	public static Texture planet2=new Texture(Gdx.files.internal("planet_02.png"));
	
	public static Texture human=new Texture(Gdx.files.internal("human.png"));
	
	
	
	
	
	public static Texture[] human_head=new Texture[4];
	
	public static Texture[] human_body=new Texture[12];
	public static Texture[] human_pants=new Texture[12];
	
	public static Texture decor_tube_cystern=new Texture(Gdx.files.internal("decoration_tube_cystern.png"));
	public static Texture decor_tube_big=new Texture(Gdx.files.internal("decoration_tube_big.png"));
	public static Texture decoration_train=new Texture(Gdx.files.internal("decoration_train.png"));
	public static Texture decoration_train_vagon_rect=new Texture(Gdx.files.internal("decoration_train_vagon_rect.png"));
	public static Texture decoration_train_vagon_open=new Texture(Gdx.files.internal("decoration_train_vagon_open.png"));
	
	public static Texture decoration_tree=new Texture(Gdx.files.internal("decoration_tree.png"));
	public static Texture decoration_tree_icon=new Texture(Gdx.files.internal("decoration_tree_icon.png"));
	
	public static Texture decoration_power_line=new Texture(Gdx.files.internal("decoration_power_line.png"));
	public static Texture decoration_power_line_icon=new Texture(Gdx.files.internal("decoration_power_line_icon.png"));
	
	public static Texture decoration_steel_box=new Texture(Gdx.files.internal("decoration_steel_box.png"));
	public static Texture decoration_steel_box_icon=new Texture(Gdx.files.internal("decoration_steel_box_icon.png"));
	
	public static Texture decoration_steel_box_cap=new Texture(Gdx.files.internal("decoration_steel_box_cap.png"));
	public static Texture decoration_steel_box_cap_icon=new Texture(Gdx.files.internal("decoration_steel_box_cap_icon.png"));
	
	public static Texture decoration_steel_box_door=new Texture(Gdx.files.internal("decoration_steel_box_door.png"));
	public static Texture decoration_steel_box_door_icon=new Texture(Gdx.files.internal("decoration_steel_box_door_icon.png"));
	
	public static Texture cystern=new Texture(Gdx.files.internal("decoration_cystern.png"));
	
	public static Texture stone_wall_02=new Texture(Gdx.files.internal("stone_wall_02.png"));;
	
	public static Texture beton_wall=new Texture(Gdx.files.internal("beton_wall.png"));;
	
	public static Texture graffiti_01=new Texture(Gdx.files.internal("graffiti_01.png"));;
	public static Texture graffiti_01_icon=new Texture(Gdx.files.internal("graffiti_01_icon.png"));;
	
	public static Texture beton_wall_icon=new Texture(Gdx.files.internal("beton_wall_icon.png"));;
	
	public static Texture beton_wall_window=new Texture(Gdx.files.internal("beton_wall_window.png"));;
	public static Texture beton_wall_window_icon=new Texture(Gdx.files.internal("beton_wall_window_icon.png"));
	
	public static Texture building_wall_in=new Texture(Gdx.files.internal("building_02.png"));;
	
	public static Texture wall_angle_A=new Texture(Gdx.files.internal("wall_angle_A.png"));;
	public static Texture wall_angle_B=new Texture(Gdx.files.internal("wall_angle_B.png"));;
	
	public static Texture building_floor=new Texture(Gdx.files.internal("building_floor.png"));;
	public static Texture building_floor_icon=new Texture(Gdx.files.internal("building_floor_icon.png"));;
	
	public static Texture building_roof=new Texture(Gdx.files.internal("roof.png"));;
	public static Texture building_roof_icon=new Texture(Gdx.files.internal("roof_icon.png"));;
	
	public static Texture building_roof_long=new Texture(Gdx.files.internal("roof_long.png"));;
	public static Texture building_roof_long_icon=new Texture(Gdx.files.internal("roof_long_icon.png"));;
	
	public static Texture indicate_bg=new Texture(Gdx.files.internal("indicate_bg.png"));;
	public static Texture highlight=new Texture(Gdx.files.internal("highlight.png"));;
	public static Texture icon_cooldown=new Texture(Gdx.files.internal("icon_cooldown.png"));;
	public static Texture icon_duration=new Texture(Gdx.files.internal("icon_duration.png"));;
	
	public static Texture decoration_cystern_icon=new Texture(Gdx.files.internal("decoration_cystern_icon.png"));;
	public static Texture decoration_tube_big_icon=new Texture(Gdx.files.internal("decoration_tube_big_icon.png"));;
	public static Texture decoration_stone_barak_icon=new Texture(Gdx.files.internal("decoration_stone_barak_icon.png"));;
	public static Texture decoration_stone_pilon_icon=new Texture(Gdx.files.internal("decoration_stone_pilon_icon.png"));;
	public static Texture decoration_stone_wall_01_icon=new Texture(Gdx.files.internal("decoration_stone_wall_01_icon.png"));;
	public static Texture decoration_stone_wall_02_icon=new Texture(Gdx.files.internal("decoration_stone_wall_02_icon.png"));;
	public static Texture decoration_tube_cystern_icon=new Texture(Gdx.files.internal("decoration_tube_cystern_icon.png"));;
	public static Texture entity_pyra_icon=new Texture(Gdx.files.internal("entity_pyra_icon.png"));;
	public static Texture entity_wheel_icon=new Texture(Gdx.files.internal("entity_wheel_icon.png"));;
	public static Texture entity_elite_wheel_icon=new Texture(Gdx.files.internal("entity_elite_wheel_icon.png"));;
	
	
	public static Texture decoration_train_icon=new Texture(Gdx.files.internal("decoration_train_icon.png"));;
	public static Texture decoration_train_vagon_rect_icon=new Texture(Gdx.files.internal("decoration_train_vagon_rect_icon.png"));;
	public static Texture decoration_train_vagon_open_icon=new Texture(Gdx.files.internal("decoration_train_vagon_open_icon.png"));;
	/*
	public static Texture mech_down=new Texture(Gdx.files.internal("mech01.png"));;
	public static Texture mech_right=new Texture(Gdx.files.internal("mech02.png"));;
	public static Texture mech_left=new Texture(Gdx.files.internal("mech03.png"));;*/
	
	public static Texture mech_leg=new Texture(Gdx.files.internal("leg.png"));
	public static Texture mech_foot=new Texture(Gdx.files.internal("foot.png"));
	public static Texture point_start=new Texture(Gdx.files.internal("point_start.png"));
	public static Texture rama=new Texture(Gdx.files.internal("rama.png"));
	public static Texture text_bg=new Texture(Gdx.files.internal("text_bg.png"));
	//public static Texture selected_skill=new Texture(Gdx.files.internal("selected_skill.png"));
	
	public static Texture mech_foot_shadow=new Texture(Gdx.files.internal("foot_shadow.png"));
	
	//public static Texture 
	
	public static Texture shadow=new Texture(Gdx.files.internal("mech/shadow.png"));
	
	public static Texture noise=new Texture(Gdx.files.internal("noise.png"));
	public static Texture normal_map=new Texture(Gdx.files.internal("normal_map.png"));
	public static Texture dissolve=load("dissolve");
	
	public static Texture button_e=load("button_e");
	public static Texture quest=load("quest");
	
	public static Texture[] pyra_body=new Texture[16];
	public static Texture[] pyra_head=new Texture[16];
	
	public static Texture[] wheel_body=new Texture[16];
	
	public static Texture[] wheel_elite_body=new Texture[16];
	public static Texture[] effect_shield=new Texture[13];
	public static Texture[] effect_explosion=new Texture[6];
	public static Texture[] effect_freeze=new Texture[11];
	
	public static Texture[] turret_body=new Texture[16];
	
	public Assets()
	{
		
	}
	
	private static Texture load(String _s) {
		// TODO Auto-generated method stub
		Texture tex=new Texture(Gdx.files.internal(_s+".png"));
		tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return tex;
	}

	public static void load_assets()
	{
		stone_wall_01.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		stone_wall_02.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		stone_pilon_01.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		planet0.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		planet1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		planet2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		/*
		mech_right.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		mech_down.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		mech_left.setFilter(TextureFilter.Linear, TextureFilter.Linear);*/
		
		
		skill_wheel.setTexture(new Texture(Gdx.files.internal("eye.png")));
		skill_wheel.setSize(2048, 2048);
		skill_wheel.setOrigin(1024, 1024);
		
		skill_wheel.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		
		panel=new Texture(Gdx.files.internal("panel.png"));
		diod=new Texture(Gdx.files.internal("diod.png"));
		particle=new Texture(Gdx.files.internal("particle.png"));
		explosion=new Texture(Gdx.files.internal("explosion.png"));
		missile=new Texture(Gdx.files.internal("missile.png"));
		
		
		
		
		shoot00 = Gdx.audio.newSound(Gdx.files.internal("shoot00.wav"));
		shoot01 = Gdx.audio.newSound(Gdx.files.internal("shoot01.wav"));
		shoot02 = Gdx.audio.newSound(Gdx.files.internal("shoot02.wav"));
		shoot03 = Gdx.audio.newSound(Gdx.files.internal("shoot03.wav"));
		shoot75523235 = Gdx.audio.newSound(Gdx.files.internal("shoot75523235.wav"));
		shoot_laser = Gdx.audio.newSound(Gdx.files.internal("shoot_laser.wav"));
		
		metal_sound = Gdx.audio.newSound(Gdx.files.internal("metal_sound.wav"));
		plastic = Gdx.audio.newSound(Gdx.files.internal("plastic.wav"));
		
		metal_destroy = Gdx.audio.newSound(Gdx.files.internal("metal_destroy.wav"));
		
		saw = Gdx.audio.newSound(Gdx.files.internal("saw.wav"));
		
		minigun = Gdx.audio.newSound(Gdx.files.internal("minigun.wav"));
		
		flash = Gdx.audio.newSound(Gdx.files.internal("flash.wav"));
		//music = Gdx.audio.newSound(Gdx.files.internal("music.wav"));
		chaos = Gdx.audio.newSound(Gdx.files.internal("chaos_chaos_chaos.wav"));
		
		chaos = Gdx.audio.newSound(Gdx.files.internal("chaos_chaos_chaos.wav"));
		//flash = Gdx.audio.newSound(Gdx.files.internal("flash.wav"));		

		
		stone_barak.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		cystern.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		icon_cooldown.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		icon_duration.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		noise.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		music.setLooping(true);
		music.setVolume(0.73f);
		music.play();
		
		time_slow_id=time_slow.play();
		time_slow.setLooping(time_slow_id, true);
		time_slow.setVolume(time_slow_id, 0.01f);
		
		select_sprite.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		select_sprite.setAlpha(1.0f);
		
	}
	
	public static void post_load_assets()
	{
		
		
		for (int i=0; i<=22; i++)
		{
			if (i<10)
			{GScreen.tile[i]=new Texture(Gdx.files.internal("tile/tile0"+i+".png"));}
			else
			{GScreen.tile[i]=new Texture(Gdx.files.internal("tile/tile"+i+".png"));}
			
			
			GScreen.tile[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		
		
		for (int i=0; i<4; i++)
		{
			human_head[i]=new Texture(Gdx.files.internal("human/head"+i+".png"));
			

		}
		
		for (int i=0; i<3; i++)
		{
			human_body[0+i*4]=new Texture(Gdx.files.internal("human/body_up_"+i+".png"));
			human_body[1+i*4]=new Texture(Gdx.files.internal("human/body_right_"+i+".png"));
			human_body[2+i*4]=new Texture(Gdx.files.internal("human/body_bottom_"+i+".png"));
			human_body[3+i*4]=new Texture(Gdx.files.internal("human/body_left_"+i+".png"));
		}
		
		for (int i=0; i<3; i++)
		{
			human_pants[0+i*4]=new Texture(Gdx.files.internal("human/pants_up_"+i+".png"));
			human_pants[1+i*4]=new Texture(Gdx.files.internal("human/pants_right_"+i+".png"));
			human_pants[2+i*4]=new Texture(Gdx.files.internal("human/pants_bottom_"+i+".png"));
			human_pants[3+i*4]=new Texture(Gdx.files.internal("human/pants_left_"+i+".png"));
		}
		
		for (int i=0; i<16; i++)
		{
			if (i<10)
			{pyra_body[i]=new Texture(Gdx.files.internal("pyra/body00"+i+".png"));}
			else
			{pyra_body[i]=new Texture(Gdx.files.internal("pyra/body0"+i+".png"));}
			
			pyra_body[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		for (int i=0; i<16; i++)
		{
			if (i<10)
			{turret_body[i]=new Texture(Gdx.files.internal("turret/body00"+i+".png"));}
			else
			{turret_body[i]=new Texture(Gdx.files.internal("turret/body0"+i+".png"));}
			
			turret_body[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		for (int i=0; i<16; i++)
		{
			if (i<10)
			{pyra_head[i]=new Texture(Gdx.files.internal("pyra/head00"+i+".png"));}
			else
			{pyra_head[i]=new Texture(Gdx.files.internal("pyra/head0"+i+".png"));}
			
			pyra_head[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		for (int i=0; i<16; i++)
		{
			if (i<10)
			{wheel_body[i]=new Texture(Gdx.files.internal("wheel/body00"+i+".png"));}
			else
			{wheel_body[i]=new Texture(Gdx.files.internal("wheel/body0"+i+".png"));}
			
			wheel_body[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		for (int i=0; i<16; i++)
		{
			if (i<10)
			{wheel_elite_body[i]=new Texture(Gdx.files.internal("elite_wheel/body00"+i+".png"));}
			else
			{wheel_elite_body[i]=new Texture(Gdx.files.internal("elite_wheel/body0"+i+".png"));}
			
			wheel_elite_body[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		
		for (int i=0; i<13; i++)
		{
			if (i<10)
			{effect_shield[i]=new Texture(Gdx.files.internal("effect_shield/shield0"+i+".png"));}
			else
			{effect_shield[i]=new Texture(Gdx.files.internal("effect_shield/shield"+i+".png"));}
			
			effect_shield[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		for (int i=0; i<6; i++)
		{
			if (i<10)
			{effect_explosion[i]=new Texture(Gdx.files.internal("effect_explosion/explosion0"+i+".png"));}
			else
			{effect_explosion[i]=new Texture(Gdx.files.internal("effect_explosion/explosion"+i+".png"));}
			
			effect_explosion[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		for (int i=0; i<11; i++)
		{
			if (i<10)
			{effect_freeze[i]=new Texture(Gdx.files.internal("effect_freeze/freeze0"+i+".png"));}
			else
			{effect_freeze[i]=new Texture(Gdx.files.internal("effect_freeze/freeze"+i+".png"));}
			
			effect_freeze[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
	}
}
