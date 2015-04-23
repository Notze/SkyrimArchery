package io.github.notze.skyrimArchery.event;

import io.github.notze.skyrimArchery.Main;

import org.bukkit.event.Listener;

public class Ranger implements Listener {

	// reference to main class
	Main main;
	
	public Ranger(Main main){
		this.main = main;
	}
}
