package conanthecivilian.oat.entity;

import conanthecivilian.oat.faction.Faction;

public interface IFactionEntityLiving {
	Faction faction = null;

	void setFaction(Faction faction);

	Faction getFaction();
}
