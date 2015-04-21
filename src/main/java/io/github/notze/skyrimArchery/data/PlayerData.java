package io.github.notze.skyrimArchery.data;

import org.bukkit.entity.Player;

/**
 * This class stores all skill data for one player.
 * @author notze
 *
 */
public class PlayerData {
	
	// the player this object stands for
	Player player;
	
	// the experience in archery
	int experience;
	
	// the level in archery according to experience
	int level;
	
	// all skill levels
	int overdraw, crit, power, hunter, ranger, quick, bullseye;
	
	public PlayerData(){
		
	}
	
	public void addExperience(int experience){
		this.experience = experience;
		expToLevel();
	}
	
	/**
	 * calculates the current skill level based on experience
	 */
	private void expToLevel(){
		
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public int getLevel(){
		return this.level;
	}
}
