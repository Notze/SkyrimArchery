package io.github.notze.skyrimArchery;

import io.github.notze.skyrimArchery.event.*;
import io.github.notze.skyrimArchery.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin{
	
	static boolean DEBUG = false;
	public static String dataFolder;
	static Main main; // 'this' not accessible inside {{}}
	static Logger logger; // for static debug logging
	
	public static FileConfiguration config;
	
	@SuppressWarnings("serial")
	@Override
	public void onEnable(){
		// create datafolder
		getDataFolder().mkdirs();
		
		dataFolder = getDataFolder().toString();
		main = this;
		logger = getLogger();
		config = getConfig();
		
		Persistence.load();
		
		// commands
		CommandExecutor commandExecutor = new CommandExecutor(this);
		this.getCommand("saskill").setExecutor(commandExecutor);
		this.getCommand("sacheat").setExecutor(commandExecutor);
		
		// skills and other events
		List<Listener> listeners = new ArrayList<Listener>(){{
			add( new ArrowDamage(main) );
			add( new BowEvent(main) );
			add( new Skilling(main) );
			add( new Bullseye(main) );
			add( new CriticalShot(main) );
			add( new HuntersDiscipline(main) );
			add( new Overdraw(main) );
			add( new PowerShot(main) );
			add( new QuickShot(main) );
			add( new Ranger(main) );
		}};
		for( Listener listener : listeners )
			this.getServer().getPluginManager().registerEvents(listener, this);
	}
	
	@Override
	public void onDisable(){
		Persistence.save();
	}
	
	/**
	 * send message to server log for debugging purpose
	 * @param msg debug message
	 */
	public static void debug(String msg){
		if( DEBUG )
			logger.info(msg);
	}
	
}
