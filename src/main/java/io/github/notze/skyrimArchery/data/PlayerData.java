package io.github.notze.skyrimArchery.data;

import java.io.Serializable;
import java.util.UUID;

import org.bukkit.entity.Player;

/**
 * This class stores all skill data for one player.
 * @author notze
 *
 */
public class PlayerData implements Serializable{
	
	private static final long serialVersionUID = 7937099286243964275L;

	// the player this object stands for
	UUID player;
	
	// the experience in archery
	int experience = 0;
	
	// the level in archery according to experience
	int level = 0;
	
	// all skill levels
	int overdraw = 0;
	int crit = 0;
	int power = 0;
	int hunter = 0;
	int ranger = 0;
	int quick = 0;
	int bullseye = 0;
	
	public PlayerData(Player player, int experience, int level, int overdraw,
			int crit, int power, int hunter, int ranger, int quick, int bullseye) {
		this.player = player.getUniqueId();
		this.experience = experience;
		this.level = level;
		this.overdraw = overdraw;
		this.crit = crit;
		this.power = power;
		this.hunter = hunter;
		this.ranger = ranger;
		this.quick = quick;
		this.bullseye = bullseye;
	}
	
	public PlayerData(Player player) {
		this.player = player.getUniqueId();
	}

	/**
	 * calculates the current skill level based on experience
	 */
	private void updateLevel(){
		if( level>=100 ) return;
		level = expToLevel(experience, 0);
		if( level > 100) level = 100;
	}
	
	/**
	 * calculate the level by total xp (vanilla formula)
	 * 
	 * @param total	total amount of experience
	 * @param level	initial 0
	 * @return the level
	 */
	static int expToLevel(int total, int level){
        int need;
        if(level <= 15)
            need = 7 + 2*level;
        else if(level <= 30)
            need = 37 + 5*(level-15);
        else
            need = 107 + 9*(level-30);
        if(total<need) return level;
        return expToLevel(total-need, level+1);
    }

	public UUID getPlayer() {
		return player;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
		updateLevel();
	}
	
	public void addExperience(int experience){
		this.experience += experience;
		updateLevel();
	}

	public int getOverdraw() {
		return overdraw;
	}

	public void setOverdraw(int overdraw) {
		this.overdraw = overdraw;
	}

	public int getCrit() {
		return crit;
	}

	public void setCrit(int crit) {
		this.crit = crit;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getHunter() {
		return hunter;
	}

	public void setHunter(int hunter) {
		this.hunter = hunter;
	}

	public int getRanger() {
		return ranger;
	}

	public void setRanger(int ranger) {
		this.ranger = ranger;
	}

	public int getQuick() {
		return quick;
	}

	public void setQuick(int quick) {
		this.quick = quick;
	}

	public int getBullseye() {
		return bullseye;
	}

	public void setBullseye(int bullseye) {
		this.bullseye = bullseye;
	}

	public int getLevel() {
		return level;
	}
}
