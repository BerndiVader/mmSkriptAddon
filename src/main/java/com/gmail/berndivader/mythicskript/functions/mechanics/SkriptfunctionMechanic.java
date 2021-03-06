package com.gmail.berndivader.mythicskript.functions.mechanics;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import ch.njol.skript.lang.function.Function;
import ch.njol.skript.lang.function.Functions;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.ITargetedLocationSkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;

public class SkriptfunctionMechanic extends SkillMechanic implements INoTargetSkill,ITargetedEntitySkill,ITargetedLocationSkill {
	
	Function<?>function;
	Object[][]parameters;
	int dataPos,locationPos,entityPos;
	String name;
	
	public SkriptfunctionMechanic(String skill, MythicLineConfig mlc) {
		super(skill, mlc);
		
		name=mlc.getString("name","");
		function=Functions.getFunction(name);
		if(function!=null) {
			parameters=new Object[function.getParameters().length][];
			dataPos=locationPos=entityPos=-1;
			for(int i=0;i<function.getParameters().length;i++) {
				String type=function.getParameter(i).getType().getCodeName();
				switch(type) {
				case "skilldata":
					dataPos=i;
					break;
				case "location":
					locationPos=i;
					break;
				case "entity":
					entityPos=i;
					break;
				}
			}
		} else {
			Bukkit.getLogger().warning("Cant find function "+name);
		}
	}

	@Override
	public boolean cast(SkillMetadata meta) {
		if(dataPos>-1) parameters[dataPos]=new SkillMetadata[] {meta};
		if(locationPos>-1) parameters[locationPos]=new Location[0];
		if(entityPos>-1) parameters[entityPos]=new Entity[0];
		function.execute(parameters);
		return true;
	}

	@Override
	public boolean castAtLocation(SkillMetadata meta, AbstractLocation aLocation) {
		if(dataPos>-1) parameters[dataPos]=new SkillMetadata[] {meta};
		if(locationPos>-1) parameters[locationPos]=new Location[] {BukkitAdapter.adapt(aLocation)};
		if(entityPos>-1) parameters[entityPos]=new Entity[0];
		function.execute(parameters);
		return true;
	}

	@Override
	public boolean castAtEntity(SkillMetadata meta, AbstractEntity aEntity) {
		if(dataPos>-1) parameters[dataPos]=new SkillMetadata[] {meta};
		if(locationPos>-1) parameters[locationPos]=new Location[0];
		if(entityPos>-1) parameters[entityPos]=new Entity[] {aEntity.getBukkitEntity()};
		function.execute(parameters);
		return true;
	}

}
