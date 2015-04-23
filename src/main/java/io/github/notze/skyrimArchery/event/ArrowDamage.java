package io.github.notze.skyrimArchery.event;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.notze.skyrimArchery.Main;
import io.github.notze.skyrimArchery.data.PlayerData;
import io.github.notze.skyrimArchery.persistence.Persistence;
import io.github.notze.skyrimArchery.util.ItemCheck;

public class ArrowDamage implements Listener{
	
	// reference to main class
	Main main;
	
	public ArrowDamage(Main main){
		this.main = main;
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e){
		Main.debug("checking for player shot arrow");
		if(e.getCause().equals(DamageCause.PROJECTILE)){
			Entity projectile = e.getDamager();
			if(projectile instanceof Arrow){
				Arrow arrow = (Arrow) projectile;
				if(arrow.getShooter() instanceof Player){
					Player p = (Player) arrow.getShooter();
					if( !(ItemCheck.isDatBow(p.getItemInHand())) ) return;
					
					// overdraw & crit
					int damage = customDamage(p, e.getDamage());
					Main.debug("original damage: " + e.getDamage());
					Main.debug("custom damage: " + damage);
					e.setDamage(damage);
					
					// powershot
					powershot(p, e.getEntity(), arrow);
					
					// hunter
					hunter(p);
					
					// bullseye
					bullseye(p, e.getEntity());
					
					// gain experience
					Persistence.playerData.get(p).addExperience((int) e.getFinalDamage());
				}
			}
		}	
	}
	
	private void powershot(Player p, Entity entity, Arrow arrow) {
		if( Persistence.playerData.get(p).getPower()<1 ) return;
		if( Math.random()<0.5 ) return;
		
		entity.setVelocity(arrow.getVelocity());
	}
	
	private void bullseye(Player p, Entity e){
		if( Persistence.playerData.get(p).getBullseye()<1 ) return;
		if( Math.random()<0.85 ) return;
		if( !(e instanceof LivingEntity) ) return;
		final LivingEntity livingEntity = (LivingEntity) e;
		p.sendMessage(ChatColor.YELLOW + "Paralized enemy!");
		
		if( livingEntity instanceof Creature ){
			new BukkitRunnable(){
				int timer = 8;
				Creature mob = (Creature) livingEntity;
				Location freezeLoc = mob.getLocation();
				
				@Override
				public void run() {
					if( timer<=0 || mob.isDead() ) cancel();
					mob.teleport(freezeLoc);
					mob.setTarget(null);
					timer--;
				}
			}.runTaskTimer(main, 0, 5);
		}else{
			new BukkitRunnable(){
				int timer = 8;
				Location freezeLoc = livingEntity.getLocation();
				
				@Override
				public void run() {
					if( timer<=0 || livingEntity.isDead() ) cancel();
					livingEntity.teleport(freezeLoc);
					timer--;
				}
			}.runTaskTimer(main, 0, 5);
		}
	}
	
	private void hunter(Player p){
		PlayerData data = Persistence.playerData.get(p);
		if( data.getHunter()>0 )
			p.getInventory().addItem(new ItemStack(Material.ARROW));
	}
	
	private int customDamage(Player p, Double dmg){
		PlayerData data = Persistence.playerData.get(p);
		double overdrawBonus = 1 + data.getOverdraw()/5.0; // max 2
		double skillBonus = 1 + data.getLevel()/200.0; // max 1,5
		double critBonus; // max 1
		double critChance; // max 0.2
		switch(data.getCrit()){
		case 3:
			critBonus = 1;
			critChance = 0.2;
			break;
		case 2:
			critBonus = 0.75;
			critChance = 0.15;
			break;
		case 1:
			critBonus = 0.5;
			critChance = 0.1;
			break;
		default:
			critBonus = 0;
			critChance = 0;
			break;
		}
		
		double newDmg = dmg * overdrawBonus * skillBonus;
		if( Math.random() >= 1-critChance ){
			p.sendMessage(ChatColor.YELLOW + "That was a critical hit!");
			newDmg += dmg*critBonus;
		}
		
		return (int) newDmg;
	}

}
