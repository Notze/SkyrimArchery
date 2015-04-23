package io.github.notze.skyrimArchery.event;

import io.github.notze.skyrimArchery.Main;

import org.bukkit.event.Listener;

public class CriticalShot implements Listener {

	// reference to main class
	Main main;
	
	public CriticalShot(Main main){
		this.main = main;
	}
}
