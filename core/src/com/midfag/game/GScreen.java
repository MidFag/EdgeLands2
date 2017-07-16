package com.midfag.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.utils.TimeUtils;
import com.midfag.entity.Entity;
import com.midfag.entity.EntityHuman;
import com.midfag.entity.EntityPlayer;
import com.midfag.entity.Shd;
import com.midfag.entity.decorations.DecorStoneWall;
import com.midfag.entity.decorations.DecorTrain;
import com.midfag.entity.decorations.DecorTube;
import com.midfag.entity.decorations.DecorTubeCarcas;
import com.midfag.entity.enemies.EntityPyra;
import com.midfag.entity.enemies.EntityVizjun;
import com.midfag.entity.missiles.Missile;
import com.midfag.equip.energoshield.EnergoshieldSimple;
import com.midfag.equip.weapon.WeaponSimpleFirle;
import com.midfag.equip.weapon.WeaponSimpleMinigun;
import com.midfag.equip.weapon.WeaponSimpleShotgun;
import com.midfag.game.GUI.GUI;
import com.midfag.game.GUI.buttons.Button;
import com.midfag.game.script.ScriptSystem;
import com.midfag.game.script.ScriptTimer;



public class GScreen implements Screen {
    public static final int path_cell = 45;

	private static final int enemy_gen_count = 10;

    public static SpriteBatch batch;
    public static SpriteBatch batch_static;
	
	final Main game;
    
	public static int scr_h=700;
	public static int scr_w=1000;
	
    public static float[][] cels=new float[200][200];
    public static float[][] path=new float[300][300];
    public static long[][] path_time=new long[300][300];
    
    public static int[][] tile_map=new int[300][300];
    public static int[][] tile_map_overlay=new int[300][300];
    
    public static Cluster[][] cluster=new Cluster[30][30];
    
    public static List<Phys> Phys_list = new ArrayList<Phys>();//список физических линий
    public static List<Missile> Missile_list = new ArrayList<Missile>();//список снарядов
    public static List<Entity> Entity_list = new ArrayList<Entity>();//список существ (НПС, декорации)
    public static List<Shd> Shd_list = new ArrayList<Shd>();//список следов, оставляемых снарядами
    public static List<GUI> GUI_list = new ArrayList<GUI>();//список пользовательских интерфейсов
    
    public static List<String> Timer = new ArrayList<String>();//список пользовательских интерфейсов
    
    public long saved_timer;
    
    public static Entity pl;
    
    public static Entity pl_mech;
    public static Entity pl_human;
    
    public static float time_speed=1;
    public static float real_delta;
    
    static OrthographicCamera camera;
    public static OrthographicCamera skills_camera;

   // public 

    public static List<Entity> Draw_list = new ArrayList<Entity>();//список пользовательских интерфейсов
    
    //public int[] test=new int[1000];
    
    public static Phys near_object;
    public float near_dist;
    
    public static Vector2 temp_vectorA=new Vector2(0.0f,0.0f);
    public static Vector2 temp_vectorB=new Vector2(0.0f,0.0f);
    
    public Vector2 prev_pos=new Vector2();
    
    public static String id="";
    
    
   
    
    public float cooldown;
    public float overlay_cooldown;
    
    public static boolean show_equip=false;
    public static boolean show_skills_wheel=false;
    public static boolean show_edit=false;
    
    public static boolean main_control=true;
    
    public static float zoom=1;
    
    public static float curx=1;
    public static float cury=1;
    
    public String active_id;
    
    public static List<Button> Button_list = new ArrayList<Button>();
    
    
    
    public boolean keypress=false;

	public static float wave_time;

	public static boolean path_visualisation=false;
	public static boolean phys_visualisation=false;

	public static Texture tile_texture=new Texture(Gdx.files.internal("tile/tile_texture.png"));;

	public static Texture[] tile= new Texture[50];

	public static float time_speed_value;
	
	public static FrameBuffer fbo= new FrameBuffer(Pixmap.Format.RGB888, 1000/1, 700/1, false);

	public static boolean show_dialog;

	
    
	public void add_timer(String _s)
	{
		Timer.add(_s+" ["+((TimeUtils.millis()-saved_timer)+"]"));
		saved_timer=TimeUtils.millis();
	}
	
	public static List<Entity> get_entity_list(Vector2 _v)
	{
		List<Entity> l=new ArrayList<Entity>();
		
		int cluster_x=(int)(_v.x/300);
	    int cluster_y=(int)(_v.y/300);
	        
	  
	    for (int x=cluster_x-2; x<=cluster_x+2; x++)
	    for (int y=cluster_y-2; y<=cluster_y+2; y++)
	    if ((x>=0)&&(y>=0)&&(x<30)&&(y<30))
	    for (int i=0; i<cluster[x][y].Entity_list.size();i++)
	    {
	    		l.add(cluster[x][y].Entity_list.get(i));
	    }
	    
	    return l;
	}
	
    public GScreen get_this()
    {
    	return this;
    }
    
    public static boolean collision (float _xs, float _ys, float _xe, float _ye, float _px, float _py, float _r)//;
    {
    	float xx=_xs-_xe;
    	float yy=_ys-_ye;
    	
    	float dy=yy/xx;
    	float dix=_xs-_px;
    	
    	float dx=xx/yy;
    	float diy=_ys-_py;
    	
    	float point_A=dix*dy;
    	float point_B=diy*dx;
    	
    	
    	
    	float iff=15.0f/(Math.abs(diy-point_A)-15.0f);

    	//float xx=_xs-_xe;
    	Main.shapeRenderer.setColor(Color.RED);
    	Main.shapeRenderer.circle(_px, _py-Math.abs(diy-point_A), 3);
    	
    	Main.shapeRenderer.setColor(Color.GREEN);
    	Main.shapeRenderer.circle(_px+Math.abs(dix-point_B), _py, 3);
    	
    	if (((Math.abs(dix-point_B))<15+15*iff)&&(iff>0))
    	{Main.shapeRenderer.setColor(Color.BLUE);}
    	else
    	{Main.shapeRenderer.setColor(Color.ORANGE);}
    	Main.shapeRenderer.circle(_px+15+15*iff, _py, 3);
    	
    	return false;
    }
    
    public static Entity add_entity_to_map(Entity _e)
    {
    	int x=(int)(_e.pos.x/300f);
    	int y=(int)(_e.pos.y/300f);
    
    	//System.out.println("Object="+_e);
    	
    	if ((x>0)&&(x<30)&&(y>0)&&(y<30))
    	{cluster[x][y].Entity_list.add(_e);}
    	

    	
    	_e.init("putter");
    	
    	return _e;
    }
    
    public static Phys get_contact(float _x,float _y,float _x2,float _y2,float _dx,float _dy,float _d,boolean _break, boolean _global, boolean _walk)
    {
    	float near_dist=9999;
    	near_object=null;
    	
    	int x_min=Math.min((int)(_x/300)-1, (int)(_x2/300)-1);
    	int x_max=Math.max((int)(_x/300)+1, (int)(_x2/300)+1);
    	
    	int y_min=Math.min((int)(_y/300)-1, (int)(_y2/300)-1);
    	int y_max=Math.max((int)(_y/300)+1, (int)(_y2/300)+1);
    	
    	x_min=Math.max(0, x_min);
    	y_min=Math.max(0, y_min);
    	
    	x_max=Math.min(29, x_max);
    	y_max=Math.min(29, y_max);
    	
    	for (int x=x_min; x<=x_max; x++)
    	for (int y=y_min; y<=y_max; y++)
    	for (int i=0; i<cluster[x][y].Phys_list.size(); i++)
    	{
    		
    		
    			Phys po=cluster[x][y].Phys_list.get(i).is_contact(_x,_y,_x2,_y2,_dx,_dy,_d);
    			
    			//cluster[x][y].Phys_list.get(i).draw();
    			
		    	if (po!=null)
		    	if (po.vector_mul<near_dist)
		    	if ((po.move_block)||(!_walk))
		    	{
		    		near_object=po;
		    		near_dist=po.vector_mul;
		    		
		    		if (_break){ x=999; y=999; break;}
		    	}
    		
    	}
    	
    	if (_global)
    	{
    		if (Phys_list.size()>0)
	    	for (int i=0; i<Phys_list.size(); i++)
	    	{
	
	    			Phys po=Phys_list.get(i).is_contact(_x,_y,_x2,_y2,_dx,_dy,_d);
	    			//Phys_list.get(i).draw();
	    			
			    	if (po!=null)
			    	if (po.vector_mul<near_dist)
			    	{
			    		near_object=po;
			    		near_dist=po.vector_mul;
			    	}
	    		}
    	}
    	
    	return near_object;
    }
    
    public static float rnd(float _r)
    {
    	return (float)(Math.random()*_r);
    	//return 0;
    }
    
    public static float sinR(float _a)
    {
    	return (float) Math.sin(Math.toRadians(_a));
    }
    
    public static float cosR(float _a)
    {
    	return (float) Math.cos(Math.toRadians(_a));
    }
    
    public static float sin(float _a)
    {
    	return (float) Math.sin((_a));
    }
    
    public static float cos(float _a)
    {
    	return (float) Math.cos((_a));
    }
    
    
    
    public GScreen(final Main gam) {
    	
    	Localisation.locad_local();

       
        
       
    	ScriptSystem.add_script("test");
    	//ScriptSystem.add_script("test");
    	ScriptSystem.Timer_list.add(new ScriptTimer("test_timer",			"test",			1.0f));
    	
    	
    	//ScriptSystem.add_script("test");
    	
    	//ScriptSystem.execute("test");
    	//find_and_push_entity SteelBoxDoor 0 50 0 50
    	
    	
    	

    	

    	
    	//System.out.println(listener.);
    	
    	tile_texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	fbo.getColorBufferTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	
        batch = new SpriteBatch();
        //batch.setShader(shader);
        
        batch_static = new SpriteBatch();
        //batch_wheel = new SpriteBatch();
        batch_static.setShader(batch.getShader());
        
        
        /*
        for (int i=0; i<1000; i++)
        {
     	   test[i]=(int) (Math.random()*1000);
        }*/
        

    	
        for (int i=0; i<30; i++)//;
        for (int j=0; j<30; j++)//;
        {
        	cluster[j][i]=new Cluster();
        }
    	
        for (int i=0; i<300; i++)//;
        for (int j=0; j<300; j++)//;
        {
        	path[j][i]=100;
        }
        
        temp_vectorA=new Vector2();
        temp_vectorB=new Vector2();
        
        this.game = gam;
        Random rn=new Random();
        
        InputHandler.but=-1;
        

       // Helper.LoadMap();
        

        
        
    	

        
       // float tubes_count
        //DecorTube o=null;
        
        //add_entity_to_map(new DecorStoneWall(new Vector2(200,200)));
        
        /*for (int k=0; k<0; k++)
        {
        	o=null;
        	
	        for (int j=0; j<3; j++)
	        {
	        	
	        	Entity tub=add_entity_to_map(new DecorTubeCarcas(new Vector2(200+j*440,300+k*55)));
	        	//Entity_list.add();
	        	
	        	if (o!=null){((DecorTube)tub).left=o;}
	        	if (o!=null){((DecorTube)o).right=((DecorTube)tub);}
	        	
	        	o=(DecorTube) tub;
	        	
	        	
		        for (int i=0; i<10; i++)
		        {
		        	if ((Math.random()<1.9)||(i==0)||(i==9))
		        	{
		        		tub=add_entity_to_map(new DecorTube(new Vector2(200+j*440+40+i*40,300+k*55)));
		        		
		        		((DecorTube)tub).left=o;
		        		((DecorTube)o).right=((DecorTube)tub);
		        		
		        		o=(DecorTube) tub;
		        	}
		        }
	        }
        }*/

        
        for (int i=0; i<300; i++)
        for (int j=0; j<300; j++)
        {
        	tile_map[j][i]=(int) rnd(3);
        	if (rnd(100)<20)
        	{tile_map[j][i]=(int) rnd(9);}
        	
        	tile_map_overlay[j][i]=-1;
        	
        }
        
        
        Gdx.input.setInputProcessor(new InputHandler());

        for (int i=0; i<0; i++)
        {
        	float randx=rnd(900);
        	float randy=rnd(700);
        	
        	Phys_list.add(new Phys(new Vector2(randx,randy), new Vector2(randx+rnd(200)-100,randy+rnd(200)-100),true,null,true));
        }
        

        /*
        Phys_list.add(new Phys(new Vector2(115,100), new Vector2(115f,200),true,null,true));
        Phys_list.add(new Phys(new Vector2(101,200), new Vector2(302,201),true,null,true));
        Phys_list.add(new Phys(new Vector2(301,200), new Vector2(250,100),true,null,true));
        Phys_list.add(new Phys(new Vector2(250,100), new Vector2(100,101),true,null,true));*/
        
        
        Phys_list.add(new Phys(new Vector2(80,9000), new Vector2(80,80),true,null,true));
        Phys_list.add(new Phys(new Vector2(9005,9000), new Vector2(9000,80),true,null,true));
        
        Phys_list.add(new Phys(new Vector2(80,80), new Vector2(9000,80),true,null,true));
        Phys_list.add(new Phys(new Vector2(80,9000), new Vector2(9000,9000),true,null,true));
        
			/*
			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
			 * /=/=/=/			*PHYS* GENERATOR		/=/=/=/
			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
			 * 
			 */
        camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000/1, 700/1);
		camera.position.set(new Vector3(500,350,0));
		
		skills_camera = new OrthographicCamera();
		skills_camera.setToOrtho(false, 1000/1, 700/1);
		skills_camera.position.set(new Vector3(500,350,0));
		skills_camera.update();
			
        for (int i=0; i<200*0; i++)
        {
	        Vector2 pc=new Vector2(rnd(10000),rnd(10000));
	        float vecangle=rnd(360);
	        Vector2 p1=new Vector2(pc.x+sinR(vecangle)*rnd(400),pc.y+cosR(vecangle)*rnd(400));
	        Vector2 p2=new Vector2(pc.x+sinR(vecangle+90)*rnd(400),pc.y+cosR(vecangle+90)*rnd(400));
	        Vector2 p3=new Vector2(pc.x+sinR(vecangle+180)*rnd(400),pc.y+cosR(vecangle+180)*rnd(400));
	        Vector2 p4=new Vector2(pc.x+sinR(vecangle+270)*rnd(400),pc.y+cosR(vecangle+270)*rnd(400));
	        
	        Phys_list.add(new Phys(p1, p2,true,null,true));
	        Phys_list.add(new Phys(p2, p3,true,null,true));
	        Phys_list.add(new Phys(p3, p4,true,null,true));
	        Phys_list.add(new Phys(p4, p1,true,null,true));
	        // create the camera and the SpriteBatch
	       
			

        }

		camera.zoom=1;
		

		


        for (int i=0; i<enemy_gen_count; i++)
        {
        	if (Math.random()>0.75f)
        	add_entity_to_map(new EntityVizjun(new Vector2(350+rnd(3000),300+rnd(3000))));
        	else
        	add_entity_to_map(new Entity(new Vector2(350+rnd(3000),300+rnd(3000))));
        }
        
        //Entity_list.add(new DecorStoneBarak(new Vector2(100,200)));

        for (int i=0; i<100; i++)
        for (int j=0; j<100; j++)
        {
        	cels[i][j]=rnd(1);

        	cels[i][j]=cels[i][j]*cels[i][j]*cels[i][j];
        	
        	if (rnd(100)<=1){cels[i][j]=10;}
        }

        Helper.LoadMap();
        
    	

	   	
	   
        
		
        
        Assets.post_load_assets();
        
	   
	   	
        for (int i=0; i<30; i++)//;
        {
        	switch (rn.nextInt(4))
        	{
        		case 0: pl.inventory[i]=new WeaponSimpleFirle();	break;
        		case 1: pl.inventory[i]=new WeaponSimpleMinigun();	break;
        		case 2: pl.inventory[i]=new WeaponSimpleShotgun();	break;
        		case 3: pl.inventory[i]=new EnergoshieldSimple();	break;
        	}
        }
        

       // pl.spr.setSize(51,21);
      

    }



    @SuppressWarnings("static-access")
	@Override
    public void render(float delta) {
    	
        /*for (int i=0; i<100; i++)
        {
        	timer_list[i]=0;
        }*/
    	
    	if ((InputHandler.key==Keys.F)&&(InputHandler.keyF_release))
    	{
    		//Helper.log("_-_-_-_--_-_-__--__--");
    		InputHandler.keyF_release=false;
    		
    		if (pl.equals(pl_human))
    		{
    			pl=pl_mech;
    			pl_human.hidden=true;
    		}
    		else
    		{
    			pl=pl_human;
    			pl_human.hidden=false;
    			pl_human.pos.x=pl_mech.pos.x;
    			pl_human.pos.y=pl_mech.pos.y-30;
    		}	
    	}
    	
    	ScriptSystem.update(delta);
    	
    	Timer.clear();
    	real_delta=delta;
    	
    	Draw_list.clear();
    	
    	//delta/=1f;
    	delta*=Math.min(1, time_speed);
    	
    	
    	/*
    	if (!main_control)
    	{delta/=10f;}*/
    	
    	if (delta>0.1f){delta=0.1f;}

    	
        Gdx.gl.glClearColor(0.45f, 0.5f, 0.55f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    	cooldown--;
    	overlay_cooldown--;
    	
    	curx=InputHandler.realx;
    	cury=InputHandler.realy;
    	
    	InputHandler.update();
    	
    	int plposx=(int)(camera.position.x/30f);
    	int plposy=(int)(camera.position.y/30f);
    	int draw_distance=Math.round(camera.zoom*(scr_w/55));
    	

    	
    	
    	fbo.begin();
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
    	



    	
    	batch.begin();

    	
    	wave_time+=real_delta;
    	
    	
		add_timer("start_point");
		
		//for (int k=0; k<100; k++)
		//for (int k=0; k<80; k++)
		for (int i=plposy-draw_distance; i<plposy+draw_distance; i++)
		for (int j=plposx-draw_distance; j<plposx+draw_distance; j++)
		if ((j>0)&&(j<300)&&(i>0)&&(i<300))
	 	{
			int ty=(int)tile_map[j][i]/8;
			int tx=tile_map[j][i]-ty*8;
				
				
			batch.draw(tile_texture, j*30-15, i*30-15, tx*60+tx+1, ty*60+ty+1, 60, 60);
	 	}
		
		
		add_timer("main_tile");
		
		

		for (int i=plposy+draw_distance; i>plposy-draw_distance; i--)
		for (int j=plposx-draw_distance; j<plposx+draw_distance; j++)
		if ((i>=0)&&(i<300)&&(j>=0)&&(j<300))
		{	
			if (tile_map_overlay[j][i]>=0)
			{
				int ty=(int)tile_map_overlay[j][i]/8;
				int tx=tile_map_overlay[j][i]-ty*8;
				
				batch.draw(tile_texture, j*30-15, i*30-15, tx*60+tx+1, ty*60+ty+1, 60, 60);
			}//}
		}
		batch.end();

		add_timer("overlay_tile");

 		game.shapeRenderer.begin(ShapeType.Filled);
 		Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        
        
			if (Shd_list!=null)
			for (int k=0; k<Shd_list.size(); k++)
			{
				Shd_list.get(k).draw();
				Shd_list.get(k).update(delta);
			}
		game.shapeRenderer.end();
		
		add_timer("missile_trail");
		
		batch.begin();
        int cluster_x=(int)(pl.pos.x/300);
        int cluster_y=(int)(pl.pos.y/300);
        

    	for (int x=cluster_x-4; x<=cluster_x+4; x++)
    	for (int y=cluster_y-4; y<=cluster_y+4; y++)
    	if ((x>=0)&&(y>=0)&&(x<30)&&(y<30))
    	for (int i=0; i<cluster[x][y].Entity_list.size();i++)
    	{
    		Entity e=cluster[x][y].Entity_list.get(i);
    		
    		if (!e.hidden)
    		{e.draw();}
    	}
        
        
        add_timer("fill_draw_list");
		
		
	    for (int k=0; k<Draw_list.size()-1; k++)
        for (int i=Draw_list.size()-1; i>0; i--)
        {
	        	if (
	        		Draw_list.get(i).pos.y
	        		>
	        		Draw_list.get(i-1).pos.y
	        		)
	        	{
		        		Entity swap=Draw_list.get(i);
		        		Draw_list.set(i, Draw_list.get(i-1));
		        		Draw_list.set(i-1,swap);
	        	}

        }
	    
	    add_timer("sort_draw_list");

	    
        for (int i=0; i<Draw_list.size(); i++)
        {
        	Draw_list.get(i).draw_action(delta);
        	Draw_list.get(i).effect_draw(delta);
        }
		
        add_timer("entity");
        

		batch.end();
		fbo.end();
		

    	
		batch_static.begin();


       
			Texture t=fbo.getColorBufferTexture();
			
			
			if (time_speed<=0.99)
			{
				batch_static.setShader(Main.shader_time_slow);
				Assets.noise.bind(1);
				Main.shader_time_slow.setUniformi("u_texture", 1);
				
				t.bind(0);
				Main.shader_time_slow.setUniformi("u_texture2", 0);
				//Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
													//		(1-0.5)/0.5
													//		(0.5-1.0)/1
				
				float time_slow=Math.min(1, (1-time_speed)*2);
				Main.shader_time_slow.setUniformf("value", 1-time_slow);
		    }
			//Main.shader_time_slow.set("value", time_speed_value+(time_speed-time_speed_value));
			
				batch_static.setColor(1.0f, 1.0f, 1.0f, 1.0f);
				batch_static.draw(t,0,scr_h,scr_w,-scr_h);
				
			
			
			
			
			/*
			for (int i=0; i<6; i++)
			//{batch_static.draw(t,0-2*i,700+2*i,1000+4*i,-700-4*i);}
			{batch_static.draw(t,sinR(i*60)*8,700+cosR(i*60)*8,1000,-700);}
			*/
			
				
				if (!Gdx.input.isKeyPressed(Keys.H))
				{
					batch_static.setColor(1.0f, 1.0f, 1.0f, 0.05f);
					for (int i=0; i<6; i++)
					//{batch_static.draw(t,0-2*i,700+2*i,1000+4*i,-700-4*i);}
					{batch_static.draw(t,sinR(i*60+40)*2f,scr_h+cosR(i*60+40)*2,scr_w,-scr_h);}
					
					batch_static.setColor(1.0f, 1.0f, 1.0f, 0.025f);
					for (int i=0; i<6; i++)
					//{batch_static.draw(t,0-2*i,700+2*i,1000+4*i,-700-4*i);}
					{batch_static.draw(t,sinR(i*60+20)*4,scr_h+cosR(i*60+20)*4,scr_w,-scr_h);}		
					
					batch_static.setColor(1.0f, 1.0f, 1.0f, 0.0125f);
					for (int i=0; i<6; i++)
					//{batch_static.draw(t,0-2*i,700+2*i,1000+4*i,-700-4*i);}
					{batch_static.draw(t,sinR(i*60)*8,scr_h+cosR(i*60)*8,scr_w,-scr_h);}
					
					batch_static.setColor(1.0f, 1.0f, 1.0f, 0.0125f);
					for (int i=0; i<6; i++)
					//{batch_static.draw(t,0-2*i,700+2*i,1000+4*i,-700-4*i);}
					{batch_static.draw(t,sinR(i*60+30)*16,scr_h+cosR(i*60+30)*16,scr_w,-scr_h);}
				}
			

			/*
			for (int i=0; i<6; i++)
			//{batch_static.draw(t,0-2*i,700+2*i,1000+4*i,-700-4*i);}
			{batch_static.draw(t,sinR(i*60+45)*32,scr_h+cosR(i*60+45)*32,scr_w,-scr_h);}*/
			
			 batch_static.setShader(batch.getShader());
			
			

			
			
			
			
			batch_static.setColor(Color.WHITE);
		batch_static.end();
		
		add_timer("draw_FBO");
		

    	
	    plposx=(int)(pl.pos.x/path_cell);
	    plposy=(int)(pl.pos.y/path_cell);
		
		 	Gdx.gl.glEnable(GL20.GL_BLEND);
	        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	        game.shapeRenderer.begin(ShapeType.Filled);
		

	        long time=TimeUtils.millis();
		
			if (path[plposx][plposy]<900)
			{
				path[plposx][plposy]=0;
				path_time[plposx][plposy]=time;
			}
			else
			{
				if (path[plposx+1][plposy]<900)
				{path[plposx+1][plposy]=0;
				path_time[plposx+1][plposy]=time;}
				
				if (path[plposx-1][plposy]<900)
				{path[plposx-1][plposy]=0;
				path_time[plposx-1][plposy]=time;}
				
				if (path[plposx][plposy+1]<900)
				{path[plposx][plposy+1]=0;
				path_time[plposx][plposy+1]=time;}
				
				if (path[plposx][plposy-1]<900)
				{path[plposx][plposy-1]=0;
				path_time[plposx][plposy-1]=time;}
				
			}
		

	    
	
 		for (int i=plposy-20; i<plposy+20; i++)
 		for (int j=plposx-20; j<plposx+20; j++)
 		if ((i>=0)&&(i<300)&&(j>=0)&&(j<300))
 		{
 			
 			if (path[j][i]>=999)
 			{
 				game.shapeRenderer.setColor(1,1/2f,1/4f,1/8f);
 			}
 			else
 			{
 				game.shapeRenderer.setColor(path[j][i]/100f,1-path[j][i]/100f,0.1f,0.5f);
 				if (path[j][i]==0){game.shapeRenderer.setColor(1,1,1,0.5f);}
 			}
 			
 			if ((cooldown<=0)&&(path[j][i]<100)&&(path[j][i]>=0))
 			{
	 			if ((j>0)&&(path_time[j-1][i]<path_time[j][i])&&(path[j-1][i]<900)){path[j-1][i]=path[j][i]+1; path_time[j-1][i]=path_time[j][i];}
	 			if ((j<290)&&(path_time[j+1][i]<path_time[j][i])&&(path[j+1][i]<900)){path[j+1][i]=path[j][i]+1; path_time[j+1][i]=path_time[j][i];}
	 			
	 			if ((i>0)&&(path_time[j][i-1]<path_time[j][i])&&(path[j][i-1]<900)){path[j][i-1]=path[j][i]+1; path_time[j][i-1]=path_time[j][i];}
	 			if ((i<290)&&(path_time[j][i+1]<path_time[j][i])&&(path[j][i+1]<900)){path[j][i+1]=path[j][i]+1; path_time[j][i+1]=path_time[j][i];}
	 			
	 			
 			}
 			
 				if (path_visualisation){game.shapeRenderer.rect(j*path_cell, i*path_cell, path_cell,path_cell);}

 			/*
 			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
 			 * /=/=/=/		*PATH* VISUALISATION		/=/=/=/
 			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
 			 * 
 			 */
 		}
 		
 		 add_timer("path_calculate");
 		
 		game.shapeRenderer.end();
 		
 		if (show_edit)
 		{
			game.shapeRenderer.begin(ShapeType.Line);
			Gdx.gl.glEnable(GL20.GL_BLEND);
	        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	        
			game.shapeRenderer.setColor(0.15f, 0.16f, 0.17f, 0.05f);
			for (int i=0; i<30; i++)
			for (int j=0; j<30; j++)
			{		
				game.shapeRenderer.rect(0, 0, j*30*10,i*30*10);
			}
			game.shapeRenderer.end();
 		}


 		for (int i=plposy-20; i<plposy+20; i++)
 		for (int j=plposx-20; j<plposx+20; j++)
		if ((i>=0)&&(i<300)&&(j>=0)&&(j<300))
 		{
 			if ((cooldown<=0)&&(path[j][i]<100)&&(path[j][i]>=0))
 			{
	 			if ((j>0)&&(path_time[j-1][i]<path_time[j][i])&&(path[j-1][i]<900)){path[j-1][i]=path[j][i]+1; path_time[j-1][i]=path_time[j][i];}
	 			if ((j<290)&&(path_time[j+1][i]<path_time[j][i])&&(path[j+1][i]<900)){path[j+1][i]=path[j][i]+1; path_time[j+1][i]=path_time[j][i];}
	 			
	 			if ((i>0)&&(path_time[j][i-1]<path_time[j][i])&&(path[j][i-1]<900)){path[j][i-1]=path[j][i]+1; path_time[j][i-1]=path_time[j][i];}
	 			if ((i<290)&&(path_time[j][i+1]<path_time[j][i])&&(path[j][i+1]<900)){path[j][i+1]=path[j][i]+1; path_time[j][i+1]=path_time[j][i];}	
 			}

 			/*
 			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
 			 * /=/=/=/		*PATH* VISUALISATION		/=/=/=/
 			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
 			 * 
 			 */
 		}
 		
 		 add_timer("path_calculate_additional");
		
		
 					if (cooldown<=0)
 					{cooldown=5;}
 					
 					if (overlay_cooldown<=0)
 					{overlay_cooldown=30;}
 		
 		

        Gdx.gl.glDisable(GL20.GL_BLEND);
        
 		game.shapeRenderer.setColor(Color.WHITE);
        
 		if (!pl.rotate_block)
	    {
 			float a=InputHandler.posx-pl.pos.x;
	    	float b=(InputHandler.posy)-pl.pos.y;
	    	
	    	float c=(float) Math.toDegrees(Math.atan2(a, b));
	    	pl.rot=180-c+180;
	    	if (c<0){c=360+c;}
	    	
	    	
	    	if (c>360){c=c-360;}
	    	//pl.spr.setRotation(360-c);
	    	if (c>347)
	    	{pl.draw_sprite=0;}
	    	else
	    	{pl.draw_sprite=Math.min(15, Math.round((c)/22.5f));}
    	}
    	

    	

            near_object=null;
        	near_dist=99999;
        
    		
       	 prev_pos.x=pl.pos.x+0.0001f;
       	 prev_pos.y=pl.pos.y+0.0001f;
       	 
      
       	 boolean is_press=false;
       	
       	 if (InputHandler.MB){is_press=true; /*time_speed+=real_delta*1;*//* System.out.println("MB");*/}
       	 
       	 if ((((pl.armored_shield!=null)&&(pl.armored_shield.value>0))||(pl.armored_shield==null))&&(pl.active))
       	 {
	       	 if (Gdx.input.isKeyPressed(Keys.W)){pl.add_impulse(0, pl.speed,delta); /*time_speed+=real_delta*2;*/ is_press=true; pl.move_vert=true; pl.direction=0;}
	       	 if (Gdx.input.isKeyPressed(Keys.S)){pl.add_impulse(0, -pl.speed,delta);/*time_speed+=real_delta*2;*/ is_press=true; pl.move_vert=true; pl.direction=2;}
	       	 
	       	 if (Gdx.input.isKeyPressed(Keys.A)){pl.add_impulse(-pl.speed, 0,delta);/*time_speed+=real_delta*2;*/ is_press=true; pl.move_vert=false; pl.direction=3;}
	       	 if (Gdx.input.isKeyPressed(Keys.D)){pl.add_impulse( pl.speed, 0,delta);/*time_speed+=real_delta*2;*/ is_press=true; pl.move_vert=false; pl.direction=1;}
       	 }
       	 
       	 if (time_speed>1.0){time_speed=1.0f;}
       	 
       	 if (!is_press)
	     {
       		 //time_speed-=1.5f*real_delta;
       		 //if (time_speed<1){time_speed*=(float)Math.pow(0.25f,real_delta);}
	       	 
	       	 if (time_speed<0.025f){time_speed=0.025f;}
       	 }
       	
       	time_speed_value=1.0f;
       	
       	time_speed+=(time_speed_value-time_speed)/50.0f;
       	
       
       	
       	if (time_speed<1.0f)
       	{
       		
       		//System.out.println (""+);
       		Assets.time_slow.setVolume(Assets.time_slow_id, Math.min(1,1.0f-time_speed)*5.0f);
       		Assets.music.setVolume((1.0f-(1-time_speed)*5)/3f);
       		Assets.time_slow.setPitch(Assets.time_slow_id, time_speed);
       	}
       	else
       	{
       		Assets.time_slow.setVolume(Assets.time_slow_id, 0.0f);
       	}
       	
       	
       	
       	 /*
       	 float move_vector_angle=(float) Math.toDegrees(Math.atan2(pl.pos.x-prev_pos.x, pl.pos.y-prev_pos.y));
       	 move_vector_angle=(float) Math.toRadians(move_vector_angle);
       	 float move_vector=prev_pos.dst(pl.pos);*/
        	/*
       	 near_object=null;

       	 	
         	near_object=get_contact(prev_pos.x,prev_pos.y,pl.pos.x,pl.pos.y,(pl.pos.x-prev_pos.x)/move_vector,(float)Math.cos(move_vector_angle),move_vector,false,true,true);

         	//if (near_object!=null)
         	//Phys_list.remove(near_object);
         	//Missile_list.get(i).update(delta);
         
         
     	if (near_object!=null)
     	if (near_object.parent!=pl)
     	{
     		 System.out.println("Wallstuck");
     		if (near_object.subline_A>=0)
     		{pl.hard_move(sinR(near_object.angle)*-(near_object.vector_mul-(move_vector+0.52f)), cosR(near_object.angle)*-(near_object.vector_mul-(move_vector+0.52f)),1);}
     		else
     		{pl.hard_move(sinR(near_object.angle)*(near_object.vector_mul-(move_vector+0.52f)), cosR(near_object.angle)*(near_object.vector_mul-(move_vector+0.52f)),1);}	
     		//pl.pos.add((float)Math.sin(Math.toRadians(near_object.angle))*-(near_object.vector_mul-(speed+0.2f)), (float)Math.cos(Math.toRadians(near_object.angle))*-(near_object.vector_mul-(speed+0.2f)));
     	}*/

     	
    	@SuppressWarnings("unused")
		Phys po=null;
    	Missile mis=null;
        for (int i=0; i<Missile_list.size();i++)
        {
        	mis=Missile_list.get(i);
        	
        	temp_vectorA.x=mis.pos.x;
        	temp_vectorA.y=mis.pos.y;
        	{Missile_list.get(i).update(delta);}
        	
        	near_dist=99999;
        	near_object=null;//w

            	 near_object=get_contact(mis.px,mis.py,mis.pos.x,mis.pos.y,mis.sx,mis.sy,mis.speed*delta,false,true,false);
            
        	if (near_object!=null)
        	{
        		if (near_object.parent==null)
        		{
        			mis.pos.x=near_object.goal_x;
        			mis.pos.y=near_object.goal_y;
        			
        			Missile_list.get(i).lifetime=-1;
        			//System.out.println("Wallstuck");
        			}
        		else
        		if
        		(
        				(near_object.parent!=null)
        				&&
        				(
        						(((Entity) near_object.parent).is_enemy!=mis.is_enemy)
        						||
        						(((Entity) near_object.parent).burrow)
        						||
        						(((Entity) near_object.parent).is_decor)
        				)
        		)
        		{
        			
        			mis.pos.x=near_object.goal_x;
        			mis.pos.y=near_object.goal_y;
        			
        			if (!Missile_list.get(i).is_decor)
        			{
        				float reflect_value=((Entity) near_object.parent).armored_shield.total_reflect;
        				float reflect_chance=Math.max(0.65f, 1.0f-reflect_value/Missile_list.get(i).damage);//=1-0=1
        				
        				reflect_chance*=1.0f-(reflect_value/(reflect_value+100.0f));//=1*(1-0/100)=1*1=1
        				
	        			if ((Math.random()<reflect_chance))
	        			{
	        				Missile_list.get(i).lifetime=-1;
	        				((Entity) near_object.parent).hit_action(Missile_list.get(i).damage,true);
	        			}
	        			else
	        			{
	        				for (int k=0; k<((Entity) near_object.parent).Skills_list.size(); k++)
	        				{
	        					
	        					if (((Entity) near_object.parent).Skills_list.get(k).learned)
	        					{
	        						((Entity) near_object.parent).Skills_list.get(k).prereflect_action(Missile_list.get(i),((Entity) near_object.parent));
	        					}
	        					
	        				}
	        				Missile_list.get(i).lifetime=10;
	        				Missile_list.get(i).angle+=Math.toRadians(180+(Math.random()*10-5));
	        				
	        				
	        				Missile_list.get(i).update_vectors_state();
	        				
	        				Missile_list.get(i).is_enemy=((Entity) near_object.parent).is_enemy;
	        				Missile_list.get(i).col=Color.GREEN;
	        				
	        				for (int k=0; k<((Entity) near_object.parent).Skills_list.size(); k++)
	        				{
	        					
	        					if (((Entity) near_object.parent).Skills_list.get(k).learned)
	        					{
	        						((Entity) near_object.parent).Skills_list.get(k).reflect_action(Missile_list.get(i),((Entity) near_object.parent));
	        					}
	        					
	        				}
	        			}
        			}
        			else
        			{Missile_list.get(i).lifetime=-1;}
        		}        		//pl.pos.add((float)Math.sin(Math.toRadians(near_object.angle))*-(near_object.vector_mul-(speed+0.5f)), (float)Math.cos(Math.toRadians(near_object.angle))*-(near_object.vector_mul-(speed+0.5f)));
        	}
        	else
        	{}
        	
        	temp_vectorB.x=mis.pos.x;
        	temp_vectorB.y=mis.pos.y;
        	
        	Missile_list.get(i).draw();
			
			if (Missile_list.get(i).have_shd)
			{Shd_list.add(Missile_list.get(i).get_shd(new Vector2(temp_vectorA.x,temp_vectorA.y), new Vector2(temp_vectorB.x,temp_vectorB.y)));}	
        }
        
        add_timer("missile_collision_detect");
        
            for (int i=0; i<Missile_list.size();i++)
            {
            	if (Missile_list.get(i).lifetime<=0)
            	{
            		Missile_list.remove(i);
            		i--;
            	}

            	//Missile_list.get(i).check();
            }
            
        int fx=0;
        int fy=0;


      batch.begin();
      
      	for (int x=cluster_x-4; x<=cluster_x+4; x++)
      	for (int y=cluster_y-4; y<=cluster_y+4; y++)
      	if ((x>=0)&&(y>=0)&&(x<30)&&(y<30))
        for (int i=0; i<cluster[x][y].Entity_list.size();i++)
        {
        	Entity e=cluster[x][y].Entity_list.get(i);
        	if (e.is_AI)
        	{	
        		if (!e.is_decor)
        		{
	        		fx=(int)(e.pos.x/path_cell);
		            fy=(int)(e.pos.y/path_cell);
		            
		        	if ((fx>0)&&(fy>0)&&(fx<299)&&(fy<299))
		        	{
		        			int mov_dir=1;
		        			if ((e.target!=null)&&(e.target.pos.dst(e.pos)<500))
		        			{mov_dir=-1;}
		        			
		        			
			        		if ((path[fx][fy+1]<path[fx][fy-1]-0)&&(path[fx][fy+1]<900))
				        	{e.add_impulse(0, e.speed*mov_dir,delta);}
				        	
				        	if ((path[fx][fy-1]<path[fx][fy+1]-0)&&(path[fx][fy-1]<900))
				        	{e.add_impulse(0, -e.speed*mov_dir,delta);}
				        	
				        	if ((path[fx+1][fy]<path[fx-1][fy]-0)&&(path[fx+1][fy]<900))
				        	{e.add_impulse(e.speed*mov_dir, 0,delta);}
				        	
				        	if ((path[fx-1][fy]<path[fx+1][fy]-0)&&(path[fx-1][fy]<900))
				        	{e.add_impulse(-e.speed*mov_dir, 0,delta);}
			        	
			        	if (path[fx][fy]<100)
			        	{path[fx][fy]=700+path[fx][fy];}
	    			
			        	path_time[fx][fy]=time;
		        	}
	        	}
        	}
        	
        	if (!e.hidden)
        	{e.update(delta);}
        	
        }
      	
      	 add_timer("entity_update");
      

        
        /*
       for (int k=0; k<200; k++)
       for (int i=0; i<200; i++)
       {
    	   if (test[i]>test[i+1])
    	   {
    		   int swap=test[i];
    		   test[i]=test[i+1];
    		   test[i+1]=swap;
    	   }
       }*/

        
        batch.end();
        
    	
        
        game.shapeRenderer.begin(ShapeType.Filled);
        
        	
        	for (int i=0; i<Phys_list.size(); i++)
        	{Phys_list.get(i).draw();}
        	
        	//if (show_edit)
        	if(phys_visualisation)
	      	for (int x=cluster_x-3; x<cluster_x+3; x++)
	      	for (int y=cluster_y-3; y<cluster_y+3; y++)
	      	if ((x>=0)&&(y>=0)&&(x<30)&&(y<30))
	      	{
	      		for (int i=0; i<cluster[x][y].Phys_list.size(); i++)
	      		{cluster[x][y].Phys_list.get(i).draw();}
	      	}
        	
        	if (pl.dead_time>0)
        	{   
        		Gdx.gl.glEnable(GL20.GL_BLEND);
            	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        		game.shapeRenderer.setColor(1, 0.25f, 0.125f, Math.min(1, (pl.dead_time/10f)*0.8f));
        		game.shapeRenderer.rect(-10000, -10000, 20000, 20000);
        	}
        	//game.shapeRenderer.circle(InputHandler.posx, InputHandler.posy, 3);
        	//game.shapeRenderer.circle(prev_pos.x+(float)Math.sin((move_vector_angle))*speed*10,prev_pos.y+(float)Math.cos((move_vector_angle))*speed*10, 3);
        game.shapeRenderer.end();
        
		Main.shapeRenderer.begin(ShapeType.Filled);
			//Main.shapeRenderer.setColor(0.5f, 1, 0.6f, 0.5f);
		
		Main.shapeRenderer.rect(pl.pos.x-camera.zoom/2f, pl.pos.y-camera.zoom/2f, camera.zoom,camera.zoom);
		
			//Main.shapeRenderer.setColor(1.0f, 0.5f, 0.25f, 1.0f);
			//Main.shapeRenderer.line(pl.pos.x,pl.pos.y,InputHandler.posx,InputHandler.posy);
			/*
			Main.shapeRenderer.rect(4000-15, 4000-15,30,30);
			collision (pl.pos.x, pl.pos.y,InputHandler.posx,InputHandler.posy,4000,4000,15);*/
			
		Main.shapeRenderer.end();
		
		Main.shapeRenderer.setColor(0.9f, 1, 0.95f, 1.0f);
		

		batch.begin();
			
	    	
	        batch.setColor(Color.WHITE);
        batch.end();
        
		 batch_static.begin();
			for (int i=0; i<GUI_list.size(); i++)
				{GUI_list.get(i).update(real_delta);}
			
			for (int i=0; i<GUI_list.size(); i++)
				{GUI_list.get(i).update2(real_delta);}
		batch_static.end();
		batch_static.setColor(Color.WHITE);
		
		
		batch_static.begin();
		
		if ((main_control))
		{
			
			
				for (int i=0; i<Timer.size(); i++)
				{
					Main.font.draw(batch_static, "draw delay: "+Timer.get(i), scr_w-350, scr_h-i*25-15);
				}
			
			
			Main.font.draw(batch_static, "WARM: "+pl.armored_shield.warm, 17, 170);
			
			Main.font.draw(batch_static, "FPS: "+Math.round(1.0f/real_delta), 17, 30);
			Main.font.draw(batch_static, "TIME SPEED: "+time_speed, 17, 90);
			Main.font.draw(batch_static, "MY: "+pl.impulse.y, 17, 60);
			
			batch_static.draw(Assets.panel, 400, 17);
		
			for (int i=0; i<50*pl.armored_shield.value/pl.armored_shield.total_value; i++)
			{
			batch_static.draw(Assets.diod, 400+7*i+5, 17+3);
			}
			

		}
		if ((Gdx.input.isButtonPressed(1))&&(!show_edit))
		//if (InputHandler.but==1)
		{
			
			float camlen=(float) Math.sqrt((pl.pos.x-InputHandler.posx)*(pl.pos.x-InputHandler.posx)+(pl.pos.y-InputHandler.posy)*(pl.pos.y-InputHandler.posy));
			camlen/=2;
		    camera.position.add(-(camera.position.x-pl.pos.x+sinR(180-pl.rot)*camlen)/20, -(camera.position.y-pl.pos.y+cosR(180-pl.rot)*camlen)/20, 0.0f);
		    camera.update();
			
		}
		else
		{
			camera.position.add(-(camera.position.x-pl.pos.x)/10, -(camera.position.y-pl.pos.y)/10, 0.0f);
			camera.update();
		}
			
			int pos=0;
			
			if (main_control)
			{
				for (int i=0; i<pl.Skills_list.size(); i++)
				{
					if (pl.Skills_list.get(i).learned)
					{
						
						pl.Skills_list.get(i).time_action(delta);
						
						if (pl.Skills_list.get(i).need_to_indicate)
		    			{pl.Skills_list.get(i).indicate(425+pos,100,real_delta);
		    			pos+=55;}
					}
				}
				
				pos=0;
				
				for (int i=0; i<5; i++)
				{
					if (pl.armored_module[i]!=null)
					{
						pl.armored_module[i].indicate(425+pos, 200, real_delta);
						pos+=55/*=*/;
					}
				}
			}

		//game.shapeRenderer_static.begin(ShapeType.Filled);
			
		
			for (int i=0; i<Button_list.size(); i++)
			{
				if (!Button_list.get(i).need_remove)
				{Button_list.get(i).draw();}
			}
			
			for (int i=0; i<Button_list.size(); i++)
			{
				if (!Button_list.get(i).need_remove)
				{
					Button_list.get(i).second_draw();
					Button_list.get(i).update(delta);
					Button_list.get(i).second_update(delta);
				}
				else
				{Button_list.remove(i); i--;}
			}
			
			batch_static.end();
		
		//game.shapeRenderer_static.end();
		
		
		
		
		if ((pl.armored_shield!=null)&&(pl.armored_shield.value<=0))
		{
			camera.rotate(1);
			camera.zoom+=0.001f;
			
			 if (Gdx.input.isKeyPressed(Keys.ESCAPE))
			 {
				 pl.armored_shield.value=1000;
				 pl.dead_time=0;
				 
				 camera.direction.set(0, 0, -1);
				 camera.up.set(0, 1, 0);
				 camera.zoom=1;
				 camera.update();
				 
				 
			            
			     //Entity_list.add(new DecorStoneBarak(new Vector2(100,200)));
			     
			     pl.pos.x=4000;
			     pl.pos.y=4000;
			     
			    
			     
			     pl.init("ressurect");
			     
				
			}
		}
        
		

		/*camera.position.x=Math.round(camera.position.x);
		camera.position.y=Math.round(camera.position.y);*/
		
        batch.setProjectionMatrix(camera.combined);
        
        //batch_static.setProjectionMatrix(camera.combined);
      
        
        game.shapeRenderer.setProjectionMatrix(camera.combined);
        
        game.shapeRenderer_static.setProjectionMatrix(skills_camera.combined);
        batch_static.setProjectionMatrix(skills_camera.combined);
      
        
        //InputHandler.but=-1;
    }

    @Override
    public void resize(int width, int height) {
    	
    	scr_w=width;
    	scr_h=height;

    	camera.setToOrtho(false, width, height);
    	skills_camera.setToOrtho(false, width, height);
    	
    	skills_camera.position.x=width/2.0f;
    	skills_camera.position.y=height/2.0f;
    	
    	camera.position.x=pl.pos.x;
    	camera.position.y=pl.pos.y;
    	
    	fbo = new FrameBuffer(Pixmap.Format.RGB888, width, height, false);
    	fbo.getColorBufferTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	
    	
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        //rainMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

      
    }



}