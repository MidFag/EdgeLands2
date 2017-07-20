package com.midfag.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main extends Game {


    public static BitmapFont font;
    public static BitmapFont font_big;
    public static ShapeRenderer shapeRenderer;
    public static ShapeRenderer shapeRenderer_static;
    
    public static ShaderProgram shader_time_slow;
    public static ShaderProgram shader;
    
	public static boolean script_activate=true;
	//public static SpriteBatch batch_wheel;
    
	public Main(boolean _script)
	{
		script_activate=_script;
	}
	
    public void create() {


        Assets.load_assets();
        
        //Assets.music.play();
        
        shapeRenderer=new ShapeRenderer();
        shapeRenderer_static=new ShapeRenderer();
        
	        ShaderProgram.pedantic = false;
	        
			shader=new ShaderProgram(Gdx.files.internal("d.vert"),(Gdx.files.internal("d.frag")));
			if (!shader.isCompiled()) {System.err.println(shader.getLog()); shader=GScreen.batch.getShader();}
		
	        shader_time_slow=new ShaderProgram(Gdx.files.internal("d.vert"),(Gdx.files.internal("time_slow.frag")));
			if (!shader_time_slow.isCompiled()) {System.err.println(shader_time_slow.getLog()); shader=GScreen.batch.getShader();}
       
        
        Texture texture = new Texture(Gdx.files.internal("fonts/big.png"));
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);// true enables mipmaps
        font_big = new BitmapFont(Gdx.files.internal("fonts/big.fnt"), new TextureRegion(texture), false);
        
        texture = new Texture(Gdx.files.internal("rus.png"));
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);// true enables mipmaps
        font = new BitmapFont(Gdx.files.internal("rus.fnt"), new TextureRegion(texture), false);
        
        this.setScreen(new GScreen(this));
        

        
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
    	GScreen.batch.dispose();
        font.dispose();
    }

}