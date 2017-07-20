package com.midfag.game.script;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import sun.util.logging.resources.logging;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.midfag.entity.Entity;
import com.midfag.equip.energoshield.EnergoshieldSimple;
import com.midfag.equip.weapon.WeaponSimpleFirle;
import com.midfag.equip.weapon.WeaponSimpleMinigun;
import com.midfag.equip.weapon.WeaponSimpleShotgun;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.Localisation;
import com.midfag.game.GUI.ButtonDialogNext;
import com.midfag.game.GUI.DialogPool;
import com.midfag.game.GUI.GUIDialog;
import com.midfag.game.GUI.cinematic.GUICinematic;
import com.midfag.game.script.actions.ScriptAction;
import com.midfag.game.script.actions.ScriptActionAddTimer;
import com.midfag.game.script.actions.ScriptActionCameraZoom;
import com.midfag.game.script.actions.ScriptActionCinematicAddFilm;
import com.midfag.game.script.actions.ScriptActionCinematicClose;
import com.midfag.game.script.actions.ScriptActionCinematicInit;
import com.midfag.game.script.actions.ScriptActionControl;
import com.midfag.game.script.actions.ScriptActionDialogAddText;
import com.midfag.game.script.actions.ScriptActionDialogExitPoint;
import com.midfag.game.script.actions.ScriptActionDialogInit;
import com.midfag.game.script.actions.ScriptActionEntryPoint;
import com.midfag.game.script.actions.ScriptActionFindAndChangeData;
import com.midfag.game.script.actions.ScriptActionFindAndPushEntity;
import com.midfag.game.script.actions.ScriptActionID;
import com.midfag.game.script.actions.ScriptActionPlaySound;
import com.midfag.game.script.actions.ScriptActionSay;

public class ScriptSystem {

	public static String entry_init="";
	public static List<ScriptAction> Actions_list=new ArrayList<ScriptAction>();
	public static List<ScriptTimer> Timer_list=new ArrayList<ScriptTimer>();
	public static List<Entity> Entity_with_id_list=new ArrayList<Entity>();
	
	public static List<ScriptTimer> Timer_pool=new ArrayList<ScriptTimer>();
	
	public static int execute_line;
	public static GUIDialog last_dialog_gui; 
	public static DialogPool pool;
	public static GUICinematic cinematic_gui;
	
	public ScriptSystem()
	{
		//Character.isDigit('1');
	}
	
	public static void add_script(String _name)
	{
		FileHandle file = Gdx.files.local("scripts/"+_name+".txt");
		
		String s=file.readString();
		String[] lines = s.split("\n");
		
		boolean already_exist=false;
		
		for (int i=0; i<Actions_list.size(); i++)
		{
			if((Actions_list.get(i) instanceof ScriptActionID)&&(Actions_list.get(i).name.equals(_name)))
			{
				already_exist=true;
				
				i=99999;
				break;
			}
		}
		
		if (!already_exist)
		{
			Actions_list.add(new ScriptActionID(_name));
			
			for (int i=0; i<lines.length; i++)
			{
				lines[i]=lines[i].substring(0, lines[i].length()-1);
				String[] data=lines[i].split(" ");
				
				String log="data mining=";
				//Helper.log("data mining=");
				
				for (int j=0; j<data.length; j++)
				{
					log+=",["+j+"]"+data[j];
					
					
				}
				
				/*Helper.log(log);
				Helper.log("-------");
				Helper.log("");*/
				
				String action=data[0].toLowerCase();
				if (action.equals("entry_point"))
				{Actions_list.add(new ScriptActionEntryPoint(data[1]));}
				
				if (action.equals("say"))
				{Actions_list.add(new ScriptActionSay(data[1]));}
				
				if (action.equals("dialog_gui_init"))
				{Actions_list.add(new ScriptActionDialogInit()); Helper.log("DIALOG INIT "+i);}
				
				if (action.equals("dialog_add_text"))
				{Actions_list.add(new ScriptActionDialogAddText(data));}
				
				if (action.equals("find_and_push_entity"))
				{Actions_list.add(new ScriptActionFindAndPushEntity(data));}
				
				if (action.equals("end")){Actions_list.add(null);}
				
				if (action.equals("add_timer"))
				{Actions_list.add(new ScriptActionAddTimer(data));}
				
				if (action.equals("dialog_exit_point"))
				{Actions_list.add(new ScriptActionDialogExitPoint(data));}
				
				if (action.equals("play_sound"))
				{Actions_list.add(new ScriptActionPlaySound(data));}
				
				if (action.equals("cinematic_init"))
				{Actions_list.add(new ScriptActionCinematicInit(data));}
				
				if (action.equals("cinematic_add_film"))
				{Actions_list.add(new ScriptActionCinematicAddFilm(data));}
				
				if (action.equals("cinematic_close"))
				{Actions_list.add(new ScriptActionCinematicClose(data));}
				
				if (action.equals("camera_zoom"))
				{Actions_list.add(new ScriptActionCameraZoom(data));}
				
				if (action.equals("camera_auto_zoom"))
				{Actions_list.add(new ScriptActionCameraAutoZoom(data));}
				
				if (action.equals("control"))
				{Actions_list.add(new ScriptActionControl(data));}
				
				if (action.equals("find_and_change_data"))
				{Actions_list.add(new ScriptActionFindAndChangeData(data));}
				
			}
		}
		else
		{
			Helper.log("SCRIPT ["+_name+"] ALREADY EXIST ON SCRIPT POOL");
		}
		
		
	}
	
	public static void execute(String _entry)
	{
		Helper.log("___---===SCRIPT EXECUTION START===---___");
		
		execute_line=-1;
		for (int i=0; i<Actions_list.size(); i++)
		{
			if ((Actions_list.get(i) instanceof ScriptActionEntryPoint)&&(Actions_list.get(i).name.equals(_entry)))
			{
				execute_line=i+1;
				
				i=99999;
				break;
			}
		}
		
		for (;;)
		{
			if (execute_line==-1){break;}
			if (
					(execute_line<Actions_list.size())
					&&
					(Actions_list.get(execute_line)!=null)
				)
			{
				Actions_list.get(execute_line).action();
				execute_line++;
			}
			else
			{
				execute_line=-1;
			}
		}
		
	}
	
	public static void update(float _d)
	{
		for (ScriptTimer tim:Timer_pool)
		{
			Timer_list.add(tim);
		}
		
		Timer_pool.clear();
		
		for (ScriptTimer tim:Timer_list)
		{
			if (tim.timer>0)
			{
				tim.timer-=_d;
				if (tim.timer<=0)
				{
					execute(tim.entry_point);
				}
			}
		}
	}
	
	public static List<Entity> find_entity(String _id)
	{
		List<Entity> l=new ArrayList<Entity>();
		
		if (_id.equals("player")){l.add(GScreen.pl);}
		if (_id.equals("player_human")){l.add(GScreen.pl_human);}
		if (_id.equals("player_mech")){l.add(GScreen.pl_mech);}
		
		for (Entity entity:ScriptSystem.Entity_with_id_list)
		{
			if (entity.id_for_script.equals(_id)){l.add(entity);}
		}
		

		return l;
		
	}
}
