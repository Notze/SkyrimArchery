package io.github.notze.skyrimArchery.event;

import io.github.notze.skyrimArchery.Main;

import org.bukkit.event.Listener;

public class Overdraw implements Listener {

	// reference to main class
	Main main;
	
	public Overdraw(Main main){
		this.main = main;
	}
}
