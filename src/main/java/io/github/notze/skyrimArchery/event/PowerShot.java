package io.github.notze.skyrimArchery.event;

import io.github.notze.skyrimArchery.Main;

import org.bukkit.event.Listener;

public class PowerShot implements Listener {

	// reference to main class
	Main main;
	
	public PowerShot(Main main){
		this.main = main;
	}
}
