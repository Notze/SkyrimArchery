package io.github.notze.skyrimArchery.event;

import io.github.notze.skyrimArchery.Main;

import org.bukkit.event.Listener;

public class QuickShot implements Listener {

	// reference to main class
	Main main;
	
	public QuickShot(Main main){
		this.main = main;
	}
}
