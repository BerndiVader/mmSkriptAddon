package com.gmail.berndivader.mmSkriptAddon.mm400.events.custom;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;

public class mmMythicSpawnerSpawnEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	
	private ActiveMob am;
	private MythicSpawner ms;

	public mmMythicSpawnerSpawnEvent (MythicSpawner spawner, ActiveMob mob) {
		this.am = mob;
		this.ms = spawner;
	}
	
	public ActiveMob getAm() {
		return this.am;
	}
	public MythicSpawner getMs() {
		return this.ms;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
        return handlers;
    }
}
