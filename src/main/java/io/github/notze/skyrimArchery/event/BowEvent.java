package io.github.notze.skyrimArchery.event;

import io.github.notze.skyrimArchery.Main;
import io.github.notze.skyrimArchery.persistence.Persistence;
import io.github.notze.skyrimArchery.util.ItemCheck;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BowEvent implements Listener{

	// reference to main class
	Main main;
	
	public BowEvent(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		if( !(e.getAction().equals(Action.RIGHT_CLICK_AIR)) ) return;
		Player p = e.getPlayer();
		ItemStack handItem = p.getItemInHand();
		if( !(ItemCheck.isDatBow(handItem)) ) return;
		
//		// ranger TODO get an idea!
//		if( Persistence.playerData.get(p).getRanger()>=1 ){
//			if( p.getWalkSpeed()<1 )
//				p.setWalkSpeed(1);
//		}
	}
	
	@EventHandler
	public void onEntityShootBow(EntityShootBowEvent e){
		if( !(e.getEntity() instanceof Player) ) return;
		Player p = (Player) e.getEntity();
		ItemStack handItem = p.getItemInHand();
		if( !(ItemCheck.isDatBow(handItem)) ) return;
		
		// quick shot
		if( Persistence.playerData.get(p).getQuick()>=1 ){
			Arrow arrow = (Arrow) e.getProjectile();
			arrow.setVelocity(arrow.getVelocity().normalize().multiply(3));
			arrow.setCritical(true);
		}
	}
}
