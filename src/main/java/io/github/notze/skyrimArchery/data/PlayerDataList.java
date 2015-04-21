package io.github.notze.skyrimArchery.data;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

/**
 * This class provides a list of player datas
 * @author notze
 *
 */
public class PlayerDataList {

	HashMap<UUID, PlayerData> items = new HashMap<UUID, PlayerData>();
	
	int size = 0;
	
	public PlayerDataList(){
		
	}
	
	public void add(PlayerData playerData){
		if( playerData == null ) return;
		Player player = playerData.getPlayer();
		if( player == null ) return;
		
		items.put(player.getUniqueId(), playerData);
		size = items.size();
	}
	
	public PlayerData getPlayerData(Player player){
		return items.get(player.getUniqueId());
	}
	
	public int size(){
		return size;
	}
}
