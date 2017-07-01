package com.midfag.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Assets {
	
	public static Texture[] tile=new Texture[100];
	
	public static Texture panel;
	public static Texture diod;
	public static Texture particle;
	
	public static Texture tube;
	public static Texture tube_carcas;
	
	public static Texture explosion;
	
	public static Sound shoot00;
	public static Sound shoot01;
	public static Sound shoot02;
	
	public static Sound metal_sound;

	public static Sound plastic;
	
	public static Sound metal_destroy;
	public static Sound saw;
	//public static Sound music;
	
	public static long music_id;
	
	static Music music = Gdx.audio.newMusic(Gdx.files.internal("mus.mp3"));

	public static Sound flash;

	public static Sound minigun;

	public static Sound shoot03;
	public static Sound shoot75523235;

	public static Sound chaos;
	public static Sound shoot_laser;
	
	public static Sound engine=Gdx.audio.newSound(Gdx.files.internal("engine3.wav"));;
	
	public static Sound crash=Gdx.audio.newSound(Gdx.files.internal("crash.wav"));;
	
	public static Sprite skill_wheel=new Sprite(new Texture(Gdx.files.internal("eye.png")));

	public static Texture missile;
	
	public static Texture rect=new Texture(Gdx.files.internal("rect.png"));
	
	public static Texture stone_wall_01=new Texture(Gdx.files.internal("stone_wall_01.png"));
	public static Texture stone_pilon_01=new Texture(Gdx.files.internal("stone_pilon_01.png"));

	public static Texture stone_wall_02=new Texture(Gdx.files.internal("stone_wall_02.png"));;
	
	public static Texture building_wall_out=new Texture(Gdx.files.internal("building_01.png"));;
	public static Texture building_wall_in=new Texture(Gdx.files.internal("building_02.png"));;
	
	/*
	public static Texture mech_down=new Texture(Gdx.files.internal("mech01.png"));;
	public static Texture mech_right=new Texture(Gdx.files.internal("mech02.png"));;
	public static Texture mech_left=new Texture(Gdx.files.internal("mech03.png"));;*/
	
	public static Texture mech_leg=new Texture(Gdx.files.internal("leg.png"));
	public static Texture mech_foot=new Texture(Gdx.files.internal("foot.png"));
	public static Texture point_start=new Texture(Gdx.files.internal("point_start.png"));
	public static Texture rama=new Texture(Gdx.files.internal("rama.png"));
	
	public static Texture mech_foot_shadow=new Texture(Gdx.files.internal("foot_shadow.png"));
	
	//public static Texture 
	
	public static Texture shadow=new Texture(Gdx.files.internal("mech/shadow.png"));
	
	public static Texture[] pyra_body=new Texture[16];
	public static Texture[] pyra_head=new Texture[16];
	
	public static Texture[] wheel_body=new Texture[16];
	
	public Assets()
	{
		
	}
	
	public static void load_assets()
	{
		stone_wall_01.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		stone_wall_02.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		stone_pilon_01.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		for (int i=0; i<=19; i++)
		{
			if (i<10)
			{tile[i]=new Texture(Gdx.files.internal("tile/tile0"+i+".png"));}
			else
			{tile[i]=new Texture(Gdx.files.internal("tile/tile"+i+".png"));}
			
			tile[i].setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		}
		

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
		
		tube=new Texture(Gdx.files.internal("decor_tube.png"));
		tube_carcas=new Texture(Gdx.files.internal("decor_tube_carcas.png"));
		
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

		
		music.setLooping(true);
		music.setVolume(0.95f);
		music.play();
		
	}
	
	public static void post_load_assets()
	{
		for (int i=0; i<16; i++)
		{
			if (i<10)
			{GScreen.pl.tex[i]=new Texture(Gdx.files.internal("mech/mech00"+i+".png"));}
			else
			{GScreen.pl.tex[i]=new Texture(Gdx.files.internal("mech/mech0"+i+".png"));}
		}
		
		for (int i=0; i<16; i++)
		{
			if (i<10)
			{GScreen.pl.bottom_tex[i]=new Texture(Gdx.files.internal("mech/bot0"+i+".png"));}
			else
			{GScreen.pl.bottom_tex[i]=new Texture(Gdx.files.internal("mech/bot"+i+".png"));}
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
	}
}
