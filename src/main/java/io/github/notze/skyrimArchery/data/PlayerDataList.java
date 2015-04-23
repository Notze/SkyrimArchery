package io.github.notze.skyrimArchery.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.bukkit.entity.Player;

/**
 * This class provides a list of player datas
 * @author notze
 *
 */
public class PlayerDataList implements Serializable{

	private static final long serialVersionUID = 2763693538150632530L;

	HashMap<UUID, PlayerData> items = new HashMap<UUID, PlayerData>();
	
	int size = 0;
	
	
	public PlayerDataList(){
		
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean contains(Player p) {
		for( UUID key : items.keySet() )
			if( items.get(key).getPlayer().equals(p) )
				return true;
		return false;
	}

	public Iterator<PlayerData> iterator() {
		return new Iterator<PlayerData>(){
			int position = 0;
			PlayerData[] p = toArray();
			
			@Override
			public boolean hasNext() {
				return position < size-1;
			}

			@Override
			public PlayerData next() {
				return p[position++];
			}
		};
	}

	public PlayerData[] toArray() {
		if( isEmpty() ) return null;
		PlayerData[] out = new PlayerData[size];
		int i=0;
		for( UUID key : items.keySet() )
			out[i] = items.get(key);
		return out;
	}
	
	public boolean add(PlayerData e) {
		if( e == null ) return false;
		UUID player = e.getPlayer();
		if( player == null ) return false;
		
		items.put(player, e);
		size++;
		return true;
	}
	
	public PlayerData get(Player p){
		PlayerData out = items.get(p.getUniqueId());
		if( out!=null )	return out;
		out = new PlayerData(p);
		add(out);
		return out;
	}
	
	public boolean remove(Player p) {
		return items.remove(p.getUniqueId()) != null;
	}

	public boolean addAll(Collection<PlayerData> c) {
		for( PlayerData p : c )
			add(p);
		return true;
	}

	public void clear() {
		items = new HashMap<UUID, PlayerData>();
		size = 0;
	}

}
