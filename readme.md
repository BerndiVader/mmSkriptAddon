#MythicMobs Skript Addon:
for MythicMobs 4.0.0 Build1930-1941


## Classes:

#### activemob
#### mythicspawner
#### mobdrop
#### mobitem


## Events:

### on mythicmob spawnevent
Called whenever MythicMobs spawns an ActiveMob. Returns event-activemob & event-entity.

### on mythicmob deathevent
Called whenever a MythicMob dies. Returns MobDrop with MobItems ***under development

## Conditions:

### %entity% instanceof activemob
true if the entity is a activemob:

Example:
```
on damage of player:
	if attacker instanceof activemob:
	    set {_am} to attacker
```

### %activemob% isdead
True if the ActiveMob is dead
	
### %activemob% has threattable
true if the activemob has a threattable:

Example:
```
on damage of player:
	if attacker instanceof activemob:
		set {_am} to attacker
		if {_am} has threattable:
			dropcombat for activemob {_am}
```

### %activemob% has mythicspawner
true if the activemob has a mythicspawner:

Example:
```		
on activemob spawnevent:
	if event-activemob has mythicspawner:
		set {_mythicspawner} to mythicspawner of event-activemob
```

### mythicspawner %mythicspawner% contains activemob %activemob%
True if the ActiveMob is attached to the MythicSpawner	


## Expressions:

#### for ActiveMob:

### activemob of %entity%
Get the activemob instance of the entity: set {_am} to activemob of event-entity

### location of activemob %activemob%
Returns the location of the activemob: set {_loc} to location of {_am}

### world of activemob %activemob%
Returns the world of the ActiveMob: set {_world} to world of event-activemob

### entity of activemob %activemob%
Get the entity instance of the ActiveMob: set {_entity} to entity of event-activemob

### activemob by uuid %string%
Get the ActiveMob by UUID. Return NULL if the entitiy is no ActiveMob.

### all activemobs [in world %string%]
Get all ActiveMobs on the whole server or, if a world is present in the expression, of the world:

Example:
```
on right click on entity:
	set {_count} to 0
	loop all activemobs in world "world":
		broadcast "%displayname of activemob loop-activemob%"
		add 1 to {_count}
	broadcast "%{_count}%"
```	

### displayname of activemob %activemob%
Returns the displayname as string of the ActiveMob, if it has one.

### owner of activemob %activemob%
Returns the owner of the ActiveMob as entity, if there is a owner present.

### lastaggro of activemob %activemob%
Get the entity instance of the entity which caused the last trigger on the activemob: set {_lastaggro} to lastaggro of {_am}

### toptarget of activemob %activemob%
Get the entity instance of the activemobs target, or the topthreatholder if the activemob has a threadtable: set {_target} to toptarget of {_am}

### uuid of activemob %activemob%
Returns a string with the uuid of the activemob: set {_uuid} to uuid of {_am}

### health of activemob %activemob%
Returns a number with the amount of health the activemob actually has left: set {_health} to health of {_am}

### maxhealth of activemob %activemob%
Returns a number with the max health of the activemob.

### mlc %string% of activemob %activemob%
%string% can be every YAML node of the mobs yml config. Returns the string of the %string% yml node of the activemob: set {_mobtype} to mlc {_node} of {_am}

### faction of activemob %activemob%
Returns the faction name the activemob is in: set {_faction} to faction of {_am}

### stance of activemob %activemob%
Returns the stance of the activemob, if it has one: set {_stance} to stance of {_am}

### level of activemob %activemob%
Returns the level of the activemob: set {_lvl} to level of {_am}

### playerkills of activemob %activemob%
Returns the playerkills of the activemob: set {_kills} to playerkills of {_am}

### lastsignal of activemob %activemob%
Returns the lastest signal of the activemob, if there is one: set {_signal} to lastsignal of {_am}


#### for MythicSpawner:

### mythicspawner of %activemob%
Returns the MythicSpawner instance of the activemob, if it have one: set {_ms} to mythicspawner of {_am}

### location of mythicspawner %mythicspawner%
Returns the Location of the MythicSpawner.

### world of mythicspawner %mythicspawner%
Returns the world of the MythicSpawner.

### all mythicspawners in world %string% - OR - all mythicspawners
Returns all MythicSpawners in the world %string% or if if used without world condition returns all MythicSpawners on the Server.

### all activemobs of mythicspawner %mythicspawner%
Returns all the ActiveMobs of the MythicSpawner

### mobtype of mythicspawner %mythicspawner%
Returns the MythicMob type of the MythicSpawner as String

### moblevel of mythicspawner %mythicspawner%
Returns the Level of the Spawner as Number

### cooldown of mythicspawner %mythicspawner% || remainingcooldown of mythicspawner %mythicspawner%
Returns the cooldown or the cooldown timer of the MythicSpawner. Both values are numbers in seconds.

### warmup of mythicspawner %mythicspawner% || remainingwarmup of mythicspawner %mythicspawner%
Returns the cooldown or the cooldown timer of the MythicSpawner. Both values are numbers in seconds.

### number of activemobs from mythicspawner %mythicspawner% || number of maxmobs from mythicspawner %mythicspawner%
Returns the number of spawned mobs or the number of MaxMobs that can be spawned.

### mobtype of mythicspawner %mythicspawner%
Retruns the MythicMob mobtype name of the MythicSpawner as string.

### moblevel of mythicspawner %mythicspawner%
Returns the level the MythicSpawner is set to as number.


#### for MobDrops & MobItems:

### all items of mobdrop %mobdrop%
Gets you the MythicMob Drops from the MythicMobDeathEvent. Only works in the mythicmob deathevent!



## Effects:

#### for ActiveMob:

### spawn mythicmob %string% at location %location% in world %string%
Spawns a MythicMob with mobtype %string% at the given location in the given world. Returns the ActiveMob instance of the spawned MythicMob. Mobtype string is case sensitive!

Example:
```
on right click:
	event-block is dirt
	set {_loc} to location of event-block
	set {_world} to "%world of event-block%"
	set {_mobtype} to "disguisedummy"
	set {_am} to spawn mythicmob {_mobtype} at location {_loc} in world {_world}
	if "%{_am}%" is "<none>":
		send "Something went wrong! Maybe the Mobtype does not exist?" to player
	else:
		send "You just spawned a MythicMob named %displayname of activemob {_am}%!" to player
```

### set health of activemob %activemob% to %number%
Set the health of the ActiveMob. Amount of 1 = 1/2 heart

### set maxhealth of activemob %activemob% to %number%
Set the max possible health the ActiveMob can have. 1 = 1/2 heart

### set lastaggro of activemob %activemob% to %entity%
Set the LastAggroEntity to a new entity: set lastaggro of {_am} to event-entity

### set dropcombat for activemob %activemob%
Clears the target, the threattable & calls the dropcombat trigger (if threattable is enabled) for the activemob: dropcombat for {_am}

### set %entity% to new target of activemob %activemob%
Gives the ActiveMob a new valid target. Must be an entity: set attacker to new target of {_am}

### set faction of activemob %activemob% to %string%
Set the MythicMobs Faction Tag for the ActiveMob: set faction of event-activemob to "Orcs"

### set stance of activemob %activemob% to %string%
Change the stance of the ActiveMob: set stance of event-activemob to "fight"

### set level of activemob %activemob% to %number%
Change the level of the ActiveMob: set level of {_am} to 10

### set owner of activemob %activemob% to %entity%
### set owner of activemob %activemob% to %string% by uuid
Change the owner of the ActiveMob eighter by %entity% or by UUID in String format.

### set kills of activemob %activemob% to %number%
Set the playerkills of the activemob to a new amount:

Example:
```
if event-entity instanceof activemob:
	set {_am} to activemob of event-entity
	set {_pks} to playerkills of activemob {_am}
	add 1 to {_pks}
	set kills of activemob {_am} to {_pks}
```

### send signal %string% to activemob %activemob% with trigger %entity%
Use this effect to send the signal %string% to the %activemob% with the trigger %entity%

### remove activemob %activemob%
Use this effect to remove the ActiveMob.


#### for MythicSpawner:

### activate mythicspawner %mythicspawner% || deactivate mythicspawner %mythicspawner%
Activates or deaktivates a MythicSpawner.

### set cooldown of mythicspawner %mythicspawner% to %number% || set remainingcooldown of mythicspawner %mythicspawner% to %number%
Set the cooldown or the remaining cooldown of the MythicMobSpawner in seconds.

### set warmup of mythicspawner %mythicspawner% to %number% || set remainingwarmup of mythicspawner %mythicspawner% to %number%
Set the warumup or the remaining warmup of the MythicMobspawner in seconds.

### set mobtype of mythicspawner %mythicspawner% to %string%
Change the Mobtype of the MythicSpawner. Where %string% is a valid MythicMob.

### set moblevel of mythicspawner %mythicspawner% to %number%
Change to moblevel of the MythicSpawner.

### make mythicspawner %mythicspawner% spawn
Activates the MythicSpawner. 

### attach activemob %activemob% to mythicspawner %mythicspawner%
Register the given ActiveMob to the MythicSpawner.


#### for MobDrops & MobItems:

### remove mobitem %mobitem% from mobdrop %mobdrop% || clear mobdrop %mobdrop%
Remove the given mobitem from the mobdrop or clears the mobdrop completely.

### set material of mobitem %mobitem% to %string%
Change the materialtype of the given mobitem into a new Materialtype. %string% have to be a valid Bukkit Material.

Example:
```
on mythicmob deathevent:
	loop all items of mobdrop event-mobdrop:
		if "%loop-mobitem%" contains "STONE":
			set material of mobitem loop-mobitem to "GRASS"
		if "%loop-mobitem%" contains "DIRT":
			remove mobitem loop-mobitem from mobdrop event-mobdrop
```

### add item %itemstack% to mobdrop %mobdrops%
Add a new item to the mobdrop of the mythicmob deathevent.

Example:
```
on mythicmob deathevent:
	clear mobdrop event-mobdrop
	set {_item} to diamond sword named "Godsword"
	add item {_item} to mobdrop event-mobdrop
```